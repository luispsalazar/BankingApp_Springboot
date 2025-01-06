package com.fdmgroup.BankingApplication.account;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "SAVINGS_ACCOUNT")
public class SavingsAccount extends Account {

	@Id
	private long accountId;

	private double interestRate;

	public SavingsAccount() {
		super();
	}

	public SavingsAccount(long accountId, double interestRate) {
		super();
		this.accountId = accountId;
		this.interestRate = interestRate;
	}

	public SavingsAccount(double interestRate) {
		super();
		this.interestRate = interestRate;
	}

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	@Override
	public String toString() {
		return "SavingsAccount [accountId=" + accountId + ", interestRate=" + interestRate + "]";
	}
}