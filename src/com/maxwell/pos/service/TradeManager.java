package com.maxwell.pos.service;

import java.util.Date;
import java.util.List;

import com.maxwell.pos.model.Trade;
import com.maxwell.pos.vo.TradeVO;
import com.maxwell.pos.vo.base.DataGrid;

public interface TradeManager {

	public void add(Trade trade);

//	public List<Trade> getListBy(Date beginDate, Date endDate);

	public DataGrid getDataGrid(TradeVO tradeVO, Integer rows, Integer page,
			String sort, String order);

	public String updateInvalid(String ids);
	
}
