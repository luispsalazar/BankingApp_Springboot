package com.fdmgroup.BankingApplication.customer;

import java.util.Objects;

import com.fdmgroup.BankingApplication.address.Address;
import com.fdmgroup.BankingApplication.address.AddressDto;

public class CustomerDto {

	private String name;
	private String customerType;
	private AddressDto addressDto;

	public CustomerDto() {
		super();
	}

	public CustomerDto(String name, String customerType, AddressDto addressDto) {
		super();
		this.name = name;
		this.customerType = customerType;
		this.addressDto = addressDto;
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

	public AddressDto getAddressDto() {
		return addressDto;
	}

	public void setAddressDto(AddressDto addressDto) {
		this.addressDto = addressDto;
	}

	@Override
	public String toString() {
		return "CustomerDto [name=" + name + ", customerType=" + customerType + ", addressDto=" + addressDto + "]";
	}
}