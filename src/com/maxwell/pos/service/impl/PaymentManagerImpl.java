package com.maxwell.pos.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.maxwell.pos.dao.PaymentDao;
import com.maxwell.pos.model.IntoGoods;
import com.maxwell.pos.model.Payment;
import com.maxwell.pos.model.User;
import com.maxwell.pos.service.PaymentManager;
import com.maxwell.pos.util.DateUtil;
import com.maxwell.pos.vo.PaymentVO;

@Component("paymentManager")
public class PaymentManagerImpl implements PaymentManager {
	
	private PaymentDao paymentDao;
	
	@Resource
	public void setPaymentDao(PaymentDao paymentDao) {
		this.paymentDao = paymentDao;
	}

	public void add(Payment payment) {
		paymentDao.save(payment);
	}

	//根據傳進來的參數 ， 設定 Payment，返回。
	public Payment get(PaymentVO paymentVO, List<IntoGoods> intoGoodsList,
			Map<String, Object> session) {
		IntoGoods intoGoods = null;
		Payment payment = new Payment();
		BeanUtils.copyProperties(paymentVO, payment);
		
		Double totalAmount = countTotalAmount(intoGoodsList);
		payment.setPaymentAmount(Math.rint(totalAmount));
		payment.setPaymentPersonnel((User) session.get("user"));
		payment.setPaymentTime(DateUtil.toDate(paymentVO.getPaymentTimeStr()));
		
		int length = intoGoodsList.size();
		for(int i=0; i<length; i++) {
			//進貨單結清
			intoGoods = intoGoodsList.get(i);
			intoGoods.setStatus(new Integer(1));
			intoGoods.setPaymentPersonnel(payment.getPaymentPersonnel());
			intoGoods.setPaymentTime(payment.getPaymentTime());
			
			intoGoods.setPayment(payment);
			
			payment.getIntoGoodses().add(intoGoods);
		}
		return payment;
	}
	
	private Double countTotalAmount(List<IntoGoods> intoGoodsList) {
		Double totalAmount = 0.00;
		for(IntoGoods i : intoGoodsList) {
			totalAmount = totalAmount + i.getTotalAmount();
		}
		return totalAmount;
	}

	public Payment getLastPayment() {
		return paymentDao.getLastPayment();
	}

}
