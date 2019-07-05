package com.altimetrik.dataLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.altimetrik.businessLayer.SendMail;

public class DatabaseOperations {
	
	public static final String dbURL = "jdbc:oracle:thin:@localhost:1521:xe";
	public static final String username = "hr";
	public static final String password = "hr";
	public static Connection con;
	public static Statement stmt;
	public static PreparedStatement pstmt;

	static Scanner sc = new Scanner(System.in);
	
	static SendMail sendMail = new SendMail();
	
	public static void databaseConnection() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(dbURL, username, password);
			stmt = con.createStatement();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void databaseConnectionClose() {
		if (con != null) {
			try {
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	public static void insertDataIntoDB(long invoiceNo, String InvoiceDate, long CustomerPo, String CustomerAddress,
			double DueAmount) {

		try {
			String SQL = "INSERT INTO NEWINFOTABLE " + "VALUES(?, ?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(SQL);
			pstmt.setLong(1, invoiceNo);
			pstmt.setString(2, InvoiceDate);
			pstmt.setLong(3, CustomerPo);
			pstmt.setString(4, CustomerAddress);
			pstmt.setDouble(5, DueAmount);
			pstmt.setString(6, "Reject");
			pstmt.executeUpdate();
			// con.commit();
		} catch (SQLException e) {
			System.out.println("Failure while inserting records");
		} finally {

		}

	}

	public static void displayData() throws SQLException {
		ResultSet rs = stmt.executeQuery("select * from NEWINFOTABLE");
		while (rs.next()) {
			System.out.println("Invoice No : " + rs.getString(1));
			System.out.println("Invoice Date :" + rs.getString(2));
			System.out.println("Customer PO :" + rs.getString(3));
			System.out.println("Address :" + rs.getString(4));
			System.out.println("Total Invoice :" + rs.getString(5));
			System.out.println("Status :" + rs.getString(6));
		}
	}

	public static void searchData() throws SQLException {
		System.out.print("Enter Invoice No :");
		long findInvoice = sc.nextLong();
		ResultSet rs = stmt.executeQuery("select * from NEWINFOTABLE where INVOICE_NO = " + findInvoice);
		if (rs.next()) {
			System.out.println("Invoice No : " + rs.getString(1));
			System.out.println("Invoice Date :" + rs.getString(2));
			System.out.println("Customer PO :" + rs.getString(3));
			System.out.println("Address :" + rs.getString(4));
			System.out.println("Total Invoice :" + rs.getString(5));
			System.out.println("Status :" + rs.getString(6));
			DatabaseOperations.approver(findInvoice);
			SendMail.send("vivzgouda@gmail.com", "vivek1998", "specializeinfo@gmail.com", "Accounts Payable Status", "Your bill is verified and approved.");
		} else {
			System.out.println("Fail !!");
		}
	}

	public static void approver(long invoiceNo) throws SQLException {
		String SQL = "UPDATE NEWINFOTABLE SET Status = ? WHERE INVOICE_NO = ?";
		pstmt = con.prepareStatement(SQL);
		pstmt.setString(1, "Accept");
		pstmt.setLong(2, invoiceNo);
		pstmt.executeUpdate();
	}
}
