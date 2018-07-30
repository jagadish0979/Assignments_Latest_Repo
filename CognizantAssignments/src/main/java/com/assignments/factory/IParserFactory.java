package com.assignments.factory;

import com.assignments.exception.ParserException;
import com.assignments.parser.IParser;

/**
 * @author Jagadish Anala
 * 
 */
public interface IParserFactory {
	public IParser getParser(String fileType) throws ParserException;
}
