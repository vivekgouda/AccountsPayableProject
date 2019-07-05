package com.altimetrik.dataLayer;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.text.PDFTextStripper;

import com.altimetrik.businessLayer.ReceiveEmailWithAttachment;

public class InvoiceDataDB {

//	static String rawText;
//	private static final String invoiceNo = "Invoice No";
//	private static final String invoiceDate = "Invoice Date";
//	private static final String customerPO = "Customer P.O.";
//	private static final String customerAddress = "Ship To";
//
//	public static void main(String[] args) throws InvalidPasswordException, IOException, SQLException {
//
//		ReceiveEmailWithAttachment mailString = new ReceiveEmailWithAttachment();
//		FindRequiredDetails details = new FindRequiredDetails();
//		File file = new File("D:\\Mails\\" + mailString.myText);
//		PDDocument pdfDocument = PDDocument.load(file);
//		PDFTextStripper pdfStripper = new PDFTextStripper();
//		rawText = pdfStripper.getText(pdfDocument);
//		
//		long invoiceNum = details.findInvoiceNo(invoiceNo);
//		String custInvoiceDate = details.findInvoiceDate(invoiceDate);
//		long custPONum = details.findCustomerPO(customerPO);
//		String custAddress = details.findCustomerAddress(customerAddress);
//		double totalInvoice = details.findTotalInvoice();
//
////		System.out.println("Invoice No: " + invoiceNum);
////		System.out.println("Invoice date: " + custInvoiceDate);
////		System.out.println("Customer PO: " + custPONum);
////		System.out.println("Customer Address: " + custAddress);
////		System.out.println("Total Invoice: " + totalInvoice);
//
//		pdfDocument.close();
//
//		DatabaseOperations.databaseConnection();
//
//		DatabaseOperations.insertDataIntoDB(invoiceNum, custInvoiceDate, custPONum, custAddress, totalInvoice);
//
//		DatabaseOperations.databaseConnectionClose();
//	}
}
