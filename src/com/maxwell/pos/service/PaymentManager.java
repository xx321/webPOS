package com.maxwell.pos.service;

import java.util.List;
import java.util.Map;

import com.maxwell.pos.model.IntoGoods;
import com.maxwell.pos.model.Payment;
import com.maxwell.pos.vo.PaymentVO;

public interface PaymentManager {

	void add(Payment payment);

	Payment get(PaymentVO paymentVO, List<IntoGoods> intoGoodsList,
			Map<String, Object> session);

	Payment getLastPayment();

}
