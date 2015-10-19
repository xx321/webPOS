package com.maxwell.pos.service;

import java.util.List;

import com.maxwell.pos.model.Trade;
import com.maxwell.pos.model.TradeInvoice;

public interface TradeInvoiceManager {

	List<Trade> listTradeByStatus(Integer status);

	TradeInvoice get(Integer tradeId);

	void update(TradeInvoice invoice);
}
