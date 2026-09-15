package com.sadiq.fintech.auth.dto;

import lombok.Data;

@Data
public class LoginResponse {
    private Long userId;
    private String username;
    private String status;
    private String token;
}
