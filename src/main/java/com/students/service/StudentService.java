package com.students.service;

import com.students.dto.StudentDto;
import com.students.entity.Student;


public interface StudentService {
	
	StudentDto findStudentByName(String name);
	
	StudentDto findAllStudents();
	
	StudentDto deleteStudent(String matri);
	
	StudentDto insertStudent(Student student);
	
	StudentDto updateStudent(String code, Student student);
	
}
