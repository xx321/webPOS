package com.maxwell.pos.dao;

import com.maxwell.pos.model.Payment;

public interface PaymentDao extends SuperDao<Payment, Integer> {

	Payment getLastPayment();

}
