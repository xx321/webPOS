package com.maxwell.pos.dao;

import java.util.Date;

import com.maxwell.pos.model.Sell;
import com.maxwell.pos.vo.SellVO;
import com.maxwell.pos.vo.base.DataGrid;

public interface SellDao extends SuperDao<Sell, Integer> {

	DataGrid getDataGrid(SellVO sellVO, Integer rows, Integer page,
			String sort, String order);

	Double getInvoicesIncome(Date beginDate, Date endDate);

}
