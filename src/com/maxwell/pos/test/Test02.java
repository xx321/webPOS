package com.maxwell.pos.test;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.maxwell.pos.model.Checkout;
import com.maxwell.pos.model.Trade;
import com.maxwell.pos.model.User;
import com.maxwell.pos.util.DateUtil;


public class Test02 {

	public static void main(String[] args) {
		
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		SessionFactory sf =  (SessionFactory) ctx.getBean("sf");
		Session session = sf.openSession();
		session.beginTransaction();
		

//		User user = (User) session.get(User.class, new Integer(10));
		
		Date beginDate = DateUtil.toDate("20130607");
		Date endDate = DateUtil.toDate("20130610");;
		
		Query query = session.createQuery("from Trade as t where t.createTime BETWEEN ? and ? ");
		query.setDate(0, beginDate);
		query.setDate(1, endDate);
		@SuppressWarnings("unchecked")
		List<Trade> trades = query.list();
		
		
		
		System.out.println(trades.size());
//		int totalItem = 0;
//		int totalAmount = 0;
//		
//		
//		Checkout checkout = new Checkout();
//		
//		checkout.setUser(user);
//		
//		for(Trade t : trades)
//		{
//			totalItem += t.getQuantity();
//			totalAmount += t.getTotal();
//			t.setStatus(new Integer(1));
//			t.setCheckout(checkout);
//			checkout.getTrades().add(t);
//		}
//		checkout.setTotalItem(totalItem);
//		checkout.setTotalAmount(totalAmount);
//		
//		checkout.setCreateTime(new java.util.Date());
//		
//		session.save(checkout);
		
		session.getTransaction().commit();
	}
}
