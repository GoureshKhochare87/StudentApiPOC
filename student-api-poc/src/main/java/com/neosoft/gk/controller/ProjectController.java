package com.neosoft.gk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.neosoft.gk.model.Project;
import com.neosoft.gk.model.Student;
import com.neosoft.gk.repository.ProjectRepository;

@Controller
public class ProjectController {
	@Autowired
	private ProjectRepository repo;
	
	@GetMapping("/projects")
	public String  listProjects(Model model) {
		List<Project> listProjects=repo.findAll();
		model.addAttribute("listProjects",listProjects);
		return "projects";
	}
	
	@GetMapping("/projects/new")
	public String showProjectNewForm(Model model) {
		model.addAttribute("project",new Project());
		return "project_form";
		
	}
	
	@PostMapping("/projects/save")
	public String saveProject(Project project) {
		repo.save(project);
		return "redirect:/projects";
	}
	@GetMapping("/projects/edit/{id}")
	public String showEditProjectForm(@PathVariable("id") Integer id,Model model) {
		
		//List<Project> listProjects=repo.findAll();
		Project project =repo.findById(id).get();

		//model.addAttribute("listProjects",listProjects);
		model.addAttribute("project",project);

		return "project_form";
		
	}
	@GetMapping("/projects/delete/{id}")
	public String showDeleteStudentForm(@PathVariable("id") Integer id,Model model) {
		repo.deleteById(id);
	return "redirect:/projects";
	}
	
}
