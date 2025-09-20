package com.example.apms.service;

import com.example.apms.dto.AnalyticsResponse;
import com.example.apms.model.ProjectStatus;
import com.example.apms.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AnalyticsService {

    @Autowired
    private ProjectRepository projectRepository;

    public AnalyticsResponse getProjectAnalytics() {
        long totalProjects = projectRepository.count();

        // Get counts for each status
        Map<ProjectStatus, Long> projectsByStatus = Arrays.stream(ProjectStatus.values())
                .collect(Collectors.toMap(
                        status -> status,
                        projectRepository::countByStatus
                ));

        // Get counts for each department
        Map<String, Long> projectsByDepartment = projectRepository.countProjectsByDepartment().stream()
                .collect(Collectors.toMap(
                        result -> (String) result[0], // Department name
                        result -> (Long) result[1]    // Count
                ));

        return new AnalyticsResponse(totalProjects, projectsByStatus, projectsByDepartment);
    }
}
