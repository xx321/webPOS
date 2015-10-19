package com.maxwell.pos.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.maxwell.pos.dao.TradeInvoiceDao;
import com.maxwell.pos.model.Trade;
import com.maxwell.pos.model.TradeInvoice;
import com.maxwell.pos.service.TradeInvoiceManager;

@Component("tradeInvoiceManager")
public class TradeInvoiceManagerImpl implements TradeInvoiceManager {
	private TradeInvoiceDao tradeInvoiceDao;
	
	@Resource
	public void setTradeInvoiceDao(TradeInvoiceDao tradeInvoiceDao) {
		this.tradeInvoiceDao = tradeInvoiceDao;
	}

	public List<Trade> listTradeByStatus(Integer status) {
		return tradeInvoiceDao.findTradesByStatus(status);
	}

	public TradeInvoice get(Integer tradeId) {
		return tradeInvoiceDao.findById(tradeId);
	}

	public void update(TradeInvoice invoice) {
		tradeInvoiceDao.update(invoice);
	}
	
}
