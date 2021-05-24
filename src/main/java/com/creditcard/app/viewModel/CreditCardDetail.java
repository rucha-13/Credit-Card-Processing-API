package com.creditcard.app.viewModel;

import io.swagger.annotations.ApiModel;

@ApiModel(description = "Details about credit card")
public class CreditCardDetail {

	private String creditCardNumber;

	private String name;

	private double balance;

	private double limit;

	public CreditCardDetail() {}

	public CreditCardDetail(String creditCardNumber, String name, double balance, double limit) {
		this.creditCardNumber = creditCardNumber;
		this.name = name;
		this.balance = balance;
		this.limit = limit;
	}

	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public double getLimit() {
		return limit;
	}

	public void setLimit(double limit) {
		this.limit = limit;
	}
	
	@Override
	public String toString() {
		return "CreditCard [creditCardNumber=" + creditCardNumber + ", name=" + name + ", balance=" + balance
				+ ", limit=" + limit + "]";
	}

}
