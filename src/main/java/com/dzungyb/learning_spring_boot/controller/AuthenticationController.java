package com.dzungyb.learning_spring_boot.controller;

import com.dzungyb.learning_spring_boot.dto.request.AuthenticationRequest;
import com.dzungyb.learning_spring_boot.dto.response.ApiResponse;
import com.dzungyb.learning_spring_boot.dto.response.AuthenticationResponse;
import com.dzungyb.learning_spring_boot.exception.ErrorCode;
import com.dzungyb.learning_spring_boot.service.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @GetMapping("/login")
    public ApiResponse<AuthenticationResponse> login(@RequestBody AuthenticationRequest loginRequest) throws Throwable {
        ApiResponse<AuthenticationResponse> apiResponse = new ApiResponse<>();

        var result = authenticationService.authenticate(loginRequest);

//        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
//        authenticationResponse.setAuthenticated(isLogin);
//        apiResponse.setResult(authenticationResponse);
//        return apiResponse;

        return apiResponse.<AuthenticationResponse>builder()
                .result(result)
                .build();
    }

}
