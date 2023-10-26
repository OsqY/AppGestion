package com.oscar.appgestion.service.impl;

import com.oscar.appgestion.dto.ProjectDto;
import com.oscar.appgestion.dto.ProjectResponse;
import com.oscar.appgestion.exceptions.ProjectNotFoundException;
import com.oscar.appgestion.exceptions.UserNotFoundException;
import com.oscar.appgestion.model.ProjectEntity;
import com.oscar.appgestion.model.ProjectPriority;
import com.oscar.appgestion.model.ProjectStatus;
import com.oscar.appgestion.model.UserEntity;
import com.oscar.appgestion.repository.ProjectRepository;
import com.oscar.appgestion.repository.UserEntityRepository;
import com.oscar.appgestion.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final UserEntityRepository userEntityRepository;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository, UserEntityRepository userEntityRepository) {
        this.projectRepository = projectRepository;
        this.userEntityRepository = userEntityRepository;
    }

    @Override
    public ProjectDto createProject(int userId, ProjectDto projectDto){
        ProjectEntity project = mapToEntity(projectDto);
        UserEntity user = userEntityRepository.findById(userId).
                orElseThrow(() -> new UsernameNotFoundException("User not found for project"));
        project.setUser(user);
        ProjectEntity savedProject = projectRepository.save(project);
        return mapToDto(savedProject);
    }

    @Override
    public ProjectResponse getAllProjects(int pageNo, int pageSize) {

        PageRequest pageable =  PageRequest.of(pageNo, pageSize);
        Page<ProjectEntity> projects = projectRepository.findAll(pageable);
        List<ProjectEntity> projectList = projects.getContent();
        List<ProjectDto> content = projectList.stream().map(this::mapToDto).collect(Collectors.toList());

        ProjectResponse projectResponse = new ProjectResponse();
        projectResponse.setContent(content);
        projectResponse.setPageNo(projects.getNumber());
        projectResponse.setPageSize(projects.getSize());
        projectResponse.setTotalElements(projects.getTotalElements());
        projectResponse.setTotalPages(projects.getTotalPages());
        projectResponse.setLast(projects.isLast());

        return projectResponse;
    }

    @Override
    public ProjectDto getProjectById(int projectId) {
        ProjectEntity project = projectRepository.findById(projectId).
                orElseThrow(() -> new ProjectNotFoundException("Project not found!"));
        return mapToDto(project);
        }

    @Override
    public ProjectDto updateProjectStatus(int userId, int projectId, ProjectStatus projectStatus) {
        UserEntity user =  userEntityRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found for project"));
        ProjectEntity project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException("Project not found to update it!"));
        if (userId != user.getId()) {
            throw new UserNotFoundException("User not found for project");
        }
        project.setStatus(projectStatus);
        ProjectEntity updatedProject = projectRepository.save(project);
        return mapToDto(updatedProject);
    }

    @Override
    public ProjectDto updateProjectPriority(int userId, int projectId, ProjectPriority projectPriority) {
        UserEntity user =  userEntityRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found for project"));
        ProjectEntity project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException("Project not found to update it!"));
        if (userId != user.getId()) {
            throw new UserNotFoundException("User not found for project");
        }
        project.setProjectPriority(projectPriority);
        ProjectEntity updatedProject = projectRepository.save(project);
        return mapToDto(updatedProject);
    }


    @Override
    public ProjectDto updateProject(int userId, int projectId, ProjectDto projectDto) {
        UserEntity user = userEntityRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found for project"));
        ProjectEntity project = projectRepository.findById(projectId).
                orElseThrow(() -> new ProjectNotFoundException("Project not found to update it!"));
        if (userId != user.getId()) {
            throw new UserNotFoundException("User not found for project");
        }
        project.setProjectName(projectDto.getProjectName());
        project.setDescription(projectDto.getDescription());
        project.setStartDate(projectDto.getStartDate());
        project.setEndDate(projectDto.getEndDate());
        project.setStatus(projectDto.getStatus());
        project.setProjectPriority(projectDto.getProjectPriority());

        ProjectEntity updatedProject = projectRepository.save(project);
        return mapToDto(updatedProject);
    }

    @Override
    public void deleteProject(int projectId) {
        ProjectEntity project = projectRepository.findById(projectId).
                orElseThrow(() -> new ProjectNotFoundException("Project not found to delete it!"));
        projectRepository.delete(project);
    }

    private ProjectDto mapToDto(ProjectEntity project) {
        ProjectDto projectDto = new ProjectDto();
        setProjectDtoProperties(project, projectDto);
        return projectDto;
    }
    private ProjectEntity mapToEntity(ProjectDto projectDto) {
        ProjectEntity project = new ProjectEntity();
        setProjectEntityProperties(projectDto, project);
        return project;
    }


    private static void setProjectEntityProperties(ProjectDto projectDto, ProjectEntity project) {
        project.setId(projectDto.getId());
        project.setProjectName(projectDto.getProjectName());
        project.setDescription(projectDto.getDescription());
        project.setStartDate(projectDto.getStartDate());
        project.setEndDate(projectDto.getEndDate());
        project.setStatus(projectDto.getStatus());
        project.setProjectPriority(projectDto.getProjectPriority());
    }
    private static void setProjectDtoProperties(ProjectEntity project, ProjectDto projectDto) {
        projectDto.setId(project.getId());
        projectDto.setProjectName(project.getProjectName());
        projectDto.setDescription(project.getDescription());
        projectDto.setStartDate(project.getStartDate());
        projectDto.setEndDate(project.getEndDate());
        projectDto.setProjectPriority(project.getProjectPriority());
        projectDto.setStatus(project.getStatus());
    }
}
