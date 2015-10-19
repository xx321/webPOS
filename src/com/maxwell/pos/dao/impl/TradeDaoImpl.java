package com.maxwell.pos.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.maxwell.pos.dao.TradeDao;
import com.maxwell.pos.model.Trade;
import com.maxwell.pos.util.DateUtil;
import com.maxwell.pos.vo.TradeVO;
import com.maxwell.pos.vo.base.DataGrid;

@Component("tradeDao")
public class TradeDaoImpl extends SuperDaoImpl<Trade, Integer> implements
		TradeDao {
	//用來找尋要日結的交易
	@SuppressWarnings("unchecked")
	public List<Trade> getTradeByStatus(Integer status) {
		String hql = "from Trade as t where t.status=" + status;
		return getHibernateTemplate().find(hql);
	}

	@SuppressWarnings("unchecked")
	public List<Trade> getTradeBy(Date beginDate, Date endDate) {
		final String hql = "from Trade as t where t.status = 1 and t.createTime >= ? and t.createTime < ?";
		return getHibernateTemplate().find(hql,
				new Object[] { beginDate, endDate });
	}
	
//	public List<?> getTradeIdsBy(Date beginDate, Date endDate) {
//		final String hql = "select t.id from Trade as t where t.status = 1 and t.createTime >= ? and t.createTime < ? order by t.createTime";
//		return getHibernateTemplate().find(hql,
//				new Object[] { beginDate, endDate });
//	}
	
//	// ---- SELECT count(*) 總交易筆數, sum(quantity) 總件數, sum(total) 總金額  , t.type 交易方式(0:現金、1:發票) ---- (銷售統計  交易項)
//	@SuppressWarnings("unchecked")
//	public List<Object[]> getCheckoutByTrade(Date createtimeStart, Date createtimeEnd) {
//		String hql = "SELECT count(*), sum(quantity), sum(total), t.type from Trade t where t.status=1 and t.createTime >= ? and t.createTime < ? group by t.type";
//		return getHibernateTemplate().find(hql,
//				new Object[] { createtimeStart, createtimeEnd });
//	}

	public DataGrid getDataGrid(TradeVO tradeVO, Integer rows, Integer page,
			String sort, String order) {
		DataGrid datagrid = new DataGrid();
		
		StringBuffer sb = new StringBuffer();
		List<Object> values = new ArrayList<Object>();
		
		sb.append("from Trade t where 1=1 ");
		addWhere(tradeVO, sb, values);
		
		Long total = (Long) queryObject("select count(*) " + sb.toString(), values);
		datagrid.setTotal(total);
		
		addOrderBy(sb, sort, order);
		@SuppressWarnings("unchecked")
		List<Trade> trades = (List<Trade>) find(sb.toString(), values, rows, page); 
		
		List<TradeVO> tradeVOs = new ArrayList<TradeVO>();
		for(Trade trade : trades) {
			tradeVO = new TradeVO();
			BeanUtils.copyProperties(trade, tradeVO);
			tradeVO.setUsername(trade.getUser().getUsername());
			if (trade.getTradeInvoice() != null)
				tradeVO.setInvoiceNumber(trade.getTradeInvoice().getInvoiceNumber());
			
			tradeVOs.add(tradeVO);
		}
		datagrid.setRows(tradeVOs);
		
		return datagrid;
	}

	private void addWhere(TradeVO tradeVO, StringBuffer sb, List<Object> values) {
		if (tradeVO.getCreatetimeStart() != null) {
			sb.append(" and t.createTime >= ? ");
			values.add(DateUtil.toDate(tradeVO.getCreatetimeStart()));
		}
		if (tradeVO.getCreatetimeEnd() != null) {
			sb.append(" and t.createTime < ? ");
			values.add(DateUtil.addDay(DateUtil.toDate(tradeVO.getCreatetimeEnd()), 1));
		}
		
		if (tradeVO.getStatus() != null) {
			sb.append(" and t.status =? ");
			values.add(tradeVO.getStatus());
		}
		
		if (tradeVO.getIds() != null && !tradeVO.getIds().trim().equals("")) {
			Integer id = null;
			try {
				id = new Integer(tradeVO.getIds().trim());
			} catch (NumberFormatException e) {
			}
			sb.append(" and t.id=? ");
			if (id != null) {
				values.add(id);
			} else {
				values.add(new Integer(-1));
			}
		}
		if (tradeVO.getInvoiceNumber() != null && !tradeVO.getInvoiceNumber().trim().equals("")) {
			sb.append(" and t.id in (select ti.id from TradeInvoice ti where ti.invoiceNumber like ?) ");
			values.add("%%" + tradeVO.getInvoiceNumber().trim() + "%%");
		}
		if (tradeVO.getType() != null) {
			sb.append(" and t.type=? ");
			values.add(tradeVO.getType());
		}
	}

	@Override
	public void addOrderBy(StringBuffer sb, String sort, String order) {
		if("username".equals(sort))
			return;
		super.addOrderBy(sb, sort, order);
	}

	public boolean exists(Integer customerId) {
		String hql = "from Trade t where t.customer.id=?";
		
		@SuppressWarnings("unchecked")
		List<Trade> trades = getHibernateTemplate().find(hql, new Object[] { customerId });
		
		if (trades != null && trades.size() > 0)
			return true;
		return false;
	}

	public Double getInvoicesIncome(Date beginDate, Date endDate) {
		String hql = "select sum(t.total) from Trade t where status=1 and t.type=1 and t.createTime>=? and t.createTime<?";
		List<Object> values = new ArrayList<Object>();
		values.add(beginDate);
		values.add(endDate);
		Double result = (Double) queryObject(hql, values);
		if (result == null)
			return 0.0;
		return result;
	}

	public Double getCashIncome(Date beginDate, Date endDate) {
		String hql = "select sum(t.total) from Trade t where status=1 and t.type=0 and t.createTime>=? and t.createTime<?";
		List<Object> values = new ArrayList<Object>();
		values.add(beginDate);
		values.add(endDate);
		Double result = (Double) queryObject(hql, values);
		if (result == null)
			return 0.0;
		return result;
	}
	
}
