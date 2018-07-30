package com.assignments.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.assignments.domain.CustomerStatement;
import com.assignments.util.AssignmentsUtil;

/**
 * @author Jagadish Anala
 *
 */
@Component
public class FileProcessor {

	@Autowired
	private Environment environment;

	public List<CustomerStatement> validateCustomerStatements(List<CustomerStatement> originalList){
		List<CustomerStatement> invalidEndBalanceList = new ArrayList<CustomerStatement>();
		List<CustomerStatement> duplicateList = new ArrayList<CustomerStatement>();
		List<CustomerStatement> resultList = new ArrayList<CustomerStatement>();
		invalidEndBalanceList = findInvalidEndBalaceList(originalList);
		resultList = addToResultList(invalidEndBalanceList,resultList);
		duplicateList = findDuplicateReferenceNumberList(originalList);
		resultList = addToResultList(duplicateList,resultList);
		return resultList;
	}
	
	private List<CustomerStatement> findInvalidEndBalaceList(List<CustomerStatement> originalList) {
		List<CustomerStatement> invalidEndBalanceList = originalList.stream().filter(
				c -> AssignmentsUtil.Round(c.getStartBalance().doubleValue() + c.getMutation().doubleValue()) != c
						.getEndBalance().doubleValue())
				.collect(Collectors.toList());
		invalidEndBalanceList.forEach(i -> i.setFailedReason(environment.getProperty("error.data.end_balance")));
		return invalidEndBalanceList;
	}

	private List<CustomerStatement> findDuplicateReferenceNumberList(List<CustomerStatement> originalList) {
		Map<Integer, List<CustomerStatement>> recordsByReferece = originalList.stream()
				.collect(Collectors.groupingBy(CustomerStatement::getTransactionReference));
		List<CustomerStatement> duplicateList = new ArrayList<CustomerStatement>();
		recordsByReferece.values().stream().filter(i -> i.size() > 1).collect(Collectors.toList())
				.forEach(d -> duplicateList.addAll(d));
		duplicateList.forEach(d -> d.setFailedReason(environment.getProperty("error.data.referene_number")));
		return duplicateList;
	}
	
	private List<CustomerStatement> addToResultList(List<CustomerStatement> listToCopy, List<CustomerStatement> resultList){
		listToCopy.forEach(i-> {
			CustomerStatement rc = new CustomerStatement();
			rc.setDescription(i.getDescription());
			rc.setFailedReason(i.getFailedReason());
			rc.setTransactionReference(i.getTransactionReference());
			resultList.add(rc);
		});
		return resultList;
	}
}