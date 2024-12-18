package com.students.controller.impl;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.LinkedList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.students.Data;
import com.students.dto.StudentDto;
import com.students.entity.Message;
import com.students.entity.Student;
import com.students.service.StudentService;
import com.students.utils.Constant;

import lombok.extern.log4j.Log4j2;

@Log4j2
@WebMvcTest(controllers = StudentControllerImpl.class)
class StudentControllerImplTest {
	
	@Autowired
    private MockMvc mockMvc;
	
	@MockitoBean
	private StudentService service;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void findStudentByNameTest() throws Exception {
		LinkedList<Student> linkedList = new LinkedList<>();
		linkedList.add(Data.STUDENT_ONE);
		
		when(service.findStudentByName("beatriz")).thenReturn(StudentDto.builder()
				.data(linkedList)
				.message(Message.builder()
						.code(Constant.CODE_SUSSES)
						.message(Constant.MESSAGE_SUSSES_FIND_BY_NAME.concat("beatriz"))
						.build())
				.build());
		
		assertThat(mockMvc.perform(get("/v1/students/beatriz"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data[0].nombre").value("Beatriz")));
	}
	
	@Test
	void findAllStudentsTest() throws Exception {
		LinkedList<Student> linkedList = new LinkedList<>();
		linkedList.add(Data.STUDENT_ONE);
		linkedList.add(Data.STUDENT_TWO);
		
		when(service.findAllStudents()).thenReturn(StudentDto.builder()
				.data(linkedList)
				.message(Message.builder()
						.code(Constant.CODE_SUSSES)
						.message(Constant.MESSAGE_SUSSES_FIND)
						.build())
				.build());
		
		assertThat(mockMvc.perform(get("/v1/students"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data[0].nombre").value("Beatriz"))
				.andExpect(jsonPath("$.data[1].nombre").value("Hector Ernan"))
				);
	}
	
	@Test
	void deleteStudentTest() throws Exception {
		
		when(service.deleteStudent("9089909019")).thenReturn(StudentDto.builder()
				.data(null)
				.message(Message.builder()
						.code(Constant.CODE_SUSSES)
						.message(Constant.MESSAGE_SUSSES_DELETE)
						.build())
				.build());
		
		assertThat(mockMvc.perform(delete("/v1/student/9089909019"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.message.message").value(Constant.MESSAGE_SUSSES_DELETE))
				);
	}
	
	@Test
	void insertStudentTest() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		when(service.insertStudent(Data.STUDENT_TWO)).thenReturn(StudentDto.builder()
				.data(null)
				.message(Message.builder()
						.code(Constant.CODE_SUSSES)
						.message(Constant.MESSAGE_SUSSES_INSERT)
						.build())
				.build());
		log.info(objectMapper.writeValueAsString(Data.STUDENT_TWO));
		assertThat(mockMvc.perform(post("/v1/student")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(Data.STUDENT_TWO)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.message.message").value(Constant.MESSAGE_SUSSES_INSERT))
				);
	}
	
	@Test
	void updateStudentTest() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		when(service.updateStudent("9089909021",Data.STUDENT_THREE_UPDATE)).thenReturn(StudentDto.builder()
				.data(null)
				.message(Message.builder()
						.code(Constant.CODE_SUSSES)
						.message(Constant.MESSAGE_SUSSES_UPDATE)
						.build())
				.build());
		assertThat(mockMvc.perform(put("/v1/student/9089909021")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(Data.STUDENT_THREE_UPDATE)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.message.message").value(Constant.MESSAGE_SUSSES_UPDATE))
				);
	}

}
