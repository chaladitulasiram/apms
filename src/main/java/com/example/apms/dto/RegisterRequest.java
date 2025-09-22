package com.example.apms.dto;

import com.example.apms.model.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {
    private String name;
    private String email;
    private String password;
    private Role role; // User will specify STUDENT or FACULTY

    // Student-specific fields
    private String course;
    private int year;

    // Faculty-specific fields
    private String department;
    private String designation;
}
