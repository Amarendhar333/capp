package com.capgemini.eWalletApp.dao;
import com.capgemini.eWalletApp.beans.*;
public interface FundTransferDAO
{
	public void fundTransfer(long presentUserId,long userId,String name,double amount);
}
