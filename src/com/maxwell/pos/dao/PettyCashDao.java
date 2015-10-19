package com.maxwell.pos.dao;

import com.maxwell.pos.model.PettyCash;
import com.maxwell.pos.vo.PettyCashVO;
import com.maxwell.pos.vo.base.DataGrid;

public interface PettyCashDao extends SuperDao<PettyCash, Integer> {

	DataGrid getDataGrid(PettyCashVO pettyCashVO, Integer rows, Integer page,
			String sort, String order);

}
