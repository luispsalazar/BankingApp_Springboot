package com.fdmgroup.BankingApplication.customer;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.BankingApplication.account.Account;
import com.fdmgroup.BankingApplication.account.AccountNotFoundException;
import com.fdmgroup.BankingApplication.account.AccountRepository;
import com.fdmgroup.BankingApplication.address.Address;
import com.fdmgroup.BankingApplication.address.AddressDto;
import com.fdmgroup.BankingApplication.address.AddressNotFoundException;
import com.fdmgroup.BankingApplication.address.AddressRepository;

@Service
public class CustomerService {

	private CustomerRepository customerRepo;
	private AddressRepository addressRepo;
	private AccountRepository accountRepo;

	@Autowired
	public CustomerService(CustomerRepository customerRepo, AddressRepository addressRepo,
			AccountRepository accountRepo) {
		super();
		this.customerRepo = customerRepo;
		this.addressRepo = addressRepo;
		this.accountRepo = accountRepo;
	}

	public Customer createCustomer(CustomerDto customerDto) {
		Customer customer = null;
		if (customerDto.getCustomerType().equals("person")) {
			customer = new Person();
		} else if (customerDto.getCustomerType().equals("company")) {
			customer = new Company();
		}
		AddressDto addressDto = customerDto.getAddressDto();
		Address address = new Address();
		BeanUtils.copyProperties(addressDto, address);
		address = addressRepo.save(address);
		BeanUtils.copyProperties(customerDto, customer);
		customer = customerRepo.save(customer);
		return setAddress(customer.getCustomerId(), address.getAddressId());
	}

	public List<Customer> getCustomers() {
		return customerRepo.findAll();
	}

	public Customer getCustomerById(long customerId) {
		Optional<Customer> customerOpt = customerRepo.findById(customerId);
		if (!customerOpt.isPresent()) {
			throw new CustomerNotFoundException("Customer with id: " + customerId + " was not found");
		}
		return customerOpt.get();
	}

	public Customer setAddress(long customerId, Integer addressId) {
		Customer customerReceived = getCustomerById(customerId);
		Address addressReceived = addressRepo.findById(addressId)
				.orElseThrow(() -> new AddressNotFoundException("Address not found"));
		customerReceived.setAddress(addressReceived);
		return customerRepo.save(customerReceived);
	}

	public Customer addAccount(long customerId, long accountId) {
		Customer customerReceived = customerRepo.findById(customerId)
				.orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
		Account accountReceived = accountRepo.findById(accountId)
				.orElseThrow(() -> new AccountNotFoundException("Account not found"));
		customerReceived.getAccounts().add(accountReceived);
		return customerRepo.save(customerReceived);
	}

	public Customer updateCustomer(CustomerDto request, long id) {
		Customer customerReceived = customerRepo.findById(id)
				.orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
		customerReceived.setName(request.getName());
		return customerRepo.save(customerReceived);
	}

	public void deleteCustomer(long id) {
		customerRepo.deleteById(id);
	}
}