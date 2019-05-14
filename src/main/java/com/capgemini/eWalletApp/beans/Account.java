package com.capgemini.eWalletApp.beans;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Account
{
	@Id
	long userId;  // Phone number
	String password;
	double balance;
	@OneToOne
	@JoinColumn(name="userId")
	Customer c;
	public Account() { }
	public Account(long userId, String password, double balance,Customer c)
	{
		this.userId = userId;
		this.password = password;
		this.balance = balance;
		this.c = c;
	}
	public long getUserId()
	{
		return userId;
	}
	public void setUserId(long userId)
	{
		this.userId = userId;
	}
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	public double getBalance()
	{
		return balance;
	}
	public void setBalance(double balance)
	{
		this.balance = balance;
	}
	public Customer getC() {
		return c;
	}
	public void setC(Customer c) {
		this.c = c;
	}
	
}
