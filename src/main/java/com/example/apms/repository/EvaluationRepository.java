package com.example.apms.repository;

import com.example.apms.model.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {

    /**
     * Finds all evaluations for a given project by its ID.
     * The method name tells Spring Data JPA to look for the 'project' property in the Evaluation entity,
     * and then look for the 'projectId' property within that Project entity.
     * @param projectId The ID of the project.
     * @return A list of evaluations for the specified project.
     */
    List<Evaluation> findByProjectProjectId(Long projectId);
}

