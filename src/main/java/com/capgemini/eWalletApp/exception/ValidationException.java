package com.capgemini.eWalletApp.exception;

public class ValidationException extends RuntimeException
{
	String message;
	public ValidationException(String message)
	{
		this.message = message;
		
	}
	public String toString()
	{
		return message;
	}
}
