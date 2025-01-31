package com.fdmgroup.BankingApplication.customer;

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

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

	private CustomerService customerService;

	@Autowired
	public CustomerController(CustomerService customerService) {
		super();
		this.customerService = customerService;
	}

//	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping
	public ResponseEntity<List<Customer>> getCustomers() {
		List<Customer> customers = customerService.getCustomers();
		return ResponseEntity.ok(customers);
	}

	@PostMapping
	public ResponseEntity<Customer> createCustomer(@RequestBody CustomerDto request) {
		Customer createdCustomer = customerService.createCustomer(request);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(createdCustomer.getCustomerId()).toUri();
		return ResponseEntity.created(location).body(createdCustomer);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Customer> getCustomer(@PathVariable long id) {
		Customer customer = customerService.getCustomerById(id);
		return ResponseEntity.ok(customer);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable long id, @RequestBody CustomerDto request) {
		Customer updatedCustomer = customerService.updateCustomer(request, id);
		return ResponseEntity.ok(updatedCustomer);
	}

	@DeleteMapping("/{id}")
	public void deleteCustomer(@PathVariable long id) {
		customerService.deleteCustomer(id);
	}
}