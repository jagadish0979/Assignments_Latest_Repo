package com.assignments.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.assignments.domain.CustomerStatement;
import com.assignments.service.AssignmentService;

/**
 * @author Jagadish Anala
 *
 */
@Controller
@RequestMapping("/")
public class AssignmentController {

	@Autowired
	private AssignmentService assignmentService; 
	
	/**
	 * Processes uploaded files.
	 * @return ResponseEntity<CustomerStatement[]>
	 */
	@PostMapping(value = "/processFile", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CustomerStatement>> processFile(@RequestParam(value="file") MultipartFile file) throws Exception {
		List<CustomerStatement> customerStatements = assignmentService.processFile(file);
		return new ResponseEntity<List<CustomerStatement>>(customerStatements, HttpStatus.OK);
	}
}
