package com.fdmgroup.BankingApplication.account;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {

	AccountService accountService;

	@Autowired
	public AccountController(AccountService accountService) {
		super();
		this.accountService = accountService;
	}

	@GetMapping
	public ResponseEntity<List<Account>> getAccounts() {
		List<Account> accounts = accountService.getAccounts();
		return ResponseEntity.ok(accounts);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Account> getAccountById(@PathVariable long id) {
		Account account = accountService.getAccountById(id);
		return ResponseEntity.ok(account);
	}

	@GetMapping("/city/{city}")
	public ResponseEntity<List<Account>> getAccountsByCity(@PathVariable String city) {
		List<Account> accounts = accountService.getAccountsByCity(city);
		return ResponseEntity.ok(accounts);
	}

	@PostMapping
	public ResponseEntity<Account> createAccount(@RequestBody AccountDto request) {
		Account createdAccount = accountService.createAccount(request);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(createdAccount.getAccountId()).toUri();
		return ResponseEntity.created(location).body(createdAccount);
	}

	@DeleteMapping("/{id}")
	public void deleteAccount(@PathVariable long id) {
		accountService.deleteAccount(id);
	}
}