package com.maxwell.pos.service;

import com.maxwell.pos.model.InvoiceForFront;

public interface InvoiceForFrontManager {
	
	void add(InvoiceForFront invoiceForFront);
	
	InvoiceForFront get(Integer id);
	
	void update(InvoiceForFront invoiceForFront);

	String getInvoiceNumber(InvoiceForFront iForFront);

}
