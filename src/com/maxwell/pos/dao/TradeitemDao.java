package com.maxwell.pos.dao;

import java.util.List;

import com.maxwell.pos.model.Tradeitem;
import com.maxwell.pos.vo.TradeitemVO;
import com.maxwell.pos.vo.base.DataGrid;

public interface TradeitemDao extends SuperDao<Tradeitem, Integer> {
	//銷售統計用 datagrid
	DataGrid getDataGrid(TradeitemVO tradeitemVO, Integer rows, Integer page,
			String sort, String order);
	//一般用 datagrid
	DataGrid getDataGrid2(TradeitemVO tradeitemVO, Integer rows, Integer page,
			String sort, String order);
	
	List<Tradeitem> findByTradeId(Integer id);

}
