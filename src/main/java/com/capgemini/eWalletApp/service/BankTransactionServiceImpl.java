package com.capgemini.eWalletApp.service;

import javax.persistence.EntityManagerFactory;

import com.capgemini.eWalletApp.beans.BankAccount;
import com.capgemini.eWalletApp.beans.CardDetails;
import com.capgemini.eWalletApp.dao.BankTransactionDAOImpl;
import com.capgemini.eWalletApp.exception.ValidationException;

public class BankTransactionServiceImpl implements BankTransactionService
{
	BankTransactionDAOImpl btdao;
	public BankTransactionServiceImpl(EntityManagerFactory emFactory)
	{
		btdao = new BankTransactionDAOImpl(emFactory);
	}
	public void depositAmount(long userId,CardDetails cardDetails, BankAccount bankAccount, double amount)
	{
		try{
			if(isValidAmount(amount))
				btdao.depositAmount(userId,cardDetails, bankAccount, amount);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	public void depositAmountSecond(long userId,int cvv,BankAccount bankAccount,double amount)
	{
		try{
			if(isValidAmount(amount))
				btdao.depositAmountSecond(userId, cvv, bankAccount, amount);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	public void withdrawAmount(long loginId,BankAccount bankAccount, double amount)
	{
		btdao.withdrawAmount(loginId, bankAccount, amount);
	}
	public boolean isCardDetailsEmpty()
	{
		return btdao.isCardDetailsEmpty();
	}
	private boolean isValidAmount(double amount)
	{
		if(amount>0)
			return true;
		throw new ValidationException("Amount must be greater than 0");
	}
}
