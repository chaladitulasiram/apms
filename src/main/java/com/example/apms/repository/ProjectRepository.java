package com.example.apms.repository;

import com.example.apms.model.Project;
import com.example.apms.model.ProjectStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    List<Project> findByDomainContainingIgnoreCase(String domain);
    List<Project> findByStatus(ProjectStatus status);
    List<Project> findByFacultyFacultyId(Long facultyId);

    // For Analytics
    Long countByStatus(ProjectStatus status);

    @Query("SELECT p.faculty.department, COUNT(p) FROM Project p WHERE p.faculty.department IS NOT NULL GROUP BY p.faculty.department")
    List<Object[]> countProjectsByDepartment();
}

