package com.dzungyb.learning_spring_boot.service;

import com.dzungyb.learning_spring_boot.dto.request.AuthenticationRequest;
import com.dzungyb.learning_spring_boot.dto.request.IntrospectRequest;
import com.dzungyb.learning_spring_boot.dto.response.AuthenticationResponse;
import com.dzungyb.learning_spring_boot.dto.response.IntrospectResponse;
import com.dzungyb.learning_spring_boot.entity.User;
import com.dzungyb.learning_spring_boot.exception.AppException;
import com.dzungyb.learning_spring_boot.exception.ErrorCode;
import com.dzungyb.learning_spring_boot.repository.UserRepository;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService {
    UserRepository userRepo;
    PasswordEncoder passwordEncoder;

    @NonFinal                           // Không cho phép constructor inject thuộc tính này.
    @Value("${signed_key}")             // Đọc giá trị của biến trong file application.properties họặc application.yml;
    protected String SIGNER_KEY;

    // Xác thực JWT.
    public IntrospectResponse introspect(IntrospectRequest request) throws JOSEException, ParseException {
        var token = request.getToken();

        JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());

        SignedJWT signedJWT = SignedJWT.parse(token);

        Date expirationTime = signedJWT.getJWTClaimsSet().getExpirationTime();
        var verified = signedJWT.verify(verifier);

        return IntrospectResponse.builder()
                .valid(verified && expirationTime.after(new Date()))
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) throws Throwable {
        var user = userRepo.findByUserName(request.getUserName())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        boolean authenticated = passwordEncoder.matches(request.getPassword(), user.getPassword());
        // Mỗi lần mã hóa một password, một salt ngẫu nhiên được sử dụng, Salt này được lưu cùng với password đã mã hóa.
        // Khi kiểm tra mật khẩu, salt được sử dụng để mã hóa mật khẩu nhập vào và thực hiện so sánh giá trị hashValue của cả hai -> Chức năng của BCryptPasswordEncoder.

        if (!authenticated) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        } else {
            var token = generateToken(user);
            return AuthenticationResponse.builder()
                    .authenticated(authenticated)
                    .token(token)
                    .build();
        }
    }

    private String generateToken(User user) {
        // header: chứa thông tin về thuật toán mã hóa.
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        // JWT Claims Set: tác dụng chứa thông tin cần thiết để cho vào payload.
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(user.getUserName())
                .issuer("dzungdev")
                .issueTime(new Date())
                .expirationTime(new Date(Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()))
                .claim("scope", buildScope(user))
                .build();

        // Payload: chứa thông tin từ JWTClaimsSet đã được chuyển thành JSON.
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        // Signature.: được tạo ra bằng header, payload và một khóa bí mật.
        JWSObject jwsObject = new JWSObject(header, payload);

        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            System.out.println("Can not create token");
            throw new RuntimeException(e);
        }
    }

    private String buildScope(User user) {
        if (!user.getRoles().isEmpty())
            return String.join(" ", user.getRoles());
        return "";
    }

}
