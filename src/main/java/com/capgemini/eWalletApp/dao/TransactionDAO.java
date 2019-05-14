package com.capgemini.eWalletApp.dao;

import java.util.List;

import com.capgemini.eWalletApp.beans.Transaction;

public interface TransactionDAO
{
	public List<Transaction> printTransactions();
}
