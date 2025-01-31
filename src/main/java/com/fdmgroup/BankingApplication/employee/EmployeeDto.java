package com.fdmgroup.BankingApplication.employee;

import java.util.Set;

public class EmployeeDto {

	private String name;
	private Set<Role> role;

	public EmployeeDto(String name, Set<Role> role) {
		super();
		this.name = name;
		this.role = role;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Role> getRole() {
		return role;
	}

	public void setRole(Set<Role> role) {
		this.role = role;
	}
}