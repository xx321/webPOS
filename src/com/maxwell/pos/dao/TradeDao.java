package com.maxwell.pos.dao;

import java.util.Date;
import java.util.List;

import com.maxwell.pos.model.Trade;
import com.maxwell.pos.vo.TradeVO;
import com.maxwell.pos.vo.base.DataGrid;

public interface TradeDao extends SuperDao<Trade, Integer> {

	public List<Trade> getTradeByStatus(Integer status);

	public List<Trade> getTradeBy(Date beginDate, Date endDate);
	
//	public List<?> getTradeIdsBy(Date beginDate, Date endDate);
	
//	public List<Object[]> getCheckoutByTrade(Date createtimeStart, Date createtimeEnd);

	public DataGrid getDataGrid(TradeVO tradeVO, Integer rows, Integer page,
			String sort, String order);

	public boolean exists(Integer customerId);

	public Double getInvoicesIncome(Date beginDate, Date endDate);

	public Double getCashIncome(Date beginDate, Date endDate);

}
