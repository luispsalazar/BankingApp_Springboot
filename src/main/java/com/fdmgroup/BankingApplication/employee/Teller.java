package com.fdmgroup.BankingApplication.employee;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "TELLER")
public class Teller extends Employee {

	public Teller() {
		super();
	}

	public Teller(long employeeId, String name, Set<Role> role) {
		super(employeeId, name, role);
	}

	public Teller(String name, Set<Role> role) {
		super(name, role);
	}
}