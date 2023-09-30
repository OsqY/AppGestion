package com.cursoSpring.cursoSpring.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter @Column(name = "id")
    private long id;
    @Getter @Setter @Column(name = "useremail")
    private String userForProject;
    @Getter @Setter @Column(name = "projectname")
    private String projectName;
    public enum ProjectPriority {
        LOW, MEDIUM, HIGH
    }
    @Getter @Setter @Column(name = "priority")
    private ProjectPriority priority;
    @Getter @Setter @Column(name = "startdate")
    private Date startDate;
    @Getter @Setter @Column(name = "enddate")
    private Date endDate;
}
