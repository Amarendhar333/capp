package com.capgemini.eWalletApp.dao;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import com.capgemini.eWalletApp.beans.*;
import com.capgemini.eWalletApp.exception.ValidationException;

public class FundTransferDAOImpl implements FundTransferDAO
{
	EntityManagerFactory emf;
	public FundTransferDAOImpl(EntityManagerFactory emf)
	{
		this.emf = emf;
	}
	public void fundTransfer(long senderUserId,long receiverUserId,String name,double amount)
	{
		EntityManager eManager = emf.createEntityManager();
		EntityTransaction trans = eManager.getTransaction();
		trans.begin();
		Account sender = eManager.find(Account.class, senderUserId);
		Account receiver = eManager.find(Account.class, receiverUserId);
		if(sender!=null)
		{
			try{
				if(isValidTransfer(sender.getBalance(),amount))
					sender.setBalance(sender.getBalance()-amount);
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
		if(receiver!=null)
			receiver.setBalance(receiver.getBalance()+amount);
		Transaction t = new Transaction(new Date(),amount,'T');
		
		FundTransfer ft = new FundTransfer(t,receiverUserId,name);
		eManager.persist(t);
		eManager.persist(ft);
		trans.commit();
		eManager.close();
	}
	private boolean isValidTransfer(double balance,double amount)
	{
		if(balance>amount)
			return true;
		throw new ValidationException("Sorry!!! Insufficient Funds");
	}
}
