package com.capgemini.eWalletApp.service;

import com.capgemini.eWalletApp.beans.BankAccount;
import com.capgemini.eWalletApp.beans.CardDetails;

public interface BankTransactionService
{
	public void depositAmount(long userId,CardDetails cardDetails,BankAccount bankAccount,double amount);
	public void withdrawAmount(long loginId,BankAccount bankAccount,double amount);
	public void depositAmountSecond(long userId,int cvv,BankAccount bankAccount,double amount);
}
