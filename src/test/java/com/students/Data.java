package com.students;

import com.students.entity.Message;
import com.students.entity.Student;
import com.students.utils.Constant;

public class Data {
	
	//Data Service
	
	public static final Student STUDENT_ONE = Student.builder()
			.apellidoMaterno("Ortiz")
			.apellidoPaterno("Hernandez")
			.nombre("Beatriz")
			.matricula("9089909019")
			.build();
	
	public static final Student STUDENT_TWO = Student.builder()
			.apellidoMaterno("Padilla")
			.apellidoPaterno("Gutierrez")
			.nombre("Hector Ernan")
			.matricula("9089909020")
			.build();
	
	public static final Student STUDENT_THREE = Student.builder()
			.apellidoMaterno("Ortiz")
			.apellidoPaterno("Zevilla")
			.nombre("Nanzi")
			.matricula("9089909021")
			.build();
	
	public static final Student STUDENT_FOUR = Student.builder()
			.apellidoMaterno("Orzco")
			.apellidoPaterno("Hernandez")
			.nombre("Patricia")
			.matricula("9089909022")
			.build();
	
	public static final Student STUDENT_FIVE = Student.builder()
			.apellidoMaterno("Pineda")
			.apellidoPaterno("Rodriguez")
			.nombre("Eleazar")
			.matricula("9089909023")
			.build();
	
	public static final Student STUDENT_THREE_UPDATE = Student.builder()
			.apellidoMaterno("Lopez")
			.apellidoPaterno("Zevilla")
			.nombre("Nanzi Paulina")
			.build();
	
	public static final Message MESSAGE_FILE = Message.builder()
			.code(Constant.CODE_SUSSES)
			.message(Constant.MESSAGE_SUSSES_FILE)
			.build();


}
