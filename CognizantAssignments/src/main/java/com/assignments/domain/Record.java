package com.assignments.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Jagadish Anala
 *
 */
@XmlRootElement(name = "record")
@XmlAccessorType(XmlAccessType.FIELD)
public class Record {

	@XmlAttribute(name = "reference")
	private Integer reference;

	@XmlElement(name = "accountNumber")
	private String accountNumber;

	@XmlElement(name = "description")
	private String description;

	@XmlElement(name = "startBalance")
	private Double startBalance;

	@XmlElement(name = "mutation")
	private Double mutation;

	@XmlElement(name = "endBalance")
	private Double endBalance;

	/**
	 * 
	 */
	public Record() {
	}

	/**
	 * @param reference
	 * @param accountNumber
	 * @param description
	 * @param startBalance
	 * @param mutation
	 * @param endBalance
	 */
	public Record(Integer reference, String accountNumber, String description, Double startBalance, Double mutation,
			Double endBalance) {
		super();
		this.reference = reference;
		this.accountNumber = accountNumber;
		this.description = description;
		this.startBalance = startBalance;
		this.mutation = mutation;
		this.endBalance = endBalance;
	}

	/**
	 * @return the accountNumber
	 */
	public String getAccountNumber() {
		return accountNumber;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return the endBalance
	 */
	public Double getEndBalance() {
		return endBalance;
	}

	/**
	 * @return the mutation
	 */
	public Double getMutation() {
		return mutation;
	}

	/**
	 * @return the reference
	 */
	public Integer getReference() {
		return reference;
	}

	/**
	 * @return the startBalance
	 */
	public Double getStartBalance() {
		return startBalance;
	}

	/**
	 * @param accountNumber the accountNumber to set
	 */
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @param endBalance the endBalance to set
	 */
	public void setEndBalance(Double endBalance) {
		this.endBalance = endBalance;
	}

	/**
	 * @param mutation the mutation to set
	 */
	public void setMutation(Double mutation) {
		this.mutation = mutation;
	}

	/**
	 * @param reference the reference to set
	 */
	public void setReference(Integer reference) {
		this.reference = reference;
	}

	/**
	 * @param startBalance the startBalance to set
	 */
	public void setStartBalance(Double startBalance) {
		this.startBalance = startBalance;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Record [reference=" + reference + ", accountNumber=" + accountNumber + ", description=" + description
				+ ", startBalance=" + startBalance + ", mutation=" + mutation + ", endBalance=" + endBalance + "]";
	}

}
