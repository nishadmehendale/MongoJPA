package com.cg.MongoJPA.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Account {
	@Id
	private int accountNumber;
	private double balance;
	private int customerId;
	
	public Account(int accountNumber, double balance, int customerId) {
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.customerId = customerId;
	}

	public Account() {
		
	}


	public void withdraw(double amount) {
		this.balance-=amount;
	}
	
	public void deposit(double amount) {
		this.balance+=amount;
	}
	
	@Override
	public String toString() {
		return "Account [accountNumber=" + accountNumber + ", balance=" + balance + "]";
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	
}
