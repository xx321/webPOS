package com.maxwell.pos.service;

import java.util.Map;

import com.maxwell.pos.model.Sellitem;
import com.maxwell.pos.vo.SellitemVO;
import com.maxwell.pos.vo.base.DataGrid;

public interface SellitemManager {

	void setSellitemsBy(String ids, Map<Integer, Sellitem> sellitems);

	void editSellitemsBy(SellitemVO sellitemVO, Map<Integer, Sellitem> sellitems);

	void deleteSellitemBy(String ids, Map<Integer, Sellitem> sellitems);

	DataGrid getDataGrid(SellitemVO sellitemVO, Integer rows, Integer page,
			String sort, String order);

	void deleteBySellId(Integer id);

}
