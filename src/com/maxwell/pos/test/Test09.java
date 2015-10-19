package com.maxwell.pos.test;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.maxwell.pos.model.Product;
import com.maxwell.pos.model.ProductBox;

public class Test09 {
	//商品 盒裝類 測試 (ProductBox)
	public static void main(String[] args) {

		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		SessionFactory sf =  (SessionFactory) ctx.getBean("sf");
		Session session = sf.openSession();
		session.beginTransaction();
		
		Product product = (Product) session.get(Product.class, 234);
		
		ProductBox pBox = new ProductBox();
		

		pBox.setBox((Product) session.get(Product.class, 235));
		pBox.setQuantity(5);
		
		product.setProductBox(pBox);
		pBox.setProduct(product);
		
		//實作  移除 關聯關係 的 對象 。
//		product.setProductBox(null);
//		ProductBox pBox = (ProductBox) session.get(ProductBox.class, product.getId());
//		pBox.setProduct(null);
//		session.delete(pBox);
		
		
		session.getTransaction().commit();
		session.close();
		sf.close();


	}
}
