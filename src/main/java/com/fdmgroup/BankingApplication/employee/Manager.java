package com.fdmgroup.BankingApplication.employee;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "MANAGER")
public class Manager extends Employee {

	public Manager() {
		super();
	}

	public Manager(long employeeId, String name, Set<Role> role) {
		super(employeeId, name, role);
	}

	public Manager(String name, Set<Role> role) {
		super(name, role);
	}
}