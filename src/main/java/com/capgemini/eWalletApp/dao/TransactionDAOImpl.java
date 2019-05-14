package com.capgemini.eWalletApp.dao;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import com.capgemini.eWalletApp.beans.*;
public class TransactionDAOImpl implements TransactionDAO
{
	EntityManagerFactory emf;
	public TransactionDAOImpl(EntityManagerFactory emf)
	{
		this.emf = emf;
	}
	public List<Transaction> printTransactions()
	{
		EntityManager eManager = emf.createEntityManager();
		List<Transaction> tlist = eManager.createQuery("SELECT t from Transaction t").getResultList();
		eManager.close();
		return tlist;
		
	}
}
