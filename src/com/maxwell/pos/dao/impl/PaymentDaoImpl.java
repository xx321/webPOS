package com.maxwell.pos.dao.impl;

import org.springframework.stereotype.Component;

import com.maxwell.pos.dao.PaymentDao;
import com.maxwell.pos.model.Payment;

@Component("paymentDao")
public class PaymentDaoImpl extends SuperDaoImpl<Payment, Integer>
	implements PaymentDao {

	public Payment getLastPayment() {
		String hql = "select max(id) from Payment";
		Object id = queryObject(hql, null);
		if (id != null)
			return findById((Integer) id);
		return null;
	}

}
