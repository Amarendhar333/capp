package com.capgemini.eWalletApp.beans;
import javax.persistence.Entity;
import javax.persistence.Id;

import java.util.Date;

import javax.persistence.Column;

@Entity
public class CardDetails
{
	@Id
	long cardNumber;
	int cvv;
	Date expiry;
	String cardHolderName;
	public CardDetails() { }
	public CardDetails(long cardNumber, int cvv, Date expiry, String cardHolderName)
	{
		this.cardNumber = cardNumber;
		this.cvv = cvv;
		this.expiry = expiry;
		this.cardHolderName = cardHolderName;
	}
	public long getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(long cardNumber) {
		this.cardNumber = cardNumber;
	}
	public int getCvv() {
		return cvv;
	}
	public void setCvv(int cvv) {
		this.cvv = cvv;
	}
	public Date getExpiry() {
		return expiry;
	}
	public void setExpiry(Date expiry) {
		this.expiry = expiry;
	}
	public String getCardHolderName() {
		return cardHolderName;
	}
	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}
	
}
