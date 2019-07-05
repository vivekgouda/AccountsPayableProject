package com.altimetrik.dataLayer;

import com.altimetrik.presentationLayer.Application;

public class FindRequiredDetails {
	
	public double totalInvoice;

	public long findInvoiceNo(String invoiceNum) {
		return Long.parseLong(
				Application.rawText.substring(Application.rawText.indexOf(invoiceNum) + invoiceNum.length(),
						Application.rawText.indexOf("Invoice Date")).trim());
	}

	public String findInvoiceDate(String invoiceDateArg) { 
		return Application.rawText.substring(Application.rawText.indexOf(invoiceDateArg) + invoiceDateArg.length(),
				Application.rawText.indexOf(invoiceDateArg) + invoiceDateArg.length() + 10).trim();
	}

	public long findCustomerPO(String customerPOArg) {
		return Long.parseLong(
				Application.rawText.substring(Application.rawText.indexOf(customerPOArg) + customerPOArg.length(),
						Application.rawText.indexOf("Account No")).trim());
	}

	public String findCustomerAddress(String customerAddress) {
		return Application.rawText
				.substring(Application.rawText.indexOf(customerAddress) + customerAddress.length(),
						Application.rawText.indexOf("Remit To"))
				.replaceAll("\\s{2,}", " ").trim();
	}

	public double findTotalInvoice() {
		String tempAmount[] = Application.rawText.split("\\$");
		String tempInvoice[] = tempAmount[tempAmount.length - 1].split("\\s");
		totalInvoice = Double.parseDouble(tempInvoice[0].replace(",", ""));
		return totalInvoice;
	}
}
