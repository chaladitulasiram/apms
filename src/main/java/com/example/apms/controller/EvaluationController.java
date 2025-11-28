package com.example.apms.controller;

import com.example.apms.model.Evaluation;
import com.example.apms.service.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import java.security.Principal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/evaluations")
public class EvaluationController {

    @Autowired
    private EvaluationService evaluationService;

    @PostMapping("/project/{projectId}")
    public ResponseEntity<Evaluation> createEvaluation(@PathVariable Long projectId,
                                                       @RequestBody Evaluation evaluation,
                                                       Principal principal) {
        // principal.getName() provides the email of the logged-in faculty member
        return ResponseEntity.ok(evaluationService.createEvaluation(projectId, principal.getName(), evaluation));
    }

    @GetMapping("/project/{projectId}")
    @PreAuthorize("hasAnyRole('FACULTY', 'STUDENT')")
    public ResponseEntity<List<Evaluation>> getEvaluationsForProject(@PathVariable Long projectId) {
        return ResponseEntity.ok(evaluationService.getEvaluationsForProject(projectId));
    }
}

