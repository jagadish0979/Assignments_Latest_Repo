package com.assignments.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.assignments.domain.CustomerStatement;


/**
 * @author Jagadish Anala
 *
 */
public interface AssignmentService {
	public List<CustomerStatement> processFile(MultipartFile multipartFile) throws Exception;
}
