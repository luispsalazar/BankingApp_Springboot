package com.fdmgroup.BankingApplication.customer;

public class CustomerDto {

	private String name;
	private String customerType;
	private String streetNumber;
	private String postalCode;

	public CustomerDto(String name, String customerType, String streetNumber, String postalCode) {
		super();
		this.name = name;
		this.customerType = customerType;
		this.streetNumber = streetNumber;
		this.postalCode = postalCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public String getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
}