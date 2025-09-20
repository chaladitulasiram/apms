package com.example.apms.security;

import com.example.apms.model.Faculty;
import com.example.apms.model.Student;
import com.example.apms.repository.FacultyRepository;
import com.example.apms.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

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
            return new User(student.getEmail(), student.getPassword(), new ArrayList<>());
        }

        // If not found, try to find a faculty member
        Faculty faculty = facultyRepository.findByEmail(email).orElse(null);
        if (faculty != null) {
            return new User(faculty.getEmail(), faculty.getPassword(), new ArrayList<>());
        }

        // If no user is found in either repository
        throw new UsernameNotFoundException("User not found with email: " + email);
    }
}
