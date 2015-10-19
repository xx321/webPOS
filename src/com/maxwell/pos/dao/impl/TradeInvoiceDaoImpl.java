package com.maxwell.pos.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.maxwell.pos.dao.TradeInvoiceDao;
import com.maxwell.pos.model.Trade;
import com.maxwell.pos.model.TradeInvoice;

@Component("tradeInvoiceDao")
public class TradeInvoiceDaoImpl extends SuperDaoImpl<TradeInvoice, Integer> implements TradeInvoiceDao {

	@SuppressWarnings("unchecked")
	public List<Trade> findTradesByStatus(Integer status) {
		String hql = "from Trade as t where t.type=1 and t.tradeInvoice.status=" + status;
		return getHibernateTemplate().find(hql);
	}

	
}
