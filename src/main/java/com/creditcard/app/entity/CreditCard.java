package com.creditcard.app.entity;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.sun.istack.NotNull;

@Entity
public class CreditCard implements Serializable{
	@Id
	@NotNull
	@Column(name="creditCardNumber")
	private BigInteger creditCardNumber;
	
	@Column(name="name")
	private String name;
	
	@Column(name="balance")
	private double balance;
	
	@Column(name="cardLimit")
	private double limit;

	@Override
	public String toString() {
		return "CreditCard [creditCardNumber=" + creditCardNumber + ", name=" + name + ", balance=" + balance
				+ ", limit=" + limit + "]";
	}

	
	public CreditCard() {}
	
	public CreditCard(BigInteger creditCardNumber, String name, double balance, double limit) {
		super();
		this.creditCardNumber = creditCardNumber;
		this.name = name;
		this.balance = balance;
		this.limit = limit;
	}


	public BigInteger getCreditCardNumber() {
		return creditCardNumber;
	}

	public void setCreditCardNumber(BigInteger creditCardNumber) {
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
	
	
}
