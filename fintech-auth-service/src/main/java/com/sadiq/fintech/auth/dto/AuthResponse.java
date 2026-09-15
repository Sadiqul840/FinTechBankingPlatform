package com.sadiq.fintech.auth.dto;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class AuthResponse {

    private Long userId;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String status;
    private String token;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
