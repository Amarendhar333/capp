package com.capgemini.eWalletApp.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.EntityManagerFactory;

import com.capgemini.eWalletApp.dao.FundTransferDAOImpl;
import com.capgemini.eWalletApp.exception.ValidationException;

public class FundTransferServiceImpl implements FundTransferService
{
	FundTransferDAOImpl fdao;
	public FundTransferServiceImpl(EntityManagerFactory emFactory)
	{
		fdao = new FundTransferDAOImpl(emFactory);
	}
	public void fundTransfer(long senderUserId, long receiverUserId, String name, double amount)
	{
		try{
			if(isValidReceiverId(receiverUserId) && isValidAmount(amount))
				fdao.fundTransfer(senderUserId, receiverUserId, name, amount);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
	}
	public boolean isValidReceiverId(long receiverUserId)
	{
		Pattern p = Pattern.compile("[0-9]{10}");
		Matcher m = p.matcher(String.valueOf(receiverUserId));
		if(m.matches())
			return true;
		throw new ValidationException("Receiver User Id must be 10 digits");
	}
	public boolean isValidAmount(double amount)
	{
		if(amount>0)
			return true;
		throw new ValidationException("Amount must be greater than 0");
	}
}
