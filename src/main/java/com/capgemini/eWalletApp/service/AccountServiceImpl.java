package com.capgemini.eWalletApp.service;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.EntityManagerFactory;

import com.capgemini.eWalletApp.beans.Account;
import com.capgemini.eWalletApp.dao.AccountDAOImpl;
import com.capgemini.eWalletApp.exception.ValidationException;

public class AccountServiceImpl implements AccountService
{
	AccountDAOImpl adao;
    public AccountServiceImpl(EntityManagerFactory emFactory)
    {
    	adao = new AccountDAOImpl(emFactory);
    }
	public void createAccount(Account a)
	{
		try{
			if(validateUserId(a.getC().getUserId()) && validateFirstName(a.getC().getFirstName()) && validateEmail(a.getC().getEmail()) 
					&& validateAddress(a.getC().getAddress()) && validatePassword(a.getPassword()))
				  adao.createAccount(a);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
	}
	public double getAccountBalance(long userId)
	{
		return adao.getAccountBalance(userId);
	}
	public boolean verifyLogin(long userId,String pwd)
	{
		try{
			if(validateUserId(userId))
				return adao.verifyLogin(userId, pwd);
		}
		catch(Exception e)
		{
			System.out.println(e);
			
		}
		return false;
	}
	private boolean validateUserId(long userId)
	{
		Pattern p = Pattern.compile("[0-9]{10}");
		Matcher m = p.matcher(String.valueOf(userId));
		if(m.matches())
			return true;
		throw new ValidationException("User Id / Mobile Number must be 10 digits");
	}
	private boolean validateFirstName(String firstName)
	{
		if(firstName!=null)
			return true;
		throw new ValidationException("FirstName cannot be empty");
	}
	private boolean validateEmail(String email)
	{
		Pattern p = Pattern.compile("[a-zA-Z0-9]*@[a-zA-Z]*\\.[a-zA-Z]*");
		Matcher m = p.matcher(email);
		if(m.matches())
			return true;
		throw new ValidationException("Email id should be in format.. ex: abc123@def.com");
	}
	private boolean validateAddress(String address)
	{
		if(address!=null)
			return true;
		throw new ValidationException("Address cannot be empty");
	}
	private boolean validatePassword(String password)
	{
		Pattern p = Pattern.compile(".{8}.*");
		Matcher m = p.matcher(password);
		if(m.matches())
			return true;
		throw new ValidationException("Password must contain atleast 8 characters");
	}
}
