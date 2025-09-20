package com.example.apms.repository;

import com.example.apms.model.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    // This method signature is crucial for the login service to find faculty by email
    Optional<Faculty> findByEmail(String email);
}

