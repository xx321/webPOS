package com.maxwell.pos.service;

import com.maxwell.pos.vo.TradeitemVO;
import com.maxwell.pos.vo.base.DataGrid;

public interface TradeitemManager {
	//銷售統計用 datagrid
	DataGrid getDataGrid(TradeitemVO tradeitemVO, Integer rows, Integer page,
			String sort, String order);
	//一般用 datagrid
	DataGrid getDataGrid2(TradeitemVO tradeitemVO, Integer rows, Integer page,
			String sort, String order);

}
