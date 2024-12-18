package com.students.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.students.dto.StudentDto;
import com.students.entity.Student;

public interface StudentController {
	
	@GetMapping("/students/{name}")
	ResponseEntity<StudentDto> findStudentByName(@PathVariable String name);
	
	@GetMapping("/students")
	ResponseEntity<StudentDto> findAllStudents();
	
	@DeleteMapping("/student/{code}")
	ResponseEntity<StudentDto> deleteStudent(@PathVariable String code);
	
	@PostMapping("/student")
	ResponseEntity<StudentDto> insertStudent(@RequestBody Student student);
	
	@PutMapping("/student/{code}")
	ResponseEntity<StudentDto> updateStudent(@PathVariable String code ,@RequestBody Student student);
}
