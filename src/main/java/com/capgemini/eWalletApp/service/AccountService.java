package com.capgemini.eWalletApp.service;
import com.capgemini.eWalletApp.dao.*;
import com.capgemini.eWalletApp.beans.*;
public interface AccountService
{
	public void createAccount(Account a);
	public double getAccountBalance(long userId);
}
