package com.maxwell.pos.dao;

import java.util.List;

import com.maxwell.pos.model.Sellitem;
import com.maxwell.pos.vo.SellitemVO;
import com.maxwell.pos.vo.base.DataGrid;

public interface SellitemDao extends SuperDao<Sellitem, Integer> {

	DataGrid getDataGrid(SellitemVO sellitemVO, Integer rows, Integer page,
			String sort, String order);

	void deleteBySellId(Integer id);

	List<Sellitem> findBySellId(Integer id);
}
