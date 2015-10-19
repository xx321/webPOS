package com.maxwell.pos.dao;

import java.util.Date;
import java.util.List;

import com.maxwell.pos.model.SpendingItem;
import com.maxwell.pos.vo.SpendingItemVO;
import com.maxwell.pos.vo.base.DataGrid;

public interface SpendingItemDao extends SuperDao<SpendingItem, Integer> {

	boolean exists(Integer subjectId);

	DataGrid getDataGrid(SpendingItemVO spendingItemVO, Integer rows,
			Integer page, String sort, String order);

	void deleteByPettyCashId(Integer id);

	List<Object[]> getFeesBy(Date beginDate, Date endDate);

}
