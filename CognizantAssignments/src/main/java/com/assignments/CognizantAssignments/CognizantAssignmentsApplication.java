package com.assignments.CognizantAssignments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * @author Jagadish Anala
 * 
 */
@SpringBootApplication
@ImportResource("classpath:applicationContext.xml")
public class CognizantAssignmentsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CognizantAssignmentsApplication.class, args);
	}
}
