package com.oscar.appgestion.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    @Column(name = "id")
    private long id;
    @Getter
    @Setter
    @Column(name = "useremail")
    private String userForProject;
    @Getter
    @Setter
    @Column(name = "projectname")
    private String projectName;

    @Getter
    @Setter
    @Column(name = "description")
    private String description;

    public enum ProjectPriority {
        BAJA, MEDIA, ALTA
    }

    @Getter
    @Setter
    @Column(name = "priority")
    private ProjectPriority priority;
    @Getter
    @Setter
    @Column(name = "startdate")
    private Date startDate;
    @Getter
    @Setter
    @Column(name = "enddate")
    private Date endDate;

    public enum ProjectStatus {
        PENDIENTE, ENPROGRESO, COMPLETADO
    }

    @Getter
    @Setter
    @Column(name = "status")
    private ProjectStatus status;

    public Project(long id, String userForProject, String projectName, String description, ProjectPriority priority, Date startDate, Date endDate, ProjectStatus status) {
        this.id = id;
        this.userForProject = userForProject;
        this.projectName = projectName;
        this.description = description;
        this.priority = priority;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }
}
