package com.example.apms.service;

import com.example.apms.model.Evaluation;
import com.example.apms.model.Faculty;
import com.example.apms.model.Project;
import com.example.apms.repository.EvaluationRepository;
import com.example.apms.repository.FacultyRepository;
import com.example.apms.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EvaluationService {

    @Autowired
    private EvaluationRepository evaluationRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private FacultyRepository facultyRepository;

    public Evaluation createEvaluation(Long projectId, String facultyEmail, Evaluation evaluationDetails) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found with id: " + projectId));
        Faculty faculty = facultyRepository.findByEmail(facultyEmail)
                .orElseThrow(() -> new RuntimeException("Faculty not found with email: " + facultyEmail));

        evaluationDetails.setProject(project);
        evaluationDetails.setFaculty(faculty);
        evaluationDetails.setEvaluationDate(LocalDate.now());

        return evaluationRepository.save(evaluationDetails);
    }

    public List<Evaluation> getEvaluationsForProject(Long projectId) {
        // This is the corrected method call
        return evaluationRepository.findByProjectProjectId(projectId);
    }
}

