package com.capgemini.eWalletApp.service;

public interface FundTransferService
{
	public void fundTransfer(long senderUserId,long receiverUserId,String name,double amount);
}
