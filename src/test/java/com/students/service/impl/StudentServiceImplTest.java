package com.students.service.impl;


import java.io.IOException;
import java.util.LinkedList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.students.Data;
import com.students.dto.StudentDto;
import com.students.entity.Student;
import com.students.utils.DriveFile;

import lombok.extern.log4j.Log4j2;

@Log4j2
class StudentServiceImplTest {
	@InjectMocks
	private StudentServiceImpl serviceImpl;
	
	@Mock
	private DriveFile driveFile;
	

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		serviceImpl = new StudentServiceImpl(driveFile);
	}

	@Test
	void findAllStudentsTest() throws IOException {
		LinkedList<Student> linkedList = new LinkedList<>();
		linkedList.add(Data.STUDENT_ONE);
		linkedList.add(Data.STUDENT_TWO);
		linkedList.add(Data.STUDENT_THREE);
		linkedList.add(Data.STUDENT_FOUR);
		
		Mockito.when(driveFile.findAll()).thenReturn(linkedList);
		
		StudentDto students = serviceImpl.findAllStudents();
		
		log.info(students.toString());
		
		Assertions.assertNotNull(students);
		
	}
	
	@Test
	void findStudentByNameTest() throws IOException {
		LinkedList<Student> linkedList = new LinkedList<>();
		linkedList.add(Data.STUDENT_ONE);
		linkedList.add(Data.STUDENT_TWO);
		linkedList.add(Data.STUDENT_THREE);
		linkedList.add(Data.STUDENT_FOUR);
		
		Mockito.when(driveFile.findAll()).thenReturn(linkedList);
		
		StudentDto students = serviceImpl.findStudentByName("Nanzi");
		
		log.info(students.toString());
		
		Assertions.assertNotNull(students);
		
	}
	
	@Test
	void deleteStudent() throws IOException {
		LinkedList<Student> linkedList = new LinkedList<>();
		linkedList.add(Data.STUDENT_ONE);
		linkedList.add(Data.STUDENT_TWO);
		linkedList.add(Data.STUDENT_THREE);
		linkedList.add(Data.STUDENT_FOUR);
		
		Mockito.when(driveFile.findAll()).thenReturn(linkedList);
		
		StudentDto students = serviceImpl.deleteStudent("9089909019");
		
		Mockito.when(driveFile.writeFile(linkedList)).thenReturn(Data.MESSAGE_FILE);
		
		log.info(students.toString());
		
		Assertions.assertNotNull(students);
		
	}
	
	@Test
	void insertStudentTest() throws IOException {
		LinkedList<Student> linkedList = new LinkedList<>();
		linkedList.add(Data.STUDENT_ONE);
		linkedList.add(Data.STUDENT_TWO);
		linkedList.add(Data.STUDENT_THREE);
		linkedList.add(Data.STUDENT_FOUR);
		
		Mockito.when(driveFile.findAll()).thenReturn(linkedList);
		
		Mockito.when(driveFile.writeFile(linkedList)).thenReturn(Data.MESSAGE_FILE);
		
		StudentDto students = serviceImpl.insertStudent(Data.STUDENT_FIVE);
		
		log.info(students.toString());
		
		Assertions.assertNotNull(students);
		
	}
	
	@Test
	void updateStudentTest() throws IOException {
		LinkedList<Student> linkedList = new LinkedList<>();
		linkedList.add(Data.STUDENT_ONE);
		linkedList.add(Data.STUDENT_TWO);
		linkedList.add(Data.STUDENT_THREE);
		linkedList.add(Data.STUDENT_FOUR);
		
		Mockito.when(driveFile.findAll()).thenReturn(linkedList);
		
		Mockito.when(driveFile.existFile()).thenReturn(true);
		
		Mockito.when(driveFile.writeFile(linkedList)).thenReturn(Data.MESSAGE_FILE);
		
		StudentDto students = serviceImpl.updateStudent("9089909021",Data.STUDENT_THREE_UPDATE);
		
		log.info(students.toString());
		
		Assertions.assertNotNull(students);
		
	}
	
	@Test
	void findStudentByMatriculaTest() throws IOException {
		LinkedList<Student> linkedList = new LinkedList<>();
		linkedList.add(Data.STUDENT_ONE);
		linkedList.add(Data.STUDENT_TWO);
		linkedList.add(Data.STUDENT_THREE);
		linkedList.add(Data.STUDENT_FOUR);
		
		Mockito.when(driveFile.findAll()).thenReturn(linkedList);
		
		Mockito.when(driveFile.existFile()).thenReturn(true);
		
		Mockito.when(driveFile.writeFile(linkedList)).thenReturn(Data.MESSAGE_FILE);
		
		Student student = serviceImpl.findStudentByMatricula("9089909021");
		
		log.info("Estudiante encontrado por la matricula 9089909021: {"+student.toString()+"}");
		
		Assertions.assertNotNull(student);
		
	}

}
