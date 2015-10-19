package com.maxwell.pos.dao;

import java.util.List;

import com.maxwell.pos.model.Trade;
import com.maxwell.pos.model.TradeInvoice;

public interface TradeInvoiceDao extends SuperDao<TradeInvoice, Integer> {

	List<Trade> findTradesByStatus(Integer status);

	
}
