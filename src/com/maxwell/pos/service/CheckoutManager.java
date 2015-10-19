package com.maxwell.pos.service;

import java.util.Date;
import java.util.List;

import com.maxwell.pos.model.Checkout;
import com.maxwell.pos.model.Trade;

public interface CheckoutManager {

	public Checkout getByTradeStatus(Integer status);
	
	public void add(Checkout checkout);

	public Checkout getLastCheckout();

	public Checkout getCheckoutByTrade(Date beginDate, Date endDate);

//	public Checkout get(List<Trade> trades);
	
//	public List<Object[]> getCheckoutByTrade(Date timeStart, Date timeEnd);
}
