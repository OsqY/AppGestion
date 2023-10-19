package com.oscar.appgestion.controllers;

import com.oscar.appgestion.model.Project;
import com.oscar.appgestion.service.ProjectService;
import com.oscar.appgestion.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/projects")
public class ProjectController {
    private final ProjectService projectService;

    private final JWTUtil jwtUtil;

    public boolean validateToken(String token) {
        String userId = jwtUtil.getKey(token);
        return userId != null;
    }

    @Autowired
    public ProjectController(ProjectService projectService, JWTUtil jwtUtil) {
        this.projectService = projectService;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping("/")
    public List<Project> getAllProjects(@RequestHeader(value = "Authorization") String token) {
        if (!validateToken(token)) {
            return null;
        }
        return projectService.getProjects();
    }
    @GetMapping("/{id}")
    public Project getProjectById(@PathVariable Long id) {
        return projectService.getProjectById(id);
    }
    @DeleteMapping("/{id}")
    public void deleteProject(@RequestHeader(value = "Authorization") String token, @PathVariable Long id) {
        if (!validateToken(token)) {
            return;
        }
        projectService.deleteProject(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Project> updateProject(@RequestHeader(value = "Authorization") String token,
                                                 @PathVariable Long id, @RequestBody Project project) {
        if (!validateToken(token)) {
            return ResponseEntity.status(401).build();
        }
        project.setId(id);
        return ResponseEntity.ok(projectService.updateProject(project));
    }

    @PatchMapping("/en_progreso/{id}")
    public ResponseEntity<Project> setProjectStatusToInProgress(@RequestHeader(value = "Authorization") String token,
                                                                @PathVariable Long id, @RequestBody Project project) {
        if (!validateToken(token)) {
            return ResponseEntity.status(401).build();
        }
        project.setId(id);
        return ResponseEntity.ok(projectService.updateProjectStatusToInProgress(project));
    }

    @PatchMapping("/completado/{id}")
    public ResponseEntity<Project> setProjectStatusToCompleted(@RequestHeader(value = "Authorization") String token,
                                                               @PathVariable Long id, @RequestBody Project project) {
        if (!validateToken(token)) {
            return ResponseEntity.status(401).build();
        }
        project.setId(id);
        return ResponseEntity.ok(projectService.updateProjectStatusToCompleted(project));
    }


    @PostMapping("/create")
    public Project createProject(@RequestHeader(value = "Authorization") String token, @RequestBody Project project) {
        if (project.getPriority() != null && (project.getPriority() == Project.ProjectPriority.BAJA ||
                project.getPriority() == Project.ProjectPriority.MEDIA || project.getPriority() == Project.ProjectPriority.ALTA)
        && (!validateToken(token))) {
            return null;
        } else {
            return projectService.createProject(project);
        }
    }
}
