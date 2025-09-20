package com.example.apms.controller;

import com.example.apms.dto.AnalyticsResponse;
import com.example.apms.service.AnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/analytics")
public class AnalyticsController {

    @Autowired
    private AnalyticsService analyticsService;

    @GetMapping("/projects")
    @PreAuthorize("hasRole('FACULTY') or hasRole('ADMIN')")
    public ResponseEntity<AnalyticsResponse> getProjectAnalytics() {
        AnalyticsResponse analytics = analyticsService.getProjectAnalytics();
        return ResponseEntity.ok(analytics);
    }
}
