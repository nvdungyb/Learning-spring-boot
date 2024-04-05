package com.dzungyb.learning_spring_boot.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component                                  // Sử dụng để Spring biết đây là một Bean và khởi tạo nó.
@ConfigurationProperties(prefix = "app")    // Sử dụng để map các properties trong file application.properties vào class này.
public class Properties {
    private String email;
    private String password;
    private String username;
    private List<String> authors;
}
