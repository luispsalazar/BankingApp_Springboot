package com.fdmgroup.BankingApplication.employee;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

	private EmployeeRepository employeeRepo;

	@Autowired
	public EmployeeService(EmployeeRepository employeeRepo) {
		super();
		this.employeeRepo = employeeRepo;
	}

	public List<Employee> getEmployees() {
		return employeeRepo.findAll();
	}

	public Employee createEmployee(EmployeeDto employeeDto) {
		Employee employee = null;
		if (employeeDto.getRole().contains(Role.TELLER)) {
			employee = new Teller();
		} else if (employeeDto.getRole().contains(Role.MANAGER)) {
			employee = new Manager();
		} else {
			throw new IllegalArgumentException("Invalid role: " + employeeDto.getRole());
		}
		employee.setName(employeeDto.getName());
		employee.setRole(employeeDto.getRole());
		return employeeRepo.save(employee);
	}

	public Employee getEmployeeById(long employeeId) {
		Optional<Employee> employeeOpt = employeeRepo.findById(employeeId);
		if (!employeeOpt.isPresent()) {
			throw new EmployeeNotFoundException("Employee with id: " + employeeId + " was not found");
		}
		return employeeOpt.get();
	}

	public Employee updateEmployee(EmployeeDto request, long id) {
		Employee employeeReceived = employeeRepo.findById(id)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee not fount"));
		employeeReceived.setName(request.getName());
		return employeeRepo.save(employeeReceived);
	}

	public void deleteEmployee(long id) {
		employeeRepo.deleteById(id);
	}
}