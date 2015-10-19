package com.maxwell.pos.service;

import java.util.Map;

import com.maxwell.pos.model.SpendingItem;
import com.maxwell.pos.vo.SpendingItemVO;
import com.maxwell.pos.vo.base.DataGrid;

public interface SpendingItemManager {

	void setSpendingItemsBy(String ids, Map<Integer, SpendingItem> spendingItems);
	
	void editSpendingItemBy(SpendingItemVO spendingItemVO, Map<Integer, SpendingItem> spendingItems);
	
	void deleteSpendingItemBy(String ids, Map<Integer, SpendingItem> spendingItems);

	DataGrid getDataGrid(SpendingItemVO spendingItemVO, Integer rows,
			Integer page, String sort, String order);

	void deleteByPettyCashId(Integer id);
}
