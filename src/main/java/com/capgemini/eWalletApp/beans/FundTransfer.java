package com.capgemini.eWalletApp.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class FundTransfer
{
	@Id
	@SequenceGenerator(name="seq",sequenceName="fundtrans_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seq")
	int id;
	@OneToOne
	@JoinColumn(name="transactionId")
	Transaction t;
	long userId;
	String name;
	public FundTransfer() { }
	public FundTransfer(Transaction t,long userId, String name) {
		super();
		this.userId = userId;
		this.name = name;
		this.t = t;
	}
	
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Transaction getT() {
		return t;
	}
	public void setT(Transaction t) {
		this.t = t;
	}
	
}
