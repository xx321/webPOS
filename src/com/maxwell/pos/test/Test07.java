package com.maxwell.pos.test;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.maxwell.pos.model.InvoiceForFront;


public class Test07 {
	
	public static void main(String[] args) {

		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		SessionFactory sf =  (SessionFactory) ctx.getBean("sf");
		
		Session session1 = sf.openSession();
//		Session session2 = sf.openSession();
		
		Transaction tx1 = session1.beginTransaction();
//		Transaction tx2 = session2.beginTransaction();

		
//		InvoiceForFront i1 = (InvoiceForFront) session1.get(InvoiceForFront.class, 1);
//		InvoiceForFront i2 = (InvoiceForFront) session2.get(InvoiceForFront.class, 1);
		
		InvoiceForFront i1 = new InvoiceForFront();
		i1.setInvoiceCoding("NX");
		i1.setInvoiceNumber(000);
		i1.setQuantity(0);
		
//		i2.setInvoiceNumber(300);
		
		session1.save(i1);
		
		tx1.commit();
//		tx2.commit();
		
		session1.close();
//		session2.close();


	}
}
