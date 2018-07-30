package com.assignments.parser.impl;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;

import com.assignments.CognizantAssignments.CognizantAssignmentsApplicationTests;
import com.assignments.parser.impl.AssignmentsCSVParser;

/**
 * @author Jagadish Anala
 *
 */
@ContextConfiguration(locations = { "classpath:/testContext.xml" })
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AssignmentsCSVParserTest extends CognizantAssignmentsApplicationTests {

	@Autowired
	private AssignmentsCSVParser csvParser;

	@Test
	public void parseFileSuccess() throws Exception {
		assertNotNull(csvParser);
		String csvFileContent = "Reference,AccountNumber,Description,Start Balance,Mutation,End Balance\n194261,NL91RABO0315273637,Clothes from Jan Bakker,21.6,-41.83,-20.23\n112806,NL27SNSB0917829871,Clothes for Willem Dekker,91.23,+15.57,106.8\n183049,NL69ABNA0433647324,Clothes for Jan King,86.66,+44.5,131.16\n183356,NL74ABNA0248990274,Subscription for Peter de Vries,92.98,-46.65,46.33\n112806,NL69ABNA0433647324,Clothes for Richard de Vries,90.83,-10.91,79.92\n112806,NL93ABNA0585619023,Tickets from Richard Bakker,102.12,+45.87,147.99\n139524,NL43AEGO0773393871,Flowers from Jan Bakker,99.44,+41.23,140.67\n179430,NL93ABNA0585619023,Clothes for Vincent Bakker,23.96,-27.43,-3.47\n141223,NL93ABNA0585619023,Clothes from Erik Bakker,94.25,+41.6,135.85\n195446,NL74ABNA0248990274,Flowers for Willem Dekker,26.32,+48.98,79.3\n";
		String fileName = "testRecords.csv";
		MockMultipartFile mockMultipartFile = new MockMultipartFile("user-file", fileName, "text/plain",
				csvFileContent.getBytes());
		assertTrue(csvParser.parseFile(mockMultipartFile) instanceof List);
		assertEquals(ArrayList.class, csvParser.parseFile(mockMultipartFile).getClass());
	}

	@Test
	public void parseFileFailure() throws Exception {
		assertNotNull(csvParser);
		String csvFileContent = "Reference\",";
		String fileName = "testRecords.csv";
		MockMultipartFile mockMultipartFile = new MockMultipartFile("user-file", fileName, "text/plain",
				csvFileContent.getBytes());
		assertThatThrownBy(() -> csvParser.parseFile(mockMultipartFile)).isInstanceOf(Exception.class);
	}
}
