package com.fdmgroup.BankingApplication.account;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "CHECKING_ACCOUNT")
public class CheckingAccount extends Account {

	@Id
	private long accountId;
	private int nextCheckNumber;

	public CheckingAccount() {
		super();
	}

	public CheckingAccount(int nextCheckNumber) {
		super();
		this.nextCheckNumber = nextCheckNumber;
	}

	public CheckingAccount(long accountId, int nextCheckNumber) {
		super();
		this.accountId = accountId;
		this.nextCheckNumber = nextCheckNumber;
	}

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	public int getNextCheckNumber() {
		return nextCheckNumber;
	}

	public void setNextCheckNumber(int nextCheckNumber) {
		this.nextCheckNumber = nextCheckNumber;
	}

	@Override
	public String toString() {
		return "CheckingAccount [accountId=" + accountId + ", nextCheckNumber=" + nextCheckNumber + "]";
	}
}