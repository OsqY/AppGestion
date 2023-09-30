package com.cursoSpring.cursoSpring.service;

import com.cursoSpring.cursoSpring.model.Project;
import com.cursoSpring.cursoSpring.model.User;
import com.cursoSpring.cursoSpring.repository.ProjectRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    @PersistenceContext
    EntityManager entityManager;
    private ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }
    public Project createProject(Project project){
        List<User> listUsers = entityManager.createQuery("SELECT email FROM User").getResultList();
        if (listUsers.contains(project.getUserForProject())){
            return projectRepository.save(project);
        }
        return null;
    }
    public List<Project> getProjects(){
        return projectRepository.findAll();
    }
    public Project getProjectById(Long id){
        return projectRepository.findById(id).orElse(null);
    }
    public Project updateProject(Project updateProject) {
        Optional<Project> optionalExistingProject = projectRepository.findById(updateProject.getId());

        if (optionalExistingProject.isPresent()) {
            Project existingProject = optionalExistingProject.get();
            existingProject.setProjectName(updateProject.getProjectName());
            existingProject.setUserForProject(updateProject.getUserForProject());
            existingProject.setPriority(updateProject.getPriority());
            existingProject.setStartDate(updateProject.getStartDate());
            existingProject.setEndDate(updateProject.getEndDate());

            Project updatedProject = projectRepository.save(existingProject);

        }
        return null;
    }

    public void deleteProject(Long id){
        projectRepository.deleteById(id);
    }
}
