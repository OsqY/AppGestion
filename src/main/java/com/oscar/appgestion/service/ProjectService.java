package com.oscar.appgestion.service;

import com.oscar.appgestion.dto.ProjectDto;
import com.oscar.appgestion.dto.ProjectResponse;
import com.oscar.appgestion.model.ProjectPriority;
import com.oscar.appgestion.model.ProjectStatus;
import org.springframework.stereotype.Service;


@Service
public interface ProjectService {
    ProjectDto createProject(int userId, ProjectDto projectDto);
    ProjectResponse getAllProjects(int pageNo, int pageSize);
    ProjectDto getProjectById(int projectId);
    ProjectDto updateProjectStatus(int userId, int projectId, ProjectStatus projectStatus);
    ProjectDto updateProjectPriority(int userId, int projectId, ProjectPriority projectPriority);
    ProjectDto updateProject(int userId, int projectId, ProjectDto projectDto);
    void deleteProject(int projectId);
}

