package com.maxwell.pos.test;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.maxwell.pos.model.Trade;
import com.maxwell.pos.model.TradeInvoice;
import com.maxwell.pos.model.User;


public class Test08 {
	
	public static void main(String[] args) {

		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		SessionFactory sf =  (SessionFactory) ctx.getBean("sf");
		Session session = sf.openSession();
		session.beginTransaction();
		
		Trade trade = new Trade();
		
		TradeInvoice tInvoice = new TradeInvoice();
		

		trade.setUser((User) session.get(User.class, 10));
		trade.setQuantity(12);
		trade.setTotal(1002.00);
		trade.setCreateTime(new java.util.Date());
		trade.setStatus(new Integer(0));
		trade.setType(1);
		
		tInvoice.setCompanyCode("12345678");
		tInvoice.setInvoiceNumber("NX12345679");
		
		trade.setTradeInvoice(tInvoice);
		tInvoice.setTrade(trade);
		
		session.save(trade);
		
		
		session.getTransaction().commit();
		



	}
}
