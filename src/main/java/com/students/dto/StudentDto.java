package com.students.dto;

import java.util.LinkedList;

import com.students.entity.Message;
import com.students.entity.Student;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {

	private LinkedList<Student> data;
	private Message message;

}
