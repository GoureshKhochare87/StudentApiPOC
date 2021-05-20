package com.neosoft.gk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neosoft.gk.model.Student;


public interface StudentRepository extends JpaRepository<Student, Integer>{
	List<Student> findByStudentId(Integer studentId);
	public Student findStudentByStudentId(Integer studentId);

}
