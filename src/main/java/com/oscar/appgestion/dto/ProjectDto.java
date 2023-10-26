package com.oscar.appgestion.dto;

import com.oscar.appgestion.model.ProjectPriority;
import com.oscar.appgestion.model.ProjectStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDto {
    private int id;
    private String projectName;
    private String description;
    private Date startDate;
    private Date endDate;
    private ProjectPriority projectPriority;
    private ProjectStatus status;

}