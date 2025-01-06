package com.fdmgroup.BankingApplication.customer;

import java.util.List;

import com.fdmgroup.BankingApplication.account.Account;
import com.fdmgroup.BankingApplication.address.Address;

import jakarta.persistence.Entity;

@Entity
public class Company extends Customer {

	public Company() {
		super();
	}

	public Company(Long customerId, String name, Address address, List<Account> accounts) {
		super(customerId, name, address, accounts);
	}

	public Company(String name, Address address, List<Account> accounts) {
		super(name, address, accounts);
	}
}