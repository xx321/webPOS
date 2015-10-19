package com.maxwell.pos.test;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.maxwell.pos.util.DateUtil;

public class Test05 {
	public static void main(String[] args) {
		//統計查詢 測試
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		SessionFactory sf = (SessionFactory) ctx.getBean("sf");
		Session session = sf.openSession();
		session.beginTransaction();
		
		Query query = session.createQuery("select ti.product.name, sum(ti.quantity), sum(ti.total) " +
										  "from Tradeitem ti " +
										  "where ti.trade.id in (select t.id " +
										  						"from Trade t " +
										  						"where t.createTime >= ? " +
										  						"and t.createTime <= ?) " +
										  "group by ti.product.id");
		query.setParameter(0, DateUtil.toDate("2013/06/07"));
		query.setParameter(1, DateUtil.toDate("2013/06/10"));
		
		List<?> result = query.list();
		for(int i=0; i<result.size(); i++) {
			Object[] os = (Object[]) result.get(i);
			for(int j=0; j<os.length; j++) {
				System.out.println(os[j].toString());
			}
			System.out.println("-------------");
		}
		session.beginTransaction().commit();
	}
}
