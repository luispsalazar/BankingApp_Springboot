package com.fdmgroup.BankingApplication.employee;

import java.util.Objects;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;

@Entity
@Table(name = "EMPLOYEE")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Employee {

	@Id
	@Column(name = "EMPLOYEE_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EMPLOYEE_SEQ_GEN")
	@SequenceGenerator(name = "EMPLOYEE_SEQ_GEN", sequenceName = "EMPLOYEE_SEQ")
	private long employeeId;

	@Column(name = "EMPLOYEE_NAME")
	private String name;

	@ElementCollection(fetch = FetchType.EAGER)
	@Enumerated(EnumType.STRING)
	@CollectionTable(name = "EMPLOYEE_ROLES", joinColumns = @JoinColumn(name = "EMPLOYEE_ID"))
	@Column(name = "ROLE")
	private Set<Role> role;

	public Employee() {
		super();
	}

	public Employee(long employeeId, String name, Set<Role> role) {
		super();
		this.employeeId = employeeId;
		this.name = name;
		this.role = role;
	}

	public Employee(String name, Set<Role> role) {
		super();
		this.name = name;
		this.role = role;
	}

	public long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
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

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", name=" + name + ", role=" + role + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(employeeId, name, role);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return employeeId == other.employeeId && Objects.equals(name, other.name) && Objects.equals(role, other.role);
	}
}