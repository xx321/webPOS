package com.maxwell.pos.dao.impl;


import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Component;

import com.maxwell.pos.dao.ProductItemDao;
import com.maxwell.pos.model.ProductItem;

@Component("productItemDao")
public class ProductItemDaoImpl extends SuperDaoImpl<ProductItem, Integer> implements
	ProductItemDao {

	public void deleteByproductId(final Integer id) {
		final String hql = "delete ProductItem p where p.product.id=:id";
		this.getHibernateTemplate().execute(new HibernateCallback() {
			
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				Query query = session.createQuery(hql);
				query.setParameter("id", id);
				return query.executeUpdate();
			}
		});
	}

	
}
