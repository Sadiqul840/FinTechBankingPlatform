package com.sadiq.fintech.auth.service;

import com.sadiq.fintech.auth.dto.*;

public interface UserService {

    RegisterResponse register(RegisterRequest request);
    LoginResponse login(LoginRequest request);

}
