package com.example.apms.controller;

import com.example.apms.dto.AuthRequest;
import com.example.apms.dto.AuthResponse;
import com.example.apms.dto.RegisterRequest;
import com.example.apms.model.Faculty;
import com.example.apms.model.Role;
import com.example.apms.model.Student;
import com.example.apms.security.JwtUtil;
import com.example.apms.security.UserDetailsServiceImpl;
import com.example.apms.service.FacultyService;
import com.example.apms.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private StudentService studentService;

    @Autowired
    private FacultyService facultyService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest) {
        if (registerRequest.getRole() == Role.STUDENT) {
            Student student = new Student();
            student.setName(registerRequest.getName());
            student.setEmail(registerRequest.getEmail());
            student.setPassword(registerRequest.getPassword());
            student.setCourse(registerRequest.getCourse());
            student.setYear(registerRequest.getYear());
            studentService.createStudent(student);
            return ResponseEntity.status(HttpStatus.CREATED).body("Student registered successfully!");
        } else if (registerRequest.getRole() == Role.FACULTY) {
            Faculty faculty = new Faculty();
            faculty.setName(registerRequest.getName());
            faculty.setEmail(registerRequest.getEmail());
            faculty.setPassword(registerRequest.getPassword());
            faculty.setDepartment(registerRequest.getDepartment());
            faculty.setDesignation(registerRequest.getDesignation());
            facultyService.createFaculty(faculty);
            return ResponseEntity.status(HttpStatus.CREATED).body("Faculty registered successfully!");
        } else {
            return ResponseEntity.badRequest().body("Invalid role specified.");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect email or password", e);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getEmail());
        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthResponse(jwt));
    }
}

