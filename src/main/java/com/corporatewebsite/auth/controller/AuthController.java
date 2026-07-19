package com.corporatewebsite.auth.controller;

import com.corporatewebsite.auth.dto.LoginRequest;
import com.corporatewebsite.auth.dto.LoginResponse;
import com.corporatewebsite.auth.service.AuthService;
import com.corporatewebsite.common.response.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ApiResponse<LoginResponse> login(
            @Valid @RequestBody LoginRequest request) {

        return ApiResponse.success(
                "Login successful",
                authService.login(request)
        );
    }
}