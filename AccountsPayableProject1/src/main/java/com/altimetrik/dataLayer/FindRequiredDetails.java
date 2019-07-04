package com.altimetrik.dataLayer;

public class FindRequiredDetails {
	
	public double totalInvoice;

	public long findInvoiceNo(String invoiceNum) {
		return Long.parseLong(
				InvoiceDataDB.rawText.substring(InvoiceDataDB.rawText.indexOf(invoiceNum) + invoiceNum.length(),
						InvoiceDataDB.rawText.indexOf("Invoice Date")).trim());
	}

	public String findInvoiceDate(String invoiceDateArg) { 
		return InvoiceDataDB.rawText.substring(InvoiceDataDB.rawText.indexOf(invoiceDateArg) + invoiceDateArg.length(),
				InvoiceDataDB.rawText.indexOf(invoiceDateArg) + invoiceDateArg.length() + 10).trim();
	}

	public long findCustomerPO(String customerPOArg) {
		return Long.parseLong(
				InvoiceDataDB.rawText.substring(InvoiceDataDB.rawText.indexOf(customerPOArg) + customerPOArg.length(),
						InvoiceDataDB.rawText.indexOf("Account No")).trim());
	}

	public String findCustomerAddress(String customerAddress) {
		return InvoiceDataDB.rawText
				.substring(InvoiceDataDB.rawText.indexOf(customerAddress) + customerAddress.length(),
						InvoiceDataDB.rawText.indexOf("Remit To"))
				.replaceAll("\\s{2,}", " ").trim();
	}

	public double findTotalInvoice() {
		String tempAmount[] = InvoiceDataDB.rawText.split("\\$");
		String tempInvoice[] = tempAmount[tempAmount.length - 1].split("\\s");
		totalInvoice = Double.parseDouble(tempInvoice[0].replace(",", ""));
		return totalInvoice;
	}
}
