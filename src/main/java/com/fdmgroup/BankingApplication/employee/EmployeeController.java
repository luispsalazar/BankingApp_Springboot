package com.fdmgroup.BankingApplication.employee;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fdmgroup.BankingApplication.customer.Customer;
import com.fdmgroup.BankingApplication.customer.CustomerDto;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

	private EmployeeService employeeService;

	@Autowired
	public EmployeeController(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}

//	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping
	public ResponseEntity<List<Employee>> getEmployees() {
		List<Employee> employees = employeeService.getEmployees();
		return ResponseEntity.ok(employees);
	}

	@PostMapping
	public ResponseEntity<Employee> createEmployee(@RequestBody EmployeeDto request) {
		Employee createdEmployee = employeeService.createEmployee(request);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(createdEmployee.getEmployeeId()).toUri();
		return ResponseEntity.created(location).body(createdEmployee);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmployee(@PathVariable long id) {
		Employee employees = employeeService.getEmployeeById(id);
		return ResponseEntity.ok(employees);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable long id, @RequestBody EmployeeDto request) {
		Employee updatedEmployee = employeeService.updateEmployee(request, id);
		return ResponseEntity.ok(updatedEmployee);
	}

	@DeleteMapping("/{id}")
	public void deleteEmployee(@PathVariable long id) {
		employeeService.deleteEmployee(id);
	}
}