package com.assignments.service.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;

import com.assignments.CognizantAssignments.CognizantAssignmentsApplicationTests;
import com.assignments.service.AssignmentService;

/**
 * @author Jagadish Anala
 *
 */
@ContextConfiguration(locations = { "classpath:/testContext.xml" })
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AssignmentServiceImplTest extends CognizantAssignmentsApplicationTests {

	@MockBean
	private AssignmentService assignmentService;

	@Test
	public void processCSVSuccess() throws Exception {
		assertNotNull(assignmentService);
		String csvFileContent = "Reference,AccountNumber,Description,Start Balance,Mutation,End Balance\\n194261,NL91RABO0315273637,Clothes from Jan Bakker,21.6,-41.83,-20.23\\n112806,NL27SNSB0917829871,Clothes for Willem Dekker,91.23,+15.57,106.8\\n183049,NL69ABNA0433647324,Clothes for Jan King,86.66,+44.5,131.16\\n183356,NL74ABNA0248990274,Subscription for Peter de Vries,92.98,-46.65,46.33\\n112806,NL69ABNA0433647324,Clothes for Richard de Vries,90.83,-10.91,79.92\\n112806,NL93ABNA0585619023,Tickets from Richard Bakker,102.12,+45.87,147.99\\n139524,NL43AEGO0773393871,Flowers from Jan Bakker,99.44,+41.23,140.67\\n179430,NL93ABNA0585619023,Clothes for Vincent Bakker,23.96,-27.43,-3.47\\n141223,NL93ABNA0585619023,Clothes from Erik Bakker,94.25,+41.6,135.85\\n195446,NL74ABNA0248990274,Flowers for Willem Dekker,26.32,+48.98,79.3\\n";
		String fileName = "testRecords.csv";
		MockMultipartFile mockMultipartFile = new MockMultipartFile("user-file", fileName, "text/plain",
				csvFileContent.getBytes());
		assertTrue(assignmentService.processFile(mockMultipartFile) instanceof List);
	}

	@Test
	public void processXMLSuccess() throws Exception {
		assertNotNull(assignmentService);
		String xmlFileContent = "<records><record reference=\"130498\"><accountNumber>NL69ABNA0433647324</accountNumber><description>Tickets for Peter Theuß</description><startBalance>26.9</startBalance><mutation>-18.78</mutation><endBalance>8.12</endBalance></record><record reference=\"167875\"><accountNumber>NL93ABNA0585619023</accountNumber><description>Tickets from Erik de Vries</description><startBalance>5429</startBalance><mutation>-939</mutation><endBalance>6368</endBalance></record><record reference=\"147674\"><accountNumber>NL93ABNA0585619023</accountNumber><description>Subscription from Peter Dekker</description><startBalance>74.69</startBalance><mutation>-44.91</mutation><endBalance>29.78</endBalance></record><record reference=\"135607\"><accountNumber>NL27SNSB0917829871</accountNumber><description>Subscription from Peter Theuß</description><startBalance>70.42</startBalance><mutation>+34.42</mutation><endBalance>104.84</endBalance></record><record reference=\"169639\"><accountNumber>NL43AEGO0773393871</accountNumber><description>Tickets for Rik de Vries</description><startBalance>31.78</startBalance><mutation>-6.14</mutation><endBalance>25.64</endBalance></record><record reference=\"105549\"><accountNumber>NL43AEGO0773393871</accountNumber><description>Flowers from Peter de Vries</description><startBalance>105.75</startBalance><mutation>-26.17</mutation><endBalance>79.58</endBalance></record><record reference=\"150438\"><accountNumber>NL74ABNA0248990274</accountNumber><description>Tickets from Richard de Vries</description><startBalance>10.1</startBalance><mutation>-0.3</mutation><endBalance>9.8</endBalance></record><record reference=\"172833\"><accountNumber>NL69ABNA0433647324</accountNumber><description>Tickets for Willem Theuß</description><startBalance>66.72</startBalance><mutation>-41.74</mutation><endBalance>24.98</endBalance></record><record reference=\"165102\"><accountNumber>NL93ABNA0585619023</accountNumber><description>Tickets for Rik Theuß</description><startBalance>3980</startBalance><mutation>+1000</mutation><endBalance>4981</endBalance></record><record reference=\"170148\"><accountNumber>NL43AEGO0773393871</accountNumber><description>Flowers for Jan Theuß</description><startBalance>16.52</startBalance><mutation>+43.09</mutation><endBalance>59.61</endBalance></record><record reference=\"170148\"><accountNumber>NL43AEGO0773393871</accountNumber><description>Flowers for Jan Theu2</description><startBalance>16.52</startBalance><mutation>+43.09</mutation><endBalance>59.61</endBalance></record></records>";
		String fileName = "testRecords.xml";
		MockMultipartFile mockMultipartFile = new MockMultipartFile("user-file", fileName, "text/plain",
				xmlFileContent.getBytes());
		assertTrue(assignmentService.processFile(mockMultipartFile) instanceof List);
	}
}
