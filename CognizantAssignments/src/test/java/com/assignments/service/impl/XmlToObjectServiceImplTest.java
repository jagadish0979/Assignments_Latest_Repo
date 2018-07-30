package com.assignments.service.impl;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import javax.xml.bind.UnmarshalException;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;

import com.assignments.CognizantAssignments.CognizantAssignmentsApplicationTests;
import com.assignments.domain.CustomerRecord;
import com.assignments.service.XmlToObjectService;

/**
 * @author Jagadish Anala
 *
 */
@ContextConfiguration(locations = { "classpath:/testContext.xml" })
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class XmlToObjectServiceImplTest extends CognizantAssignmentsApplicationTests {

	@Autowired
	private XmlToObjectService xmlToObjectService;

	@Test
	public void processXmlFileSuccess() throws Exception {
		assertNotNull(xmlToObjectService);
		String xmlFileContent = "<records><record reference=\"130498\"><accountNumber>NL69ABNA0433647324</accountNumber><description>Tickets for Peter Theuß</description><startBalance>26.9</startBalance><mutation>-18.78</mutation><endBalance>8.12</endBalance></record><record reference=\"167875\"><accountNumber>NL93ABNA0585619023</accountNumber><description>Tickets from Erik de Vries</description><startBalance>5429</startBalance><mutation>-939</mutation><endBalance>6368</endBalance></record><record reference=\"147674\"><accountNumber>NL93ABNA0585619023</accountNumber><description>Subscription from Peter Dekker</description><startBalance>74.69</startBalance><mutation>-44.91</mutation><endBalance>29.78</endBalance></record><record reference=\"135607\"><accountNumber>NL27SNSB0917829871</accountNumber><description>Subscription from Peter Theuß</description><startBalance>70.42</startBalance><mutation>+34.42</mutation><endBalance>104.84</endBalance></record><record reference=\"169639\"><accountNumber>NL43AEGO0773393871</accountNumber><description>Tickets for Rik de Vries</description><startBalance>31.78</startBalance><mutation>-6.14</mutation><endBalance>25.64</endBalance></record><record reference=\"105549\"><accountNumber>NL43AEGO0773393871</accountNumber><description>Flowers from Peter de Vries</description><startBalance>105.75</startBalance><mutation>-26.17</mutation><endBalance>79.58</endBalance></record><record reference=\"150438\"><accountNumber>NL74ABNA0248990274</accountNumber><description>Tickets from Richard de Vries</description><startBalance>10.1</startBalance><mutation>-0.3</mutation><endBalance>9.8</endBalance></record><record reference=\"172833\"><accountNumber>NL69ABNA0433647324</accountNumber><description>Tickets for Willem Theuß</description><startBalance>66.72</startBalance><mutation>-41.74</mutation><endBalance>24.98</endBalance></record><record reference=\"165102\"><accountNumber>NL93ABNA0585619023</accountNumber><description>Tickets for Rik Theuß</description><startBalance>3980</startBalance><mutation>+1000</mutation><endBalance>4981</endBalance></record><record reference=\"170148\"><accountNumber>NL43AEGO0773393871</accountNumber><description>Flowers for Jan Theuß</description><startBalance>16.52</startBalance><mutation>+43.09</mutation><endBalance>59.61</endBalance></record><record reference=\"170148\"><accountNumber>NL43AEGO0773393871</accountNumber><description>Flowers for Jan Theu2</description><startBalance>16.52</startBalance><mutation>+43.09</mutation><endBalance>59.61</endBalance></record></records>";
		String fileName = "testRecords.xml";
		MockMultipartFile mockMultipartFile = new MockMultipartFile("file", fileName,
				MediaType.APPLICATION_XML_VALUE.toString(), xmlFileContent.getBytes("UTF-8"));
		assertTrue(xmlToObjectService.processXmlFile(mockMultipartFile) instanceof CustomerRecord);
		assertEquals(CustomerRecord.class, xmlToObjectService.processXmlFile(mockMultipartFile).getClass());
	}

	@Test
	public void processXmlFileFailure() throws Exception {
		assertNotNull(xmlToObjectService);
		String xmlFileContent = "records><record reference=\"130498\"><accountNumber>NL69ABNA0433647324</accountNumber><description>Tickets for Peter Theuß</description><startBalance>26.9</startBalance><mutation>-18.78</mutation><endBalance>8.12</endBalance></record><record reference=\"167875\"><accountNumber>NL93ABNA0585619023</accountNumber><description>Tickets from Erik de Vries</description><startBalance>5429</startBalance><mutation>-939</mutation><endBalance>6368</endBalance></record><record reference=\"147674\"><accountNumber>NL93ABNA0585619023</accountNumber><description>Subscription from Peter Dekker</description><startBalance>74.69</startBalance><mutation>-44.91</mutation><endBalance>29.78</endBalance></record><record reference=\"135607\"><accountNumber>NL27SNSB0917829871</accountNumber><description>Subscription from Peter Theuß</description><startBalance>70.42</startBalance><mutation>+34.42</mutation><endBalance>104.84</endBalance></record><record reference=\"169639\"><accountNumber>NL43AEGO0773393871</accountNumber><description>Tickets for Rik de Vries</description><startBalance>31.78</startBalance><mutation>-6.14</mutation><endBalance>25.64</endBalance></record><record reference=\"105549\"><accountNumber>NL43AEGO0773393871</accountNumber><description>Flowers from Peter de Vries</description><startBalance>105.75</startBalance><mutation>-26.17</mutation><endBalance>79.58</endBalance></record><record reference=\"150438\"><accountNumber>NL74ABNA0248990274</accountNumber><description>Tickets from Richard de Vries</description><startBalance>10.1</startBalance><mutation>-0.3</mutation><endBalance>9.8</endBalance></record><record reference=\"172833\"><accountNumber>NL69ABNA0433647324</accountNumber><description>Tickets for Willem Theuß</description><startBalance>66.72</startBalance><mutation>-41.74</mutation><endBalance>24.98</endBalance></record><record reference=\"165102\"><accountNumber>NL93ABNA0585619023</accountNumber><description>Tickets for Rik Theuß</description><startBalance>3980</startBalance><mutation>+1000</mutation><endBalance>4981</endBalance></record><record reference=\"170148\"><accountNumber>NL43AEGO0773393871</accountNumber><description>Flowers for Jan Theuß</description><startBalance>16.52</startBalance><mutation>+43.09</mutation><endBalance>59.61</endBalance></record><record reference=\"170148\"><accountNumber>NL43AEGO0773393871</accountNumber><description>Flowers for Jan Theu2</description><startBalance>16.52</startBalance><mutation>+43.09</mutation><endBalance>59.61</endBalance></record></records>";
		String fileName = "testRecords.xml";
		MockMultipartFile mockMultipartFile = new MockMultipartFile("user-file", fileName,
				MediaType.APPLICATION_XML_VALUE.toString(), xmlFileContent.getBytes("UTF-8"));
		assertThatThrownBy(() -> xmlToObjectService.processXmlFile(mockMultipartFile))
				.isInstanceOf(UnmarshalException.class);
	}
}
