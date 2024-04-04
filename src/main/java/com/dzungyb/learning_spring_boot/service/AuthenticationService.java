package com.dzungyb.learning_spring_boot.service;

import com.dzungyb.learning_spring_boot.dto.request.AuthenticationRequest;
import com.dzungyb.learning_spring_boot.dto.response.AuthenticationResponse;
import com.dzungyb.learning_spring_boot.entity.User;
import com.dzungyb.learning_spring_boot.exception.AppException;
import com.dzungyb.learning_spring_boot.exception.ErrorCode;
import com.dzungyb.learning_spring_boot.repository.UserRepository;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService {
    UserRepository userRepo;

    @NonFinal
    protected static final String SIGNER_KEY = "ljtFnmhIFDwq0AwaK2OKKWCS5qPa9Oiu+rfR9goE7t6wd7KyoksE3sj7VAQI4jmP";

    public AuthenticationResponse authenticate(AuthenticationRequest request) throws Throwable {
        var user = userRepo.findByUserName(request.getUserName())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        boolean authenticated = new BCryptPasswordEncoder().matches(request.getPassword(), user.getPassword());

        if (!authenticated) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        } else {
            var token = generateToken(request.getUserName());
            return AuthenticationResponse.builder()
                    .authenticated(authenticated)
                    .token(token)
                    .build();
        }
    }

    private String generateToken(String username) {
        // header: chứa thông tin về thuật toán mã hóa.
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        // JWT Claims Set: tác dụng chứa thông tin cần thiết để cho vào payload.
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(username)
                .issuer("dzungdev")
                .issueTime(new Date())
                .expirationTime(new Date(Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()))
                .claim("userId", "userId")
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

}
