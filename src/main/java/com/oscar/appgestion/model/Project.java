package com.oscar.appgestion.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "useremail")
    private String userForProject;

    @Column(name = "projectname")
    private String projectName;

    @Column(name = "description")
    private String description;

    public enum ProjectPriority {
        BAJA, MEDIA, ALTA
    }

    @Column(name = "priority")
    private ProjectPriority priority;

    @Column(name = "startdate")
    private Date startDate;

    @Column(name = "enddate")
    private Date endDate;

    public enum ProjectStatus {
        PENDIENTE, ENPROGRESO, COMPLETADO
    }

    @Column(name = "status")
    private ProjectStatus status;

}
