package com.maxwell.pos.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.maxwell.pos.action.base.BaseAction;
import com.maxwell.pos.model.Trade;
import com.maxwell.pos.model.TradeInvoice;
import com.maxwell.pos.model.Tradeitem;
import com.maxwell.pos.service.TradeInvoiceManager;
import com.maxwell.pos.vo.base.InvoiceInfo;
import com.maxwell.pos.vo.base.InvoiceItem;

@Component("tradeInvoiceAction")
@Scope("prototype")
public class TradeInvoiceAction extends BaseAction {

	private static final long serialVersionUID = -8034752436704041157L;
	
	private TradeInvoiceManager tradeInvoiceManager;
	
	@Resource
	public void setTradeInvoiceManager(TradeInvoiceManager tradeInvoiceManager) {
		this.tradeInvoiceManager = tradeInvoiceManager;
	}
	
	private String invoicesJsonString;

	public String getInvoicesJsonString() {
		return invoicesJsonString;
	}

	private Integer tradeId;
	
	public void setTradeId(Integer tradeId) {
		this.tradeId = tradeId;
	}
	
	private String checkCode;
	
	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}

	public String getInvoiceInfo() {
		List<Trade> trades = tradeInvoiceManager.listTradeByStatus(new Integer(0));
		List<InvoiceInfo> invoices = new ArrayList<InvoiceInfo>();
		
		for (Trade t : trades) {
			InvoiceInfo invoice = new InvoiceInfo();
			invoice.setId(t.getId());
			invoice.setCreateTime(t.getCreateTime());
			invoice.setQuantity(t.getQuantity());
			invoice.setTotal(t.getTotal().intValue());
			
			invoice.setCompanyCode(t.getTradeInvoice().getCompanyCode());
			
			for (Tradeitem tradeitem : t.getTradeitems()) {
				InvoiceItem item = new InvoiceItem();
				item.setProductName(tradeitem.getProduct().getName());
				item.setQuantity(tradeitem.getQuantity());
				item.setTotal(tradeitem.getTotal().intValue());
				
				invoice.getItem().add(item);
				
			}
			
			invoices.add(invoice);
		}
		invoicesJsonString = JSON.toJSONStringWithDateFormat(invoices,
				"yyyy-MM-dd HH:mm:ss");
		
		return SUCCESS;
	}
	
	public void update() {
		if (!"30339084".equals(checkCode))
			return;
		
		TradeInvoice invoice = tradeInvoiceManager.get(tradeId);
		invoice.setStatus(new Integer(1));
		
		tradeInvoiceManager.update(invoice);
	}
}
