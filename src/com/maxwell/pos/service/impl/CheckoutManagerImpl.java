package com.maxwell.pos.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.maxwell.pos.dao.CheckoutDao;
import com.maxwell.pos.dao.TradeDao;
import com.maxwell.pos.model.Checkout;
import com.maxwell.pos.model.Trade;
import com.maxwell.pos.service.CheckoutManager;

@Component("CheckoutManager")
public class CheckoutManagerImpl implements CheckoutManager {
	
	private CheckoutDao checkoutDao;
	private TradeDao tradeDao;
	
	@Resource
	public void setCheckoutDao(CheckoutDao checkoutDao) {
		this.checkoutDao = checkoutDao;
	}
	@Resource
	public void setTradeDao(TradeDao tradeDao) {
		this.tradeDao = tradeDao;
	}

	public Checkout getByTradeStatus(Integer status) {
		
		List<Trade> trades = tradeDao.getTradeByStatus(status);
		
		return setTrades(trades);
	}
	public void add(Checkout checkout) {
		checkoutDao.save(checkout);
	}
	
	public Checkout getLastCheckout() {
		return checkoutDao.getLastCheckout();
	}
//	public Checkout get(List<Trade> trades) {
//		
//		return setTrades(trades);
//	}
	
	private Checkout setTrades(List<Trade> trades) {
		
		int cashCount = 0;
		int cashItem = 0;
		double cashAmount = 0;
		int invoiceCount = 0;
		int invoiceItem = 0;
		double invoiceAmount = 0;
		int totalCount = 0;
		int totalItem = 0;
		double totalAmount = 0;
		
		Checkout checkout = new Checkout();
		//此處的for迴圈 ， 已經嚴重影響系統性能。(如果統計的筆數超過萬筆，迴圈就要計算超過萬次!
		/**
		 * 解決辦法 : 將要取得的參數 分多次的sql語句，
		 * 			個別拿到之後再做set的動作。
		 * 		也就是把for 回迴整個區塊拿掉。
		 */
		int i= 0;
		for(Trade t : trades)
		{
			if (t.getType() == 1) {
				invoiceCount++;
				invoiceItem += t.getQuantity();
				invoiceAmount += t.getTotal();
			} else {
				cashCount++;
				cashItem += t.getQuantity();
				cashAmount += t.getTotal();
			}
			totalCount++;
			totalItem += t.getQuantity();
			totalAmount += t.getTotal();
			if(t.getStatus() == 0)
				t.setStatus(new Integer(1));
			t.setCheckout(checkout);
			checkout.getTrades().add(t);
			i++;
			System.out.println("====================第"+ i +"次=============================");
		}
		System.out.println("==================== 總共做了"+ i +"次迴圈計算。=============================");
		checkout.setCashCount(cashCount);
		checkout.setCashItem(cashItem);
		checkout.setCashAmount(Math.rint(cashAmount));
		checkout.setInvoiceCount(invoiceCount);
		checkout.setInvoiceItem(invoiceItem);
		checkout.setInvoiceAmount(Math.rint(invoiceAmount));
		checkout.setTotalCount(totalCount);
		checkout.setTotalItem(totalItem);
		checkout.setTotalAmount(Math.rint(totalAmount));	
		
		return checkout;
	}
	
	public Checkout getCheckoutByTrade(Date beginDate, Date endDate) {
		List<Trade> trades = tradeDao.getTradeBy(beginDate, endDate);
		return setTrades(trades);
	}
	
//	public List<Object[]> getCheckoutByTrade(Date timeStart, Date timeEnd) {
//		return tradeDao.getCheckoutByTrade(timeStart, timeEnd);
//	}
}
