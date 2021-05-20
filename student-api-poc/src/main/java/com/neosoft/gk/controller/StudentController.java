package com.neosoft.gk.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.neosoft.gk.fileuploadutil.FileUploadUtil;
import com.neosoft.gk.model.Project;
import com.neosoft.gk.model.Student;
import com.neosoft.gk.repository.ProjectRepository;
import com.neosoft.gk.repository.StudentRepository;
import com.neosoft.gk.resultmodel.ResultModel;
import com.neosoft.gk.service.StudentService;

@RestController/*for testing purpose only*/ 
//@Controller /*for checking thymeleaf pages only*/
/*put @RestController or @Controller at a time*/
public class StudentController {
	@GetMapping("")
	public String viewHomePage() {
		return "index";
	}
	
	@Autowired
	private StudentRepository studentRepo;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private ProjectRepository projectRepo;
	
	@GetMapping("/students/new")
	public String showCreateNewStudentForm(Model model) {
		List<Project> listProjects=projectRepo.findAll();
		model.addAttribute("listProjects",listProjects);
		model.addAttribute("student",new Student());
		return "student_form";
	}
	
	@PostMapping("/students/save")
	public String saveStudent(Student student,@RequestParam("image") MultipartFile multipartFile) throws IOException {
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        student.setPhotos(fileName);
        Student savedStudent =studentRepo.save(student);
        String uploadDir = "student-photos/" + savedStudent.getStudentId();
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
		return "redirect:/students";
	}
	
	@GetMapping("/students")
	public String listStudents(Model model) {
		List<Student> listStudents=studentRepo.findAll();
		model.addAttribute("listStudents",listStudents);
		return "students";
		
	}
	
	@GetMapping("/students/edit/{id}")
	public String showEditStudentForm(@PathVariable("id") Integer id,Model model) {
		
		List<Project> listProjects=projectRepo.findAll();
		Student student =studentRepo.findById(id).get();

		model.addAttribute("listProjects",listProjects);
		model.addAttribute("student",student);

		return "student_form";
		
	}
	@GetMapping("/students/delete/{id}")
	public String showDeleteStudentForm(@PathVariable("id") Integer id,Model model) {
	studentRepo.deleteById(id);
	return "redirect:/students";
	}
	
	
	//==============================================
	@GetMapping("/studentstest")
	public ResultModel getAllStudentsTest(){
		return studentService.getStudentsByTest();
	}
	
	@PostMapping("/studenttest")
	public ResultModel createStudentTest(@Valid @RequestBody Student student) {
		return studentService.createStudentTest(student);
	}
	
	// get employee by id rest api
	@GetMapping("/studenttest/{id}")
	public ResultModel getStudentByIdTest(@Valid @PathVariable Integer id) {
		return studentService.getStudentByIdTest(id);
	}

	@PutMapping("/studenttest/{id}")
	public ResultModel updateStudentTest(@Valid @PathVariable Integer id,@RequestBody Student employeeDetails){
		return studentService.updateStudentTest(employeeDetails, id);
	}
	
	// delete employee rest api
	@DeleteMapping("/studenttest/{id}")
	public ResultModel deleteStudentTest(@Valid @PathVariable Integer id){
		return studentService.deleteStudentTest(id);
	}
	
}
