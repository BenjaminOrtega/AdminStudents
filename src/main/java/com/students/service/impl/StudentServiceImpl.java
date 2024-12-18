package com.students.service.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;

import org.springframework.stereotype.Service;

import com.students.dto.StudentDto;
import com.students.entity.Message;
import com.students.entity.Student;
import com.students.service.StudentService;
import com.students.utils.Constant;
import com.students.utils.DriveFile;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class StudentServiceImpl implements StudentService{

	private DriveFile driveFile;
	
	public StudentServiceImpl(DriveFile driveFile) {
		this.driveFile = driveFile;
	}
	
	
	@Override
	public StudentDto findAllStudents(){
		StudentDto dto = new StudentDto();
		LinkedList<Student> students = new LinkedList<>();
		try {
			dto.setData(driveFile.findAll());
			dto.setMessage(Message.builder()
					.code(Constant.CODE_SUSSES)
					.message(Constant.MESSAGE_SUSSES_FIND)
					.build());
		} catch (FileNotFoundException e) {
			dto.setData(students);
			dto.setMessage(Message.builder()
					.code(Constant.CODE_ERROR_FIND_FILE)
					.message(Constant.MESSAGE_ERROR_FIND_FILE)
					.build());
			log.error(e);
		} catch (IOException e) {
			dto.setData(students);
			dto.setMessage(Message.builder()
					.code(Constant.CODE_ERROR_MANIPULATION_FILE)
					.message(Constant.MESSAGE_ERROR_MANIPULATION_FILE)
					.build());
			log.error(e);
		}
		return dto;
	}
	
	@Override
	public StudentDto findStudentByName(String name){
		StudentDto dto = new StudentDto();
		LinkedList<Student> students = findAllStudents().getData();
		LinkedList<Student> studentsByName = new LinkedList<>();
		students.forEach(s -> {
			if (s.getNombre().toLowerCase().equals(name.toLowerCase())) {
				
				studentsByName.add(Student.builder()
						.apellidoMaterno(s.getApellidoMaterno())
						.apellidoPaterno(s.getApellidoPaterno())
						.nombre(s.getNombre())
						.matricula(s.getMatricula())
						.build());
			}
			
			dto.setData(studentsByName);
			dto.setMessage(Message.builder()
					.code(Constant.CODE_SUSSES)
					.message(Constant.MESSAGE_SUSSES_FIND_BY_NAME.concat(name))
					.build());
			 
		});
		
		return dto;
	}
	
	@Override
	public StudentDto deleteStudent(String matri) {
		StudentDto dto = new StudentDto();
		LinkedList<Student> students = findAllStudents().getData();
		Student studentfind = findStudentByMatricula(matri);
		log.info("Antes de Eliminar al alumno: {"+students.toString()+"}");
		if (students.isEmpty()) {
			dto.setData(new LinkedList<>());
			dto.setMessage(Message.builder()
					.code(Constant.CODE_SUSSES)
					.message(Constant.MESSAGE_ERROR_DELETE)
					.build());
		}else if(studentfind.getMatricula() == null){
			dto.setData(new LinkedList<>());
			dto.setMessage(Message.builder()
					.code(Constant.CODE_SUSSES)
					.message(Constant.MESSAGE_ERROR_DELETE)
					.build());
		}else {
			students.remove(studentfind);
			log.info("Despues de Eliminar al alumno: {"+students.toString()+"}");
			driveFile.writeFile(students);
			dto.setData(new LinkedList<>());
			dto.setMessage(Message.builder()
					.code(Constant.CODE_SUSSES)
					.message(Constant.MESSAGE_SUSSES_DELETE)
					.build());
		}
		return dto;
	}
	
	@Override
	public StudentDto insertStudent(Student student) {
		StudentDto dto = new StudentDto();
		
		if (!driveFile.existFile())
			driveFile.createFile();
			
		LinkedList<Student> students = findAllStudents().getData();
		log.info("Antes de Insertar al alumno: {"+students.toString()+"}");
		students.add(student);
		Message m = driveFile.writeFile(students);
		
		if (m.getCode().equals(Constant.MESSAGE_ERROR_WRITE_FILE)) 
			dto.setMessage(m);
		else
			m.setCode(Constant.CODE_SUSSES);
			m.setMessage(Constant.MESSAGE_SUSSES_INSERT);
			dto.setMessage(m);
			log.info("Despues de Insertar al alumno: {"+students.toString()+"}");
		
		return dto;
		
	}
	
	@Override
	public StudentDto updateStudent(String code, Student student) {
		StudentDto dto = new StudentDto();
		if (driveFile.existFile()) {
			LinkedList<Student> students = findAllStudents().getData();
			Student studentfind = findStudentByMatricula(code);
			log.info("Antes de Actualizar al alumno: {"+students.toString()+"}");
			if (students.contains(studentfind)) {
				int index = students.indexOf(studentfind);
				studentfind.setApellidoMaterno(student.getApellidoMaterno());
				studentfind.setApellidoPaterno(student.getApellidoPaterno());
				studentfind.setNombre(student.getNombre());
				
				students.set(index, studentfind);
				
				Message m = driveFile.writeFile(students);
				
				if (m.getCode().equals(Constant.MESSAGE_ERROR_WRITE_FILE)) 
					dto.setMessage(m);
				else
					m.setCode(Constant.CODE_SUSSES);
					m.setMessage(Constant.MESSAGE_SUSSES_UPDATE);
					dto.setMessage(m);
					log.info("Despues de Actualizar al alumno: {"+students.toString()+"}");
			}
			
		}else {
			dto.setMessage(Message.builder()
					.code(Constant.CODE_ERROR)
					.message(Constant.MESSAGE_ERROR_EXIST_FILE)
					.build());
		}
		return dto;
	}
	
	public Student findStudentByMatricula(String matri) {
			
			LinkedList<Student> students = findAllStudents().getData();
			
			Student student = new Student();
			
			students.forEach(s -> {
				if (s.getMatricula().equals(matri)) {
					student.setApellidoMaterno(s.getApellidoMaterno());
					student.setApellidoPaterno(s.getApellidoPaterno());
					student.setNombre(s.getNombre());
					student.setMatricula(s.getMatricula());
				}
				 
			});
			
			return student;
		}
	
}
