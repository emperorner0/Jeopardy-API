package com.example.JeopardyAPI;

import com.example.JeopardyAPI.controller.CSVController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackageClasses = com.example.JeopardyAPI)
public class JeopardyApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(JeopardyApiApplication.class, args);
	}

}
