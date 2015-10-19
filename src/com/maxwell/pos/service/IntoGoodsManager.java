package com.maxwell.pos.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.maxwell.pos.model.GoodsItem;
import com.maxwell.pos.model.IntoGoods;
import com.maxwell.pos.vo.IntoGoodsVO;
import com.maxwell.pos.vo.base.DataGrid;

public interface IntoGoodsManager {

	public void add(IntoGoods intoGoods);

	public DataGrid getDataGrid(IntoGoodsVO intoGoodsVO, Integer rows,
			Integer page, String sort, String order);

	public IntoGoods findBy(Map<String, Object> session,
			IntoGoodsVO intoGoodsVO);
	
	String purchase(Map<Integer, GoodsItem> goodsItems, IntoGoods intoGoods);
	
	void updatePurchase(IntoGoods intoGoods);
	void deletePurchase(IntoGoods intoGoods);
	
	public IntoGoods get(Integer id);

	public void update(IntoGoods intoGoods);

	public String delete(String ids);

	public List<IntoGoods> getListByIds(String idsStr);

	public void copyProperties(IntoGoods intoGoods, IntoGoodsVO intoGoodsVO);

	public void copyProperties(Collection<IntoGoods> intoGoodses,
			Collection<IntoGoodsVO> intoGoodsVOs);
}
