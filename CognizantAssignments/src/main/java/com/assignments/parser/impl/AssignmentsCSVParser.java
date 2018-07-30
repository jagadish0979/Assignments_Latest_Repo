package com.assignments.parser.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.assignments.domain.CustomerStatement;
import com.assignments.exception.ParserException;
import com.assignments.helper.FileProcessor;
import com.assignments.parser.IParser;

/**
 * @author Jagadish Anala
 *
 */
@Component
public class AssignmentsCSVParser implements IParser {

	@Autowired
	private FileProcessor fileProcessor;

	@Override
	public List<CustomerStatement> parseFile(MultipartFile multipartFile)
			throws ParserException, IOException, Exception {
		List<CustomerStatement> customerStatements = new ArrayList<CustomerStatement>();

		List<CustomerStatement> resultList = new ArrayList<CustomerStatement>();

		List<CSVRecord> records = getLines(multipartFile);
		for (CSVRecord record : records) {
			CustomerStatement customerStatement = new CustomerStatement();
			customerStatement.setTransactionReference(Integer.parseInt(record.get(0)));
			customerStatement.setAccountNumber(record.get(1));
			customerStatement.setDescription(record.get(2));
			customerStatement.setStartBalance(Double.parseDouble(record.get(3)));
			customerStatement.setMutation(Double.parseDouble(record.get(4)));
			customerStatement.setEndBalance(Double.parseDouble(record.get(5)));
			customerStatements.add(customerStatement);
		}
		resultList = fileProcessor.validateCustomerStatements(customerStatements);
		return resultList;
	}

	private List<CSVRecord> getLines(MultipartFile multipartFile) throws IOException, ParserException {
		if (multipartFile.getSize() == 0)
			throw new ParserException("Invalid File");
		BufferedReader bufferedReader = null;
		List<CSVRecord> csvRecords = new ArrayList<CSVRecord>();
		InputStream inputStream = multipartFile.getInputStream();
		bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		CSVParser csvParser = new CSVParser(bufferedReader,
				CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());

		csvRecords = csvParser.getRecords();
		if (csvRecords.size() == 0) {
			csvParser.close();
			throw new ParserException("Invalid File");
		}
		if (bufferedReader != null) {
			bufferedReader.close();
		}
		if (csvParser != null) {
			csvParser.close();
		}
		return csvRecords;
	}
}
