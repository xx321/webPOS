package com.maxwell.pos.action;


import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.maxwell.pos.action.base.BaseAction;
import com.maxwell.pos.model.InvoiceForFront;
import com.maxwell.pos.service.InvoiceForFrontManager;
import com.maxwell.pos.vo.InvoiceForFrontVO;
import com.opensymphony.xwork2.ModelDriven;

@Component("invoiceForFrontAction")
@Scope("prototype")
public class InvoiceForFrontAction extends BaseAction implements ModelDriven<InvoiceForFrontVO>{

	private static final long serialVersionUID = -8034752436704041157L;
	private InvoiceForFrontManager invoiceForFrontManager;
	
	@Resource
	public void setInvoiceForFrontManager(InvoiceForFrontManager invoiceForFrontManager) {
		this.invoiceForFrontManager = invoiceForFrontManager;
	}
	
	private InvoiceForFront invoiceForFront;
	
	public InvoiceForFront getInvoiceForFront() {
		return invoiceForFront;
	}

	public void setInvoiceForFront(InvoiceForFront invoiceForFront) {
		this.invoiceForFront = invoiceForFront;
	}

	private InvoiceForFrontVO invoiceForFrontVO = new InvoiceForFrontVO();
	
	public InvoiceForFrontVO getModel() {
		return invoiceForFrontVO;
	}
	
	public String load() {
		invoiceForFront = invoiceForFrontManager.get(new Integer(1));
		if (invoiceForFront == null) {
			invoiceForFront = new InvoiceForFront();
			invoiceForFrontManager.add(invoiceForFront);
		}
		return SUCCESS;
	}
	
	public String update() {	
		if (invoiceForFront.getQuantity() == null || invoiceForFront.getQuantity() == 0)
			invoiceForFront.setQuantity(new Integer(0));
		else
			invoiceForFront.setQuantity((invoiceForFront.getQuantity()+1)%250);
		invoiceForFrontManager.update(invoiceForFront);
		return SUCCESS;
	}
}
