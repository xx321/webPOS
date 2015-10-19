package com.maxwell.pos.dao;

import java.util.List;

import com.maxwell.pos.model.IntoGoods;
import com.maxwell.pos.vo.IntoGoodsVO;
import com.maxwell.pos.vo.base.DataGrid;

public interface IntoGoodsDao extends SuperDao<IntoGoods, Integer> {

	boolean exists(Integer supplierId);

	DataGrid getDataGrid(IntoGoodsVO intoGoodsVO, Integer rows, Integer page,
			String sort, String order);

	List<IntoGoods> findListByIds(List<Integer> ids);

}
