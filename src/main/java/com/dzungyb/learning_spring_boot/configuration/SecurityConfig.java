package com.dzungyb.learning_spring_boot.configuration;

import com.nimbusds.jose.JWSAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.spec.SecretKeySpec;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Value("${signed_key}")
    private String SIGNER_KEY;

    private final String[] PUBLIC_ENDPOINT_POST = {"/users", "/auth/token", "/auth/introspect"};
    private final String[] PUBLIC_ENDPOINT_GET = {"/users"};

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.csrf(Customizer.withDefaults())
//                .authorizeHttpRequests(authorize -> authorize
//                        .anyRequest().authenticated())
//                .httpBasic(Customizer.withDefaults())
//                .formLogin(Customizer.withDefaults());

        http.authorizeHttpRequests(request ->
                request.requestMatchers(HttpMethod.POST, PUBLIC_ENDPOINT_POST).permitAll()
                        .requestMatchers(HttpMethod.GET, PUBLIC_ENDPOINT_GET).permitAll()
//                        .anyRequest().authenticated());
                        .anyRequest().permitAll());

        http.oauth2ResourceServer(
                oauth2 -> oauth2.jwt(jwtConfigurer -> jwtConfigurer.decoder(jwtDecoder())));

        http.csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }

    @Bean
    JwtDecoder jwtDecoder() {
        SecretKeySpec secretKeySpec = new SecretKeySpec(SIGNER_KEY.getBytes(), "HS512");
        return NimbusJwtDecoder
                .withSecretKey(secretKeySpec)
                .macAlgorithm(MacAlgorithm.HS512)
                .build();
    }


}

/* @Configuration
: Được init, run các public method mà chứa các annotation bean và inject các bean vào applicationContext.
: Khi một lớp được đánh dấu bằng @Configuration, Spring sẽ quét qua lớp đó để tìm các phương thức được đánh dấu
bằng @Bean. Các phương thức này sẽ tạo và cấu hình các bean và đưa chúng vào context của spring.

_ Khi cần một bean cụ thể chỉ cần yêu cầu spring cung cấp nó từ context bằng cách sử dụng @Autowired hoặc thông qua injection.

 */