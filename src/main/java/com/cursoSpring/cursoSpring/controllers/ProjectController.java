package com.cursoSpring.cursoSpring.controllers;

import com.cursoSpring.cursoSpring.model.Project;
import com.cursoSpring.cursoSpring.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
<<<<<<< HEAD
public class ProjectController  {
=======
@RequestMapping("/projects")
public class ProjectController {
>>>>>>> a04e22074c94280ec32b52ab5809b01714c48872
    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("api/projects")
    public List<Project> getAllProjects() {
        return projectService.getProjects();
    }

<<<<<<< HEAD
    @GetMapping("api/projects/{id}")
    public Project getProjectById(@PathVariable Long id){
        return projectService.getProjectById(id);
    }

    @DeleteMapping("api/projects/{id}")
    public void deleteProject(@PathVariable Long id){
        projectService.deleteProject(id);
    }

    @PutMapping("api/projects/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable Long id, @RequestBody Project project){
        project.setId(id);
        return ResponseEntity.ok(projectService.updateProject(project));
    }
    @PostMapping("api/projects/create")
    public Project createProject(@RequestBody Project project){
        if (project.getPriority() != null && (project.getPriority() == Project.ProjectPriority.BAJA ||
        project.getPriority() == Project.ProjectPriority.MEDIA || project.getPriority() == Project.ProjectPriority.ALTA)){
=======
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
>>>>>>> a04e22074c94280ec32b52ab5809b01714c48872
            return projectService.createProject(project);
        } else {
            return null;
        }
    }
}
