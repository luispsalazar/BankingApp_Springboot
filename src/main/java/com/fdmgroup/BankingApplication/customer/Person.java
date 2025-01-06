package com.fdmgroup.BankingApplication.customer;

import java.util.List;

import com.fdmgroup.BankingApplication.account.Account;
import com.fdmgroup.BankingApplication.address.Address;

import jakarta.persistence.Entity;

@Entity
public class Person extends Customer {

	public Person() {
		super();
	}

	public Person(Long customerId, String name, Address address, List<Account> accounts) {
		super(customerId, name, address, accounts);
	}

	public Person(String name, Address address, List<Account> accounts) {
		super(name, address, accounts);
	}
}