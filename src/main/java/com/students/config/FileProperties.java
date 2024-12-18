package com.students.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Getter;

@Component
@PropertySource(value = { "classpath:application.yml" })
@Getter
public class FileProperties {
	@Value( "${file.path}" )
	private String path;
	@Value( "${file.name}" )
	private String name;
	
	@Override
	public String toString() {
		return path+name;
	}

}
