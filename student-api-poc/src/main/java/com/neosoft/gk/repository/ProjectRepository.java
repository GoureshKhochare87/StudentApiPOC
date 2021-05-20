package com.neosoft.gk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neosoft.gk.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Integer>{
	
}
