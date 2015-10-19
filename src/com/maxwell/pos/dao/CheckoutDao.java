package com.maxwell.pos.dao;

import com.maxwell.pos.model.Checkout;

public interface CheckoutDao extends SuperDao<Checkout, Integer> {

	public Checkout getLastCheckout();
	
}
