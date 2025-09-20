package com.example.apms.controller;

import com.example.apms.model.Faculty;
import com.example.apms.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/faculty")
public class FacultyController {

    @Autowired
    private FacultyService facultyService;

    @PostMapping
    public Faculty createFaculty(@RequestBody Faculty faculty) {
        return facultyService.createFaculty(faculty);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'FACULTY', 'STUDENT')")
    public List<Faculty> getAllFaculty() {
        return facultyService.getAllFaculty();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'FACULTY')")
    public ResponseEntity<Faculty> getFacultyById(@PathVariable Long id) {
        return facultyService.getFacultyById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('FACULTY') and #id == principal.id)")
    public ResponseEntity<Faculty> updateFaculty(@PathVariable Long id, @RequestBody Faculty facultyDetails) {
        try {
            return ResponseEntity.ok(facultyService.updateFaculty(id, facultyDetails));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteFaculty(@PathVariable Long id) {
        try {
            facultyService.deleteFaculty(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

