package com.example.apms.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

// Data Transfer Object for authentication responses
@Getter
@Setter
@AllArgsConstructor
public class AuthResponse {
    private String token;
}
