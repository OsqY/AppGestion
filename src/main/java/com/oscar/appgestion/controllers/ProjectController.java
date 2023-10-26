package com.oscar.appgestion.controllers;

import com.oscar.appgestion.dto.ProjectDto;
import com.oscar.appgestion.dto.ProjectResponse;
import com.oscar.appgestion.model.ProjectPriority;
import com.oscar.appgestion.model.ProjectStatus;
import com.oscar.appgestion.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/")
public class ProjectController {

    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("projects")
    public ResponseEntity<ProjectResponse> getProjects(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
        return new ResponseEntity<>(projectService.getAllProjects(pageNo, pageSize), HttpStatus.OK);
    }

    @GetMapping("project/{id}")
    public ResponseEntity<ProjectDto> projectDetails(@PathVariable int id) {
        return ResponseEntity.ok(projectService.getProjectById(id));
    }

    @PostMapping("project/{userId}/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ProjectDto> createProject(@PathVariable(value = "userId") int userId, @RequestBody ProjectDto projectDto) {
        return ResponseEntity.ok(projectService.createProject(userId, projectDto));
    }

    @PutMapping("project/{userId}/update/{projectId}")
    public ResponseEntity<ProjectDto> updateProject(@PathVariable("userId") int userId,
                                                    @PathVariable("projectId") int projectId,
                                                    @RequestBody ProjectDto projectDto) {
        return ResponseEntity.ok(projectService.updateProject(userId, projectId, projectDto));
    }

    @PatchMapping("project/{userId}/statusToStatus/{projectId}")
    public ResponseEntity<ProjectDto> updateProjectStatusToCompleted(
            @PathVariable("userId") int userId,
            @PathVariable("projectId") int projectId,
            @RequestParam("Status") ProjectStatus status
    ) {
        return ResponseEntity.ok(projectService.updateProjectStatus(userId, projectId, status));
    }

    @PatchMapping("project/{userId}/priorityToPriority/{projectId}")
    public ResponseEntity<ProjectDto> updateProjectPriority(
            @PathVariable("userId") int userId,
            @PathVariable("projectId") int projectId,
            @RequestParam("Priority") ProjectPriority projectPriority
    ) {
        return ResponseEntity.ok(projectService.updateProjectPriority(userId, projectId, projectPriority));
    }

    @DeleteMapping("project/{id}/delete")
    public ResponseEntity<String> deleteProject(@PathVariable("id") int id) {
        projectService.deleteProject(id);
        return new ResponseEntity<>("Project deleted.", HttpStatus.OK);
    }
}
