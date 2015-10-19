package com.maxwell.pos.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.maxwell.pos.dao.InvoiceForFrontDao;
import com.maxwell.pos.model.InvoiceForFront;
import com.maxwell.pos.service.InvoiceForFrontManager;

@Component("invoiceForFrontManager")
public class InvoiceForFrontManagerImpl implements InvoiceForFrontManager {
	
	private InvoiceForFrontDao invoiceForFrontDao;
	
	@Resource
	public void setInvoiceForFrontDao(InvoiceForFrontDao invoiceForFrontDao) {
		this.invoiceForFrontDao = invoiceForFrontDao;
	}
	
	public void add(InvoiceForFront invoiceForFront) {
		invoiceForFront.setInvoiceCoding("AA");
		invoiceForFront.setInvoiceNumber(10000000);
		invoiceForFront.setQuantity(0);
		invoiceForFrontDao.save(invoiceForFront);
	}
	
	public InvoiceForFront get(Integer id) {
		return invoiceForFrontDao.findById(id);
	}
	
	public void update(InvoiceForFront invoiceForFront) {
		invoiceForFrontDao.update(invoiceForFront);
	}

	public String getInvoiceNumber(InvoiceForFront invoiceForFront) {
		
		if (invoiceForFront.getQuantity() >= 250)
			return null;
		Integer invoiceNumber = invoiceForFront.getInvoiceNumber()+invoiceForFront.getQuantity();
		invoiceForFront.setQuantity(invoiceForFront.getQuantity()+1);
		
		String result = invoiceForFront.getInvoiceCoding() + invoiceNumber.toString();
		
		return result;
	}
	
}
