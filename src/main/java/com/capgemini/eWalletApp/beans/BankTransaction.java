package com.capgemini.eWalletApp.beans;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class BankTransaction
{ 
	@Id
	@SequenceGenerator(name="seq",sequenceName="banktrans_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seq")
	int id;
	@OneToOne
	@JoinColumn(name="transactionId")
	Transaction t;
	long AccountNumber;
	String AccountHolderName;
	String ifscCode;
	char transactionType;
	
	public BankTransaction(){ }
	public BankTransaction(Transaction t,long accountNumber, String accountHolderName, String ifscCode,
			char transactionType)
	{
		AccountNumber = accountNumber;
		AccountHolderName = accountHolderName;
		ifscCode = ifscCode;
		this.transactionType = transactionType;
		this.t = t;
	}
	public long getAccountNumber() {
		return AccountNumber;
	}
	public void setAccountNumber(long accountNumber) {
		AccountNumber = accountNumber;
	}
	public String getAccountHolderName() {
		return AccountHolderName;
	}
	public void setAccountHolderName(String accountHolderName) {
		AccountHolderName = accountHolderName;
	}
	public String getIfscCode() {
		return ifscCode;
	}
	public void setIfscCode(String ifscCode) {
		ifscCode = ifscCode;
	}
	public char getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(char transactionType) {
		this.transactionType = transactionType;
	}
	public Transaction getT() {
		return t;
	}
	public void setT(Transaction t) {
		this.t = t;
	}
	
}
