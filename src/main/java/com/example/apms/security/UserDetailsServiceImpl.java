package com.example.apms.security;

import com.example.apms.model.Admin; // Import this
import com.example.apms.model.Faculty;
import com.example.apms.model.Student;
import com.example.apms.repository.AdminRepository; // Import this
import com.example.apms.repository.FacultyRepository;
import com.example.apms.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private FacultyRepository facultyRepository;

    @Autowired
    private AdminRepository adminRepository; // Add this

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // 1. Check Student
        Student student = studentRepository.findByEmail(email).orElse(null);
        if (student != null) {
            return new User(student.getEmail(), student.getPassword(),
                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + student.getRole().name())));
        }

        // 2. Check Faculty
        Faculty faculty = facultyRepository.findByEmail(email).orElse(null);
        if (faculty != null) {
            return new User(faculty.getEmail(), faculty.getPassword(),
                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + faculty.getRole().name())));
        }

        // 3. Check Admin
        Admin admin = adminRepository.findByEmail(email).orElse(null);
        if (admin != null) {
            return new User(admin.getEmail(), admin.getPassword(),
                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + admin.getRole().name())));
        }

        throw new UsernameNotFoundException("User not found with email: " + email);
    }
}