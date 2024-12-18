package com.students.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

import org.springframework.stereotype.Component;

import com.students.config.FileProperties;
import com.students.entity.Message;
import com.students.entity.Student;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class DriveFile {

	private FileProperties fileProperties;
	
	public DriveFile(FileProperties fileProperties) {
		this.fileProperties = fileProperties;
	}
	
	public LinkedList<Student> findAll() throws IOException {
		LinkedList<Student> students = new LinkedList<>();
		BufferedReader br = new BufferedReader(new FileReader(fileProperties.getName()));
		String line;
		while ((line = br.readLine()) != null) {
			String[] studentProp = line.split(",");
			students.add(Student.builder()
					.nombre(studentProp[0])
					.apellidoPaterno(studentProp[1])
					.apellidoMaterno(studentProp[2])
					.matricula(studentProp[3])
					.build());
		}
		br.close();
		
		return students;

	}
	
	public Message writeFile(LinkedList<Student> students) {
			File file = new File(fileProperties.getName());
			BufferedWriter bw;
			try {
				bw = new BufferedWriter(new FileWriter(file));
				
				students.forEach(s -> {
					try {
						bw.write(s.getNombre()
								+","+s.getApellidoPaterno()
								+","+s.getApellidoMaterno()
								+","+s.getMatricula()+"\n");
					} catch (IOException e) {
						log.error(e);
					}
				});
				
				bw.close();
				return Message.builder()
						.code(Constant.CODE_SUSSES)
						.message(Constant.MESSAGE_SUSSES_FILE)
						.build();
			} catch (IOException e) {
				log.error(e);
				return Message.builder()
						.code(Constant.CODE_ERROR_WRITE_FILE)
						.message(Constant.MESSAGE_ERROR_WRITE_FILE)
						.build();
				}
			
		}

	public Boolean existFile() {
		
		File file = new File(fileProperties.getName());
		
		return file.exists();
		
	}
	
	public void createFile() {
		log.info("ruta: "+fileProperties.getPath()+fileProperties.getName());
		File file = new File(fileProperties.getName());
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
