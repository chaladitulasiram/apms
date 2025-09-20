package com.example.apms.service;

import com.example.apms.model.Faculty;
import com.example.apms.repository.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacultyService {

    @Autowired
    private FacultyRepository facultyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Faculty createFaculty(Faculty faculty) {
        // Encode the password. The role is already set by default in the Faculty entity.
        faculty.setPassword(passwordEncoder.encode(faculty.getPassword()));
        return facultyRepository.save(faculty);
    }

    public Optional<Faculty> getFacultyById(Long id) {
        return facultyRepository.findById(id);
    }

    public List<Faculty> getAllFaculty() {
        return facultyRepository.findAll();
    }

    public Faculty updateFaculty(Long id, Faculty facultyDetails) {
        Faculty faculty = facultyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Faculty not found with id: " + id));

        faculty.setName(facultyDetails.getName());
        faculty.setEmail(facultyDetails.getEmail());
        faculty.setDepartment(facultyDetails.getDepartment());
        faculty.setDesignation(facultyDetails.getDesignation());

        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(Long id) {
        if (!facultyRepository.existsById(id)) {
            throw new RuntimeException("Faculty not found with id: " + id);
        }
        facultyRepository.deleteById(id);
    }
}

