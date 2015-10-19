package com.maxwell.pos.service;

import java.util.Collection;
import java.util.Map;

import com.maxwell.pos.model.Sell;
import com.maxwell.pos.model.Sellitem;
import com.maxwell.pos.vo.SellVO;
import com.maxwell.pos.vo.base.DataGrid;

public interface SellManager {
	
	void add(Sell sell);
	
	Sell findBy(Map<String, Object> session, SellVO sellVO);
	
	String sales(Map<Integer, Sellitem> sellitems, Sell sell);
	
	void updateSales(Sell sell);
	void deleteSales(Sell sell);
	
	DataGrid getDataGrid(SellVO sellVO, Integer rows, Integer page,
			String sort, String order);

	Sell get(Integer id);

	void update(Sell sell);

	String delete(String ids);

	void copyProperties(Collection<Sell> sells, Collection<SellVO> sellVOs);


}
