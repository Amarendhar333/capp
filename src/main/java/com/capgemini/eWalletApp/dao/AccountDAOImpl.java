package com.capgemini.eWalletApp.dao;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import com.capgemini.eWalletApp.beans.*;

public class AccountDAOImpl implements AccountDAO
{
	EntityManagerFactory emf;
	/*public void setEmf(EntityManagerFactory emf)
	{
		this.emf = emf;
	} */
	public AccountDAOImpl(EntityManagerFactory emf)
	{
		this.emf = emf;
	}
	public void createAccount(Account a)
	{
		EntityManager eManager = emf.createEntityManager();
		EntityTransaction trans = eManager.getTransaction();
		trans.begin();
		eManager.persist(a.getC());
		eManager.persist(a);
		trans.commit();
		eManager.close();
		
		
	}
	public double getAccountBalance(long userId)
	{
		EntityManager eManager = emf.createEntityManager();
		Account a = eManager.find(Account.class, userId);
		eManager.close();
		if(a!=null)
			return a.getBalance();
		return 0.0;
		
	}
	public boolean verifyLogin(long userId,String pwd)
	{
		EntityManager eManager = emf.createEntityManager();
		Account a = eManager.find(Account.class, userId);
		eManager.close();
		if(a!=null)
		{
			if(a.getPassword().equals(pwd))
				return true;
		}
		return false;
	}
}
