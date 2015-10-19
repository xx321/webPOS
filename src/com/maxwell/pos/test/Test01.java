package com.maxwell.pos.test;

import java.util.Date;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.maxwell.pos.model.Customer;
import com.maxwell.pos.model.Sell;
import com.maxwell.pos.model.User;

public class Test01 {
	//�H�W��k���R���ӫ~�� �Ұ����~�����p�T�{�C
	//�p�G�~�䥼���p��䥦���� (List<?> == 0 ) �A�h�i�H�R���A �Ϥ� ����. 
	
	//User , Product ��Ӫ?�n���o�ؽT�{�C
	public static void main(String[] args) {

		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		SessionFactory sf =  (SessionFactory) ctx.getBean("sf");
		Session session = sf.openSession();
		session.beginTransaction();
		
//		Product product = (Product) session.get(Product.class, new Integer(202));
//		System.out.println(product.getTradeitems().size());
		
//		Query query = session.createQuery("from Tradeitem t where t.product.id =" + product.getId() );
//		List<?> products = query.list();
//		System.out.println(products.size());
//		if(products.size() == 0)
//			session.delete(product);
		
		Sell sell = new Sell();
		User u = new User();
		u.setId(10);
		sell.setUser(u);
		Customer customer = new Customer();
		customer.setId(302);
		sell.setCustomer(customer);
		sell.setInvoiceNumber("");
		sell.setTotal(1500.0);
		sell.setTax(0.05);
		sell.setSalesTax(75.0);
		sell.setTotalAmount(1575.0);
		sell.setCreateTime(new Date());
		sell.setStatus(0);
		sell.setDescription("");
		sell.setClosed(1);
		sell.setDelivery("");
		sell.setSid(sell.getId().toString());

		
		session.getTransaction().commit();
		sf.close();
		
		
//		Date date = DateUtil.toDate("20130607");
//		
//		System.out.println(date.toString());

	}
}
