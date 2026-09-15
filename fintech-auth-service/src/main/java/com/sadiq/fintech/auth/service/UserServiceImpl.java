package com.sadiq.fintech.auth.service;

import com.sadiq.fintech.auth.dto.*;
import com.sadiq.fintech.auth.entity.User;
import com.sadiq.fintech.auth.repository.UserRepository;
import com.sadiq.fintech.auth.security.JwtService;
import org.aspectj.weaver.patterns.IToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements  UserService {

    final private  UserRepository userRepository;
    final private  PasswordEncoder passwordEncoder;
    final private  JwtService jwtService;
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }
    @Override
    public RegisterResponse register(RegisterRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPhoneNumber(request.getPhoneNumber());

        user.setRole("CUSTOMER");
        user.setStatus("ACTIVE");

        User savedUser = userRepository.save(user);


        RegisterResponse registerResponse = new RegisterResponse();
        registerResponse.setUserId(savedUser.getUserId());
        registerResponse.setUsername(savedUser.getUsername());
        registerResponse.setMessage("Registration Suceessfully");
        return registerResponse;
    }

    @Override
    public LoginResponse login(LoginRequest request) {

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(()->new RuntimeException("User not found"));



        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtService.generateToken(user);

        LoginResponse response = new LoginResponse();
        response.setUserId(user.getUserId());
        response.setUsername(user.getUsername());
        response.setStatus(user.getStatus());
        response.setToken(token);



        return response;
    }
}
