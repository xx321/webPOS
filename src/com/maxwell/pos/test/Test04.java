package com.maxwell.pos.test;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.maxwell.pos.util.DateUtil;


public class Test04 {
	public static void main(String[] args) {
		
		//HQL in 的用法
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		SessionFactory sf = (SessionFactory) ctx.getBean("sf");
		Session session = sf.openSession();
		session.beginTransaction();
		Query query = session.createQuery("select t.id from Trade t where t.createTime between ? and ? ");
		query.setParameter(0, DateUtil.toDate("2013/06/07"));
		query.setParameter(1, DateUtil.toDate("2013/06/10"));
		
		List<?> list = query.list();
		
		query = session.createQuery("select count(*) " +
				  					"from Tradeitem ti " +
				  					"where ti.trade.id in (:ids) " +
				  					"group by ti.product.id" + 
				  					" order by ti.product.category.name desc ");
		
		query.setParameterList("ids", list);
		
		System.out.println(query.list().size());
//		query = session.createQuery("select ti.product.category.name, ti.product.name, sum(ti.quantity), sum(ti.total) " +
//										  "from Tradeitem ti " +
//										  "where ti.trade.id in (:ids) " +
//										  "group by ti.product.id" + 
//										  " order by ti.product.category.name desc ");
//		query.setParameterList("ids", list);
//		list = query.list();
//		
//		for(int i=0; i<list.size(); i++) {
//			Object[] os = (Object[]) list.get(i);
//			for(int j=0; j<os.length; j++) {
//				System.out.println(os[j].toString());
//			}
//			System.out.println("-------------");
//		}
		
		
		session.beginTransaction().commit();
	}
}
