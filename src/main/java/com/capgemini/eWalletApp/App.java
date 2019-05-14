 package com.capgemini.eWalletApp;
import com.capgemini.eWalletApp.beans.*;
import com.capgemini.eWalletApp.service.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class App 
{
	//static long userId;
    public static void main( String[] args )
    {
        EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("EWALLET");
        AccountServiceImpl aService = new AccountServiceImpl(emFactory);
        BankTransactionServiceImpl btService = new BankTransactionServiceImpl(emFactory);
        FundTransferServiceImpl ftService = new FundTransferServiceImpl(emFactory);
        TransactionServiceImpl tService = new TransactionServiceImpl(emFactory);
        Scanner sc = new Scanner(System.in);
        do{
        	System.out.println("Welcome to the eWallet Application \n-------------------------------------");
        	System.out.println("1. Sign Up"); 
        	System.out.println("2. Sign In");
        	System.out.println("3. Exit the Application");
        	System.out.println("Please chose any one of the above options");
        	int optMain = sc.nextInt();
        	sc.nextLine();
        	switch(optMain)
        	{
        	case 1 : 
        		System.out.println("Enter the required details to register.....");
        		System.out.print("Enter Phone Number(User Id)(Must be 10 digits)   :   ");
        	    long userId = sc.nextLong();sc.nextLine();
        	    System.out.print("\nEnter First Name (Cannot be null)  :   ");
        	    String firstName = sc.nextLine();
        	    System.out.print("\nEnter Last Name  :  ");
        	    String lastName = sc.nextLine();
        	    System.out.print("\nEnter emaild id (e.g.. abc@xyz.com)  :  ");
        	    String email = sc.nextLine();
        	    System.out.print("\nEnter address (as a string)  :  ");
        	    String address = sc.nextLine();
        	    System.out.print("\nEnter password (must have atleast 8 characters)  :  ");
        	    String password = sc.nextLine();
        	    System.out.print("\nConfirm Password  :  ");
        	    String confirmPwd = sc.nextLine();
        	    if(password.equals(confirmPwd))
        	    {
        	    	System.out.println("Are you sure you want to save the details? (Yes/No)");
        	    	String yesNo = sc.nextLine();
        	    	if(yesNo.equalsIgnoreCase("yes"))
        	    	{
        	    		aService.createAccount(new Account(userId,password,0.0, new Customer(userId,firstName,lastName,email,address)));
        	    	}
        	    	else if(yesNo.equalsIgnoreCase("no"))
        	    		System.out.println("Sorry!! We have to store your details.....");
        	    	else
        	    		System.out.println("Invalid!! Only yes/no are applicable");
        	    }
        	    else
        	    	System.out.println("Passwords does not match");
        	    System.out.println("Your account balance is Rs."+aService.getAccountBalance(userId));
        	    break;
        	case 2 :
        		System.out.print("Enter User Id : ");
        		long loginId = sc.nextLong();
        		sc.nextLine();
        		System.out.print("Enter Password : ");
        		String pwd = sc.nextLine();
        		if(aService.verifyLogin(loginId,pwd))
        		{
        			System.out.println("You have successfully logged in!!!!!");
        			boolean login = true;
        			do{
        			System.out.println("Please chose any of the below action");
        			System.out.println("1. Show the account balance");
        			System.out.println("2. Deposit to account");
        			System.out.println("3. Withdraw from account");
        			System.out.println("4. Fund Transacfer to other account");
        			System.out.println("5. Get all transactions");
        			System.out.println("6. Sign Out");
        			int optLogin = sc.nextInt();
        			sc.nextLine();
        			switch(optLogin)
        			{
        			case 1 : 
        				System.out.println("Your account balance is Rs."+aService.getAccountBalance(loginId));
        				break;
        			case 2 :
        				if(btService.isCardDetailsEmpty())
        				{
        					System.out.println("No existing cards not found!!! store a new one...");
        					System.out.print("Enter card number : ");
        					long cardNumber = sc.nextLong();
        					sc.nextLine();
        					System.out.print("Enter cvv : ");
        					int cvv = sc.nextInt();
        					sc.nextLine();
        					System.out.print("Enter expiry day : ");
        					String day = sc.nextLine();
        					System.out.print("Enter expiry month : ");
        					String month = sc.nextLine();
        					System.out.print("Enter expiry year : ");
        					String year = sc.nextLine();
        					System.out.print("Enter Card Holder Name : ");
        					String name = sc.nextLine();
        					System.out.print("Enter bank account number : ");
        					long accountNumber = sc.nextLong();
        					sc.nextLine();
        					System.out.print("Enter ifsc code : ");
        					String ifsc = sc.nextLine();
        					System.out.print("Enter amount to be deposited : ");
        					double amount = sc.nextDouble();
        					sc.nextLine();
        					btService.depositAmount(loginId,new CardDetails(cardNumber,cvv,parseDate(day+"-"+year+"-"+month),name), new BankAccount(accountNumber,name,ifsc), amount);
        					//System.out.println("Amount successfully deposited");
        				}
        				else
        				{
        					System.out.println("one card found!!!!!");
        					System.out.print("Enter cvv : ");
        					int cvv = sc.nextInt();
        					sc.nextLine();
        					System.out.print("Enter Card Holder Name : ");
        					String name = sc.nextLine();
        					System.out.print("Enter bank account number : ");
        					long accountNumber = sc.nextLong();
        					sc.nextLine();
        					System.out.print("Enter ifsc code : ");
        					String ifsc = sc.nextLine();
        					System.out.print("Enter amount to be deposited : ");
        					double amount = sc.nextDouble();
        					sc.nextLine();
        					btService.depositAmountSecond(loginId,cvv, new BankAccount(accountNumber,name,ifsc), amount);
        					//System.out.println("Amount successfully deposited");
        				}
        				break;
        			case 3 :
        				System.out.print("Enter Account Number : ");
        				long accountNumber = sc.nextLong();
        				sc.nextLine();
        				System.out.print("Enter Name : ");
        				String name = sc.nextLine();
        				System.out.print("Enter IFSC Code : ");
        				String ifsc = sc.nextLine();
        				System.out.print("Enter amount to be withdrawn : ");
        				double amount = sc.nextDouble();
        				sc.nextLine();
        				btService.withdrawAmount(loginId,new BankAccount(accountNumber,name,ifsc), amount);
        				System.out.println("Amount successfully withdrawn");
        				break;
        			case 4 :
        				System.out.print("Enter the receiver User Id : ");
        				long receiverUserId = sc.nextLong();
        				sc.nextLine();
        				System.out.print("Enter the receiver name : ");
        				String receiverName = sc.nextLine();
        				System.out.print("Enter the amount to be transferred : ");
        				double transferAmount = sc.nextDouble();
        				sc.nextLine();
        				ftService.fundTransfer(loginId, receiverUserId, receiverName, transferAmount);
        				System.out.println("Fund transferred to receiver successfully");
        				break;
        			case 5 :
        				List<Transaction> tlist = tService.printTransactions();
        				for(Transaction t : tlist)
        				{
        					System.out.print("trans_id : "+t.getTransactionId()+",trans_date :    "+t.getTransactionDate()+",amount =   "+t.getTransactionAmount()+"   ");
        					if(t.getTransactionType()=='W' || t.getTransactionType()=='D')
        					{
        						BankTransaction bt = t.getBt();
        						if(bt!=null)
        							System.out.println(",account_num : "+bt.getAccountNumber()+",name :   "+bt.getAccountHolderName()+",trans_type :   "+t.getTransactionType()+" ");
        					}
        					else
        					{
        						FundTransfer ft = t.getFt();
        						if(ft!=null)
        							System.out.println(",receiver_id : "+ft.getUserId()+",name :   "+ft.getName()+",trans_type :   "+t.getTransactionType());
        					}
        				}
        				break;
        			case 6 :
        				login = false;
        				break;
        			default : 
        				System.out.println("Invalid Option!!");
        			}
        			System.out.println();
        			
        			}while(login);
        		}
        		else
        			System.out.println("User Id and Password does not match");
        		break;
        	case 3 : 
        		System.exit(0);
        	default : 
        		System.out.println("Invalid option!!!!!!");
        	}
        }while(true);
        	
    }
    public static Date parseDate(String date)
    {
    	try{
    		return new SimpleDateFormat("dd-MM-yyyy").parse(date);
    	}
    	catch(ParseException e)
    	{
    		return null;
    	}
    }
}
