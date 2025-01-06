package com.fdmgroup.BankingApplication.account;

public class AccountDto {

	private String accountType;
	private Long customerId;

	public AccountDto() {
		super();
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
}