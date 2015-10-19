package com.maxwell.pos.service;

import java.util.Map;

import com.maxwell.pos.model.PettyCash;
import com.maxwell.pos.model.SpendingItem;
import com.maxwell.pos.vo.PettyCashVO;
import com.maxwell.pos.vo.base.DataGrid;

public interface PettyCashManager {

	PettyCash findBy(Map<String, Object> session, PettyCashVO pettyCashVO);

	String setSpendingItems(Map<Integer, SpendingItem> spendingItems,
			PettyCash pettyCash);

	void add(PettyCash pettyCash);

	DataGrid getDataGrid(PettyCashVO pettyCashVO, Integer rows, Integer page,
			String sort, String order);

	PettyCash get(Integer id);

	void update(PettyCash pettyCash);

	String delete(String ids);

}
