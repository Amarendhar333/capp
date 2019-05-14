package com.capgemini.eWalletApp.dao;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import com.capgemini.eWalletApp.beans.*;
import com.capgemini.eWalletApp.exception.ValidationException;
public class BankTransactionDAOImpl implements BankTransactionDAO
{
	EntityManagerFactory emf;
	public BankTransactionDAOImpl(EntityManagerFactory emf)
	{
		this.emf = emf;
	}
	public void depositAmount(long userId,CardDetails cardDetails,BankAccount bankAccount ,double amount)
	{
		EntityManager eManager = emf.createEntityManager();
		EntityTransaction trans = eManager.getTransaction();
		trans.begin();
		eManager.persist(cardDetails);
		Account a = eManager.find(Account.class, userId);
		if(a!=null)
			a.setBalance(a.getBalance()+amount);
		Transaction t = new Transaction(new Date(), amount,'D');
		
		BankTransaction bt = new BankTransaction(t,bankAccount.getAccountNumber(),bankAccount.getAccountHolderName(),bankAccount.getIfscCode(),'D');
		eManager.persist(t);
		eManager.persist(bt);
		trans.commit();
		eManager.close();
	}
	public void depositAmountSecond(long userId,int cvv,BankAccount bankAccount,double amount)
	{
		EntityManager eManager = emf.createEntityManager();
		EntityTransaction trans = eManager.getTransaction();
		trans.begin();
		CardDetails cd = (CardDetails)eManager.createQuery("select cd from CardDetails cd where cd.cvv=cvv").getSingleResult();
		if(cd!=null)
		{
			Account a = eManager.find(Account.class, userId);
			if(a!=null)
				a.setBalance(a.getBalance()+amount);
			Transaction t = new Transaction(new Date(), amount,'D');
			
			BankTransaction bt = new BankTransaction(t,bankAccount.getAccountNumber(),bankAccount.getAccountHolderName(),bankAccount.getIfscCode(),'D');
			eManager.persist(t);
			eManager.persist(bt);
		}
		trans.commit();
		eManager.close();
	}
	public void withdrawAmount(long loginId,BankAccount bankAccount,double amount)
	{
		EntityManager eManager = emf.createEntityManager();
		EntityTransaction trans = eManager.getTransaction();
		trans.begin();
		Account a = eManager.find(Account.class, loginId);
		if(a!=null)
			a.setBalance(a.getBalance()-amount);
		/*if(a!=null)
		{
			try{
				if(isValidWithdraw(a.getBalance(),amount))
					a.setBalance(a.getBalance()-amount);
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}*/
		Transaction t = new Transaction(new Date(),amount,'W');
		
		BankTransaction bt = new BankTransaction(t,bankAccount.getAccountNumber(),bankAccount.getAccountHolderName(),bankAccount.getIfscCode(),'W');
		eManager.persist(t);
		eManager.persist(bt);
		eManager.flush();
		trans.commit();
		eManager.close();
	}
	//public boolean checkCardDetailsEmpty(CardDetails)
	public boolean isCardDetailsEmpty()
	{
		EntityManager eManager = emf.createEntityManager();
		List<CardDetails> cdList = eManager.createQuery("SELECT cd FROM CardDetails cd").getResultList();
		eManager.close();
		if(cdList.isEmpty())
			return true;
		return false;
	}
	private boolean isValidWithdraw(double balance,double amount)
	{
		if(balance>amount)
			return true;
		throw new ValidationException("Sorry!!! Insufficient Funds");
	}
}
