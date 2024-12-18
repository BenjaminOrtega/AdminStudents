package com.students.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
	private String nombre;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String matricula;
}
