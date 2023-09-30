package com.cursoSpring.cursoSpring.controllers;

import com.cursoSpring.cursoSpring.model.Project;
import com.cursoSpring.cursoSpring.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController  {
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
    public Project getProjectById(@PathVariable Long id){
        return projectService.getProjectById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable Long id){
        projectService.deleteProject(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable Long id, @RequestBody Project project){
        project.setId(id);
        if (project != null){
            return ResponseEntity.ok(projectService.updateProject(project));
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/create")
    public Project createProject(@RequestBody Project project){
        if (project.getPriority() != null && (project.getPriority() == Project.ProjectPriority.LOW ||
        project.getPriority() == Project.ProjectPriority.MEDIUM || project.getPriority() == Project.ProjectPriority.HIGH)){
            return projectService.createProject(project);
        } else {
            return null;
        }
    }
}
