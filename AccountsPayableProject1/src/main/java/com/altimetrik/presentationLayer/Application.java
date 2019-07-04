package com.altimetrik.presentationLayer;

import java.sql.SQLException;
import java.util.Scanner;

import com.altimetrik.businessLayer.ReceiveEmailWithAttachment;
import com.altimetrik.dataLayer.DatabaseOperations;

public class Application {

	public static void main(String[] args) throws SQLException {
		
		Scanner sc = new Scanner(System.in);
	
		ReceiveEmailWithAttachment mailDetails = new ReceiveEmailWithAttachment();
		String pop3Host = "pop.gmail.com";
		String mailStoreType = "pop3";
		final String userName = "xxxxxxxx@gmail.com";
		final String password = "password";
		mailDetails.receiveEmail(pop3Host, mailStoreType, userName, password);
		
		System.out.println("Welcome To Account Payable Project");
		
		while(true)
		{
			System.out.println("\n------------------------------------------------");
			System.out.println("Menu");
			System.out.println("1.List All Invoices");
			System.out.println("2.Approve Invoice");
			System.out.println("3.Exit");
			System.out.println("\n------------------------------------------------");
			System.out.println("Enter Your Choice : ");
			int choice = sc.nextInt();
			switch(choice)
			{
			case 1:
				DatabaseOperations.displayData();
				break;
			case 2:
				DatabaseOperations.searchData();
				break;
			case 3:
				System.exit(0);
			default:
				System.out.println("Wrong Choice !!");
				
			}
		}

	}

}
