package com.kiki.newsbe.controller.auth;

import com.kiki.newsbe.annotations.swagger.PostEndpoint;
import com.kiki.newsbe.annotations.swagger.SwaggerTypeGroup;
import com.kiki.newsbe.controller.advices.BaseController;
import com.kiki.newsbe.request.auth.RegisterRequest;
import com.kiki.newsbe.request.auth.LoginRequest;
import com.kiki.newsbe.response.auth.LoginResponse;
import com.kiki.newsbe.response.auth.RegisterResponse;
import com.kiki.newsbe.services.auth.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@BaseController("api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostEndpoint(
            value = "/register",
            tagName = "User",
            description = "Register",
            group = SwaggerTypeGroup.DEFAULT
    )
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest request) {
        RegisterResponse response = authService.register(request);
        return ResponseEntity.ok(response);
    }

    @PostEndpoint(
            value = "/login",
            tagName = "User",
            description = "Login",
            group = SwaggerTypeGroup.DEFAULT
    )
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        LoginResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }
    
    @PostEndpoint(
            value = "/refresh-token",
            tagName = "User",
            description = "Refresh Token",
            group = SwaggerTypeGroup.DEFAULT
    )
    public ResponseEntity<LoginResponse> refreshToken(@RequestHeader("Authorization") String refreshToken) {
        LoginResponse response = authService.refreshToken(refreshToken);
        return ResponseEntity.ok(response);
    }

    @PostEndpoint(
            value = "/logout",
            tagName = "User",
            description = "Logout",
            group = SwaggerTypeGroup.DEFAULT
    )
    public ResponseEntity<Map<String, Boolean>> logout(@RequestHeader("Authorization") String token) {
        System.out.println("Authorization header: " + token);
        Map<String, Boolean> response = authService.logout(token);
        return ResponseEntity.ok(response);
    }
}
