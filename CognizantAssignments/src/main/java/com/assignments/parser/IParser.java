package com.assignments.parser;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.assignments.domain.CustomerStatement;
import com.assignments.exception.ParserException;

public interface IParser {
	public List<CustomerStatement> parseFile(MultipartFile file) throws ParserException, IOException, Exception;
}
