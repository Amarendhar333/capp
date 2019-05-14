package com.capgemini.eWalletApp.dao;
import com.capgemini.eWalletApp.beans.*;

public interface AccountDAO
{
	public void createAccount(Account a);
	public double getAccountBalance(long userId);
}
