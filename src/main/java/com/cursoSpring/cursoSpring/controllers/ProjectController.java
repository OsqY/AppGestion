package com.cursoSpring.cursoSpring.controllers;

import com.cursoSpring.cursoSpring.model.Project;
import com.cursoSpring.cursoSpring.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {
    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/")
    public List<Project> getAllProjects() {
        return projectService.getProjects();
    }

    @GetMapping("/{id}")
    public Project getProjectById(@PathVariable Long id) {
        return projectService.getProjectById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable Long id, @RequestBody Project project) {
        project.setId(id);
        return ResponseEntity.ok(projectService.updateProject(project));
    }

    @PatchMapping("/en_progreso/{id}")
    public ResponseEntity<Project> setProjectStatusToInProgress(@PathVariable Long id, @RequestBody Project project) {
        project.setId(id);
        return ResponseEntity.ok(projectService.updateProjectStatusToInProgress(project));
    }

    @PatchMapping("/completado/{id}")
    public ResponseEntity<Project> setProjectStatusToCompleted(@PathVariable Long id, @RequestBody Project project) {
        project.setId(id);
        return ResponseEntity.ok(projectService.updateProjectStatusToCompleted(project));
    }


    @PostMapping("/create")
    public Project createProject(@RequestBody Project project) {
        if (project.getPriority() != null && (project.getPriority() == Project.ProjectPriority.BAJA ||
                project.getPriority() == Project.ProjectPriority.MEDIA || project.getPriority() == Project.ProjectPriority.ALTA)) {
            return projectService.createProject(project);
        } else {
            return null;
        }
    }
}
