package com.example.apms.controller;

import com.example.apms.model.Evaluation;
import com.example.apms.service.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/evaluations")
public class EvaluationController {

    @Autowired
    private EvaluationService evaluationService;

    @PostMapping("/project/{projectId}/faculty/{facultyId}")
    @PreAuthorize("hasRole('FACULTY')")
    public ResponseEntity<Evaluation> createEvaluation(@PathVariable Long projectId,
                                                       @PathVariable Long facultyId,
                                                       @RequestBody Evaluation evaluation) {
        return ResponseEntity.ok(evaluationService.createEvaluation(projectId, facultyId, evaluation));
    }

    @GetMapping("/project/{projectId}")
    @PreAuthorize("hasAnyRole('FACULTY', 'STUDENT')")
    public ResponseEntity<List<Evaluation>> getEvaluationsForProject(@PathVariable Long projectId) {
        return ResponseEntity.ok(evaluationService.getEvaluationsForProject(projectId));
    }
}

