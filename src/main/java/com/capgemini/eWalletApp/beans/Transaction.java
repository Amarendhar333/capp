package com.capgemini.eWalletApp.beans;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Transaction
{
	@Id
	@SequenceGenerator(name="seq",sequenceName="trans_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seq")
	int transactionId;
	Date transactionDate;
	double transactionAmount;
	char transactionType;
	@OneToOne(mappedBy="t")
	BankTransaction bt;
	@OneToOne(mappedBy="t")
	FundTransfer ft;
	public Transaction(){ }
	public Transaction(Date transactionDate,double transactionAmount, char transactionType)
	{
		this.transactionDate = transactionDate;
		this.transactionAmount = transactionAmount;
		this.transactionType = transactionType;
	}
	public int getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	public Date getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
	public double getTransactionAmount() {
		return transactionAmount;
	}
	public void setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	public char getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(char transactionType) {
		this.transactionType = transactionType;
	}
	public BankTransaction getBt() {
		return bt;
	}
	public void setBt(BankTransaction bt) {
		this.bt = bt;
	}
	public FundTransfer getFt() {
		return ft;
	}
	public void setFt(FundTransfer ft) {
		this.ft = ft;
	}
	
}
