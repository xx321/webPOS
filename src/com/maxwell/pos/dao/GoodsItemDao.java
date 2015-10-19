package com.maxwell.pos.dao;

import java.util.Date;
import java.util.List;

import com.maxwell.pos.model.GoodsItem;
import com.maxwell.pos.vo.GoodsItemVO;
import com.maxwell.pos.vo.base.DataGrid;

public interface GoodsItemDao extends SuperDao<GoodsItem, Integer> {

	DataGrid getDataGrid(GoodsItemVO goodsItemVO, Integer rows, Integer page,
			String sort, String order);

	void deleteByIntoGoodsId(Integer id);

	List<GoodsItem> findByIntoGoodsId(Integer id);

	List<Object[]> getCostsBy(Date beginDate, Date endDate);
}
