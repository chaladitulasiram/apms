package com.example.apms.security;

import com.example.apms.model.Faculty;
import com.example.apms.model.Student;
import com.example.apms.repository.FacultyRepository;
import com.example.apms.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.authority.SimpleGrantedAuthority; // Add this
import java.util.Collections;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private FacultyRepository facultyRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // First, try to find a student with the given email
        Student student = studentRepository.findByEmail(email).orElse(null);
        if (student != null) {
            // Return UserDetails with the STUDENT role
            return new User(student.getEmail(), student.getPassword(),
                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + student.getRole().name())));
        }

        // If not found, try to find a faculty member
        Faculty faculty = facultyRepository.findByEmail(email).orElse(null);
        if (faculty != null) {
            // Return UserDetails with the FACULTY role
            return new User(faculty.getEmail(), faculty.getPassword(),
                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + faculty.getRole().name())));
        }

        // If no user is found in either repository
        throw new UsernameNotFoundException("User not found with email: " + email);
    }
}

