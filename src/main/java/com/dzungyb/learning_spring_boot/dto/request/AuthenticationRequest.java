package com.dzungyb.learning_spring_boot.dto.request;

import lombok.Data;

@Data
public class AuthenticationRequest {
    private String userName;
    private String password;

}
