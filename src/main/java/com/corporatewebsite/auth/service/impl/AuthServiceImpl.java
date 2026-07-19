package com.corporatewebsite.auth.service.impl;

import com.corporatewebsite.auth.dto.LoginRequest;
import com.corporatewebsite.auth.dto.LoginResponse;
import com.corporatewebsite.auth.entity.AdminUser;
import com.corporatewebsite.auth.repository.AdminUserRepository;
import com.corporatewebsite.auth.service.AuthService;
import com.corporatewebsite.auth.service.JwtService;
import com.corporatewebsite.common.exception.BadRequestException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final AdminUserRepository adminUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthServiceImpl(AdminUserRepository adminUserRepository,
                           PasswordEncoder passwordEncoder,JwtService jwtService) {

        this.adminUserRepository = adminUserRepository;
        this.passwordEncoder = passwordEncoder;

        this.jwtService = jwtService;
    }

    @Override
    public LoginResponse login(LoginRequest request) {

        AdminUser admin = adminUserRepository
                .findByUsername(request.getUsername())
                .orElseThrow(() ->
                        new BadRequestException("Invalid username or password."));

        if (!admin.getActive()) {
            throw new BadRequestException("Admin account is inactive.");
        }

        if (!passwordEncoder.matches(
                request.getPassword(),
                admin.getPassword())) {

            throw new BadRequestException("Invalid username or password.");
        }

        String token =
                jwtService.generateToken(admin.getUsername());

        return new LoginResponse(admin.getUsername(), admin.getRole().name(), token);
    }
}