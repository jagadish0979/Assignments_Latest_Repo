package com.assignments.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Jagadish Anala
 *
 */
@XmlRootElement(name = "records")
public class CustomerRecord {

	private List<Record> recordList;

	/**
	 * 
	 */
	public CustomerRecord() {
	}

	/**
	 * @param records
	 */
	public CustomerRecord(List<Record> recordList) {
		super();
		this.recordList = recordList;
	}

	/**
	 * @return the recordList
	 */
	@XmlElement(name="record", required = true)
	public List<Record> getRecordList() {
		return recordList;
	}

	/**
	 * @param recordList the recordList to set
	 */
	public void setRecordList(List<Record> recordList) {
		this.recordList = recordList;
	}

}