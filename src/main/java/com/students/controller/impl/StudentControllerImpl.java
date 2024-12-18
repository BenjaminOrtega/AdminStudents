package com.students.controller.impl;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.students.controller.StudentController;
import com.students.dto.StudentDto;
import com.students.entity.Student;
import com.students.service.StudentService;

@RestController
@RequestMapping("/v1")
public class StudentControllerImpl implements StudentController{
	
	private final StudentService studentService;

	public StudentControllerImpl(StudentService studentService) {
		this.studentService = studentService;
	}

	@Override
	public ResponseEntity<StudentDto> findStudentByName(String name) {
		// TODO Auto-generated method stub
		return new ResponseEntity<StudentDto>(studentService.findStudentByName(name), HttpStatusCode.valueOf(200));
	}

	@Override
	public ResponseEntity<StudentDto> findAllStudents() {
		return  new ResponseEntity<StudentDto>(studentService.findAllStudents(), HttpStatusCode.valueOf(200));
	}

	@Override
	public ResponseEntity<StudentDto> deleteStudent(String code) {
		// TODO Auto-generated method stub
		return new ResponseEntity<StudentDto>(studentService.deleteStudent(code), HttpStatusCode.valueOf(200));
	}

	@Override
	public ResponseEntity<StudentDto> insertStudent(Student student) {
		// TODO Auto-generated method stub
		return new ResponseEntity<StudentDto>(studentService.insertStudent(student), HttpStatusCode.valueOf(200));
	}

	@Override
	public ResponseEntity<StudentDto> updateStudent(String code, Student student) {
		// TODO Auto-generated method stub
		return new ResponseEntity<StudentDto>(studentService.updateStudent(code,student), HttpStatusCode.valueOf(200));
	}

}
