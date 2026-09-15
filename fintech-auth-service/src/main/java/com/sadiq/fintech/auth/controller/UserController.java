package com.sadiq.fintech.auth.controller;

import com.sadiq.fintech.auth.dto.LoginRequest;
import com.sadiq.fintech.auth.dto.LoginResponse;
import com.sadiq.fintech.auth.dto.RegisterRequest;
import com.sadiq.fintech.auth.dto.RegisterResponse;
import com.sadiq.fintech.auth.service.UserService;


import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

    final private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> userRegistration(@Valid @RequestBody RegisterRequest request){
        return new ResponseEntity<>(userService.register(request), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> userLogin(@Valid @RequestBody LoginRequest request){
        return new ResponseEntity<>(userService.login(request),HttpStatus.OK);
    }

}
