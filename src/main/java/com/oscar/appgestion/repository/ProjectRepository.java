package com.oscar.appgestion.repository;

import com.oscar.appgestion.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
