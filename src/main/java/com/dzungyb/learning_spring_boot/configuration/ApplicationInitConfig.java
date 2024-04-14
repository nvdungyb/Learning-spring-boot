package com.dzungyb.learning_spring_boot.configuration;

import com.dzungyb.learning_spring_boot.entity.User;
import com.dzungyb.learning_spring_boot.enums.Role;
import com.dzungyb.learning_spring_boot.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@Configuration
@AllArgsConstructor
@Slf4j
public class ApplicationInitConfig {
    private final PasswordEncoder passwordEncoder;

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepo) {
        return args -> {
            if (userRepo.findByUserName("ADMIN").isEmpty()) {
                Set<String> roles = new HashSet<>();
                roles.add(Role.ADMIN.name());
                User user = User.builder()
                        .userName("ADMIN")
                        .password(passwordEncoder.encode("ADMIN"))
                        .roles(roles)
                        .build();

                userRepo.save(user);
                log.warn("Admin user created with default password: ADMIN, please change it immediately.");
            }
        };

    }

}
