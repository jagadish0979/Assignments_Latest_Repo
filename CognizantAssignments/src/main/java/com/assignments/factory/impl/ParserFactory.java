package com.assignments.factory.impl;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.assignments.exception.ParserException;
import com.assignments.factory.IParserFactory;
import com.assignments.parser.IParser;
import com.assignments.parser.impl.AssignmentsCSVParser;
import com.assignments.parser.impl.AssignmentsXMLParser;

/**
 * @author Jagadish Anala
 * 
 */
@Component
public class ParserFactory implements IParserFactory {

	@Autowired
	private AssignmentsXMLParser xmlParser;

	@Autowired
	private AssignmentsCSVParser csvParser;

	@Autowired
	private Environment env;

	final static Map<String, IParser> map = new HashMap<>();

	@PostConstruct
	public void init() {
		map.put("XML", xmlParser);
		map.put("CSV", csvParser);
	}

	@Override
	public IParser getParser(String fileType) throws ParserException {
		IParser parser = map.get(fileType.toUpperCase());
		if (parser != null) {
			return parser;
		}
		String message = MessageFormat.format(env.getProperty("error.parser.invalid"), fileType.toUpperCase());
		throw new ParserException(message);
	}
}
