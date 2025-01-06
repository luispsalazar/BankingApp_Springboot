package com.fdmgroup.BankingApplication.account;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fdmgroup.BankingApplication.customer.Customer;
import com.fdmgroup.BankingApplication.customer.CustomerService;

@Service
public class AccountService {

	private AccountRepository accountRepo;
	private CustomerService customerService;
	private final RestTemplate restTemplate;

	@Autowired
	public AccountService(AccountRepository accountRepo, CustomerService customerService, RestTemplate restTemplate) {
		super();
		this.accountRepo = accountRepo;
		this.customerService = customerService;
		this.restTemplate = restTemplate;
	}

	public Account createAccount(AccountDto accountDto) {
		Account account = null;
		if (accountDto.getAccountType().equals("checking")) {
			account = new CheckingAccount();
		} else if (accountDto.getAccountType().equals("savings")) {
			account = new SavingsAccount();
		}
		Customer customer = customerService.getCustomerById(accountDto.getCustomerId());
		account.setCustomer(customer);
		account = accountRepo.save(account);
		return account;
	}

	public List<Account> getAccounts() {
		return accountRepo.findAll();
	}

	public Account getAccountById(long accountId) {
		Optional<Account> accountOpt = accountRepo.findById(accountId);
		if (!accountOpt.isPresent()) {
			throw new AccountNotFoundException("Account with id: " + accountId + " was not found");
		}
		return accountOpt.get();
	}

	public void deleteAccount(long id) {
		accountRepo.deleteById(id);
	}

	public List<Account> getAccountsByCity(String city) {
		return accountRepo.findAccountsByCity(city);
	}
}