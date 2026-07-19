package com.corporatewebsite.auth.service;

import com.corporatewebsite.auth.dto.LoginRequest;
import com.corporatewebsite.auth.dto.LoginResponse;

public interface AuthService {

    LoginResponse login(LoginRequest request);

}