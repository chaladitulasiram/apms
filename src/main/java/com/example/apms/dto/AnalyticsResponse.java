package com.example.apms.dto;

import com.example.apms.model.ProjectStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class AnalyticsResponse {
    private long totalProjects;
    private Map<ProjectStatus, Long> projectsByStatus;
    private Map<String, Long> projectsByDepartment;
}
