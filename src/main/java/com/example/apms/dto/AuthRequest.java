package com.example.apms.dto;

import lombok.Getter;
import lombok.Setter;

// Data Transfer Object for authentication requests (login)
@Getter
@Setter
public class AuthRequest {
    private String email;
    private String password;
}
