package com.cursoSpring.cursoSpring.repository;

import com.cursoSpring.cursoSpring.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
