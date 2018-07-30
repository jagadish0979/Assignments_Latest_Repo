package com.assignments.factory;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ContextConfiguration;

import com.assignments.CognizantAssignments.CognizantAssignmentsApplicationTests;
import com.assignments.constant.FileType;
import com.assignments.exception.ParserException;
import com.assignments.parser.IParser;
import com.assignments.parser.impl.AssignmentsCSVParser;
import com.assignments.parser.impl.AssignmentsXMLParser;

@ContextConfiguration(locations = { "classpath:/testContext.xml" })
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ParserFactoryTest extends CognizantAssignmentsApplicationTests {

	@Autowired
	private IParserFactory parserFactory;

	@Test
	public void testParsersSuccess() throws ParserException {
		assertNotNull(parserFactory);
		assertTrue(parserFactory.getParser(FileType.csv.toString()) instanceof IParser);
		assertTrue(parserFactory.getParser(FileType.xml.toString()) instanceof IParser);
	}

	@Test
	public void testParsersFailure() {
		assertNotNull(parserFactory);
		assertThatThrownBy(() -> parserFactory.getParser(FileType.pdf.toString())).isInstanceOf(ParserException.class);
		assertThatThrownBy(() -> parserFactory.getParser(FileType.html.toString())).isInstanceOf(ParserException.class);
		
	}
	
	@Test
	public void testCSVParser() throws ParserException {
		assertNotNull(parserFactory);
		assertEquals(AssignmentsCSVParser.class, parserFactory.getParser(FileType.csv.toString()).getClass());
	}

	@Test
	public void testXMLParser() throws ParserException {
		assertNotNull(parserFactory);
		assertEquals(AssignmentsXMLParser.class, parserFactory.getParser(FileType.xml.toString()).getClass());
	}
	
}
