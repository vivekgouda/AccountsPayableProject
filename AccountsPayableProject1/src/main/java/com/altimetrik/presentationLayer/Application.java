package com.altimetrik.presentationLayer;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.text.PDFTextStripper;

import com.altimetrik.businessLayer.ReceiveEmailWithAttachment;
import com.altimetrik.dataLayer.DatabaseOperations;
import com.altimetrik.dataLayer.FindRequiredDetails;

public class Application {

	public static String rawText;
	private static final String invoiceNo = "Invoice No";
	private static final String invoiceDate = "Invoice Date";
	private static final String customerPO = "Customer P.O.";
	private static final String customerAddress = "Ship To";

	public static void main(String[] args) throws SQLException, InvalidPasswordException, IOException {

		Scanner sc = new Scanner(System.in);

		ReceiveEmailWithAttachment mailDetails = new ReceiveEmailWithAttachment();
		String pop3Host = "pop.gmail.com";
		String mailStoreType = "pop3";
		final String userName = "vivzgouda@gmail.com";
		final String password = "vivek1998";
		mailDetails.receiveEmail(pop3Host, mailStoreType, userName, password);

		FindRequiredDetails details = new FindRequiredDetails();
		File file = new File(ReceiveEmailWithAttachment.myText);
		PDDocument pdfDocument = PDDocument.load(file);
		PDFTextStripper pdfStripper = new PDFTextStripper();
		rawText = pdfStripper.getText(pdfDocument);

		long invoiceNum = details.findInvoiceNo(invoiceNo);
		String custInvoiceDate = details.findInvoiceDate(invoiceDate);
		long custPONum = details.findCustomerPO(customerPO);
		String custAddress = details.findCustomerAddress(customerAddress);
		double totalInvoice = details.findTotalInvoice();

		pdfDocument.close();

		DatabaseOperations.databaseConnection();
		DatabaseOperations.insertDataIntoDB(invoiceNum, custInvoiceDate, custPONum, custAddress, totalInvoice);
		DatabaseOperations.databaseConnectionClose();

		System.out.println("\n------------------------------------------------");
		System.out.println("Welcome To Account Payable Project");

		while (true) {
			System.out.println("\n------------------------------------------------");
			System.out.println("Menu");
			System.out.println("1.List All Invoices");
			System.out.println("2.Approve Invoice");
			System.out.println("3.Exit");
			System.out.println("\n------------------------------------------------");
			System.out.print("Enter Your Choice : ");
			int choice = sc.nextInt();
			switch (choice) {
			case 1:
				DatabaseOperations.databaseConnection();
				DatabaseOperations.displayData();
				DatabaseOperations.databaseConnectionClose();
				break;
			case 2:
				DatabaseOperations.databaseConnection();
				DatabaseOperations.searchData();
				DatabaseOperations.databaseConnectionClose();
				break;
			case 3:
				System.exit(0);
			default:
				System.out.println("Wrong Choice !!");

			}
		}

	}

}
