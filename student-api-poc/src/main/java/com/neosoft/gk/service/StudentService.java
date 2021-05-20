package com.neosoft.gk.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neosoft.gk.model.Student;
import com.neosoft.gk.repository.StudentRepository;
import com.neosoft.gk.resultmodel.ResultModel;


@Service
public class StudentService {
	@Autowired
	private StudentRepository studentRepository;
	
	public ResultModel createStudentTest(Student student){
		studentRepository.save(student);
		return new ResultModel("Student with Name : "+student.getFirstName()+" and ID: "+student.getStudentId()+" saved ",Boolean.TRUE,"Success");
	}
	public ResultModel getStudentsByTest() {
		List<Student> students=studentRepository.findAll();
		if(students.size()==0) {
			return new ResultModel("No Student were Found",Boolean.FALSE,"Failed");
		}
		else {
			return new ResultModel("Number of Student were Found are "+students.size(),Boolean.TRUE,"Success");
		}
	}


	public ResultModel getStudentByIdTest(Integer studentId) {
		List<Student> student=studentRepository.findByStudentId(studentId);
		if(student.size()==0) {
			return new ResultModel("No Student with ID: "+studentId+" were Found",Boolean.FALSE,"Failed");
		}
		else {
			return new ResultModel("Number of Student with ID: "+studentId+" were Found are "+student.size(),Boolean.TRUE,"Success");
		}
	}

	public ResultModel updateStudentTest(Student student, Integer studentId) {
		Student studentData=studentRepository.findStudentByStudentId(studentId);
		if(studentData==null) {
			return new ResultModel("No Employee with ID: "+studentId+" were Found",Boolean.FALSE,"Failed");
		}
		else {
			studentData.setFirstName(student.getFirstName());
			studentData.setLastName(student.getLastName());
			studentData.setEmail(student.getEmail());
			studentData.setContact(student.getContact());
			return new ResultModel("Employee with ID: "+studentId+" is Updated Succesfully ",Boolean.TRUE,"Success");
		}
	}
	public ResultModel deleteStudentTest(Integer studentId) {
		Student student=studentRepository.findStudentByStudentId(studentId);
		if(student==null) {
			return new ResultModel("No Employee with ID: "+studentId+" were Found",Boolean.FALSE,"Failed");
		}
		else {
			studentRepository.delete(student);
			return new ResultModel("Employee with ID: "+studentId+" is Deleted Succesfully ",Boolean.TRUE,"Success");
			
		}
	}

}
