package com.assignments.parser.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.assignments.domain.CustomerRecord;
import com.assignments.domain.CustomerStatement;
import com.assignments.domain.Record;
import com.assignments.exception.ParserException;
import com.assignments.helper.FileProcessor;
import com.assignments.parser.IParser;
import com.assignments.service.XmlToObjectService;

/**
 * @author Jagadish Anala
 *
 */
@Component
public class AssignmentsXMLParser implements IParser {

	@Autowired
	private XmlToObjectService xmlToObjectService;

	@Autowired
	private FileProcessor fileProcessor;

	@Override
	public List<CustomerStatement> parseFile(MultipartFile multipartFile)
			throws ParserException, IOException, Exception {
		List<CustomerStatement> customerStatements = new ArrayList<CustomerStatement>();
		List<CustomerStatement> resultList = new ArrayList<CustomerStatement>();

		CustomerRecord customerRecord = null;
		customerRecord = xmlToObjectService.processXmlFile(multipartFile);

		if (customerRecord != null) {
			List<Record> recordList = customerRecord.getRecordList();
			if (recordList != null) {
				int size = recordList.size();
				for (int i = 0; i < size; i++) {
					Record record = recordList.get(i);
					CustomerStatement cs = new CustomerStatement();
					cs.setTransactionReference(record.getReference());
					cs.setAccountNumber(record.getAccountNumber());
					cs.setDescription(record.getDescription());
					cs.setStartBalance(record.getStartBalance());
					cs.setMutation(record.getMutation());
					cs.setEndBalance(record.getEndBalance());
					customerStatements.add(cs);
				}
				resultList = fileProcessor.validateCustomerStatements(customerStatements);
			}
		}
		return resultList;
	}
}
