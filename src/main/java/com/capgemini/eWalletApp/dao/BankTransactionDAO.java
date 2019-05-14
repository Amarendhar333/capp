package com.capgemini.eWalletApp.dao;
import com.capgemini.eWalletApp.beans.*;
public interface BankTransactionDAO
{
	public void depositAmount(long userId,CardDetails cardDetails,BankAccount bankAccount,double amount);
	public void withdrawAmount(long loginId,BankAccount bankAccount,double amount);
	public void depositAmountSecond(long userId,int cvv,BankAccount bankAccount,double amount);
}
