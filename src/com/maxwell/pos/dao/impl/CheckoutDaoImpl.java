package com.maxwell.pos.dao.impl;

import org.springframework.stereotype.Component;

import com.maxwell.pos.dao.CheckoutDao;
import com.maxwell.pos.model.Checkout;

@Component("checkoutDao")
public class CheckoutDaoImpl extends SuperDaoImpl<Checkout, Integer> implements
		CheckoutDao {

	public Checkout getLastCheckout() {
		String hql = "select max(id) from Checkout";
		Object id = queryObject(hql, null);
		if (id != null)
			return findById((Integer) id);
		return null;
	}

}
