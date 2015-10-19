package com.maxwell.pos.service;

import java.util.Map;

import com.maxwell.pos.model.GoodsItem;
import com.maxwell.pos.vo.GoodsItemVO;
import com.maxwell.pos.vo.base.DataGrid;

public interface GoodsItemManager {

	public void add(GoodsItem goodsItem);

	public void setGoodsItemsBy(String ids, Map<Integer, GoodsItem> goodsItems);
	
	public void editGoodsItemsBy(GoodsItemVO goodsItemVO,
			Map<Integer, GoodsItem> goodsItems);

	public void deleteGoodsItemBy(String ids, Map<Integer, GoodsItem> goodsItems);

	public DataGrid getDataGrid(GoodsItemVO goodsItemVO, Integer rows,
			Integer page, String sort, String order);

	public void deleteByIntoGoodsId(Integer id);

}
