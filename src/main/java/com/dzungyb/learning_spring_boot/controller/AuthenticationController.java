package com.dzungyb.learning_spring_boot.controller;

import com.dzungyb.learning_spring_boot.dto.request.AuthenticationRequest;
import com.dzungyb.learning_spring_boot.dto.request.IntrospectRequest;
import com.dzungyb.learning_spring_boot.dto.response.IntrospectResponse;
import com.dzungyb.learning_spring_boot.dto.response.ApiResponse;
import com.dzungyb.learning_spring_boot.dto.response.AuthenticationResponse;
import com.dzungyb.learning_spring_boot.service.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/token")
    public ApiResponse<AuthenticationResponse> login(@RequestBody AuthenticationRequest loginRequest) throws Throwable {
        ApiResponse<AuthenticationResponse> apiResponse = new ApiResponse<>();

        var result = authenticationService.authenticate(loginRequest);

        return apiResponse.<AuthenticationResponse>builder()
                .code(200)
                .result(result)
                .build();
    }

    @PostMapping("/introspect")
    ApiResponse<IntrospectResponse> authenticate(@RequestBody IntrospectRequest request) throws ParseException, JOSEException {
        var result = authenticationService.introspect(request);

        return ApiResponse.<IntrospectResponse>builder()
                .code(200)
                .result(result)
                .build();
    }

}
