package com.assignments.service.impl;

import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.assignments.domain.CustomerStatement;
import com.assignments.factory.IParserFactory;
import com.assignments.service.AssignmentService;

/**
 * @author Jagadish Anala
 *
 */
@Service("assignmentService")
public class AssignmentServiceImpl implements AssignmentService {

	@Autowired
	private IParserFactory parserFactory;

	@Override
	public List<CustomerStatement> processFile(MultipartFile multipartFile) throws Exception {
		String fileType = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
		List<CustomerStatement> customerStatements = parserFactory.getParser(fileType).parseFile(multipartFile);
		return customerStatements;
	}
}
