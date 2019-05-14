package com.capgemini.eWalletApp.service;

import java.util.List;

import javax.persistence.EntityManagerFactory;

import com.capgemini.eWalletApp.beans.Transaction;
import com.capgemini.eWalletApp.dao.TransactionDAOImpl;

public class TransactionServiceImpl implements TransactionService
{
	TransactionDAOImpl tdao;
	public TransactionServiceImpl(EntityManagerFactory emFactory)
	{
		tdao = new TransactionDAOImpl(emFactory);
	}
	public List<Transaction> printTransactions()
	{
		return tdao.printTransactions();
	}
	
}
