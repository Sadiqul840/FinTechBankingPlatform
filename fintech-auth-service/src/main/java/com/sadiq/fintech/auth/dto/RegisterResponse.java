package com.sadiq.fintech.auth.dto;

import lombok.Data;

@Data
public class RegisterResponse {
    private Long userId;
    private String username;
    private String message;
}
