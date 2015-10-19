package com.maxwell.pos.dao;

import com.maxwell.pos.model.Customer;
import com.maxwell.pos.vo.CustomerVO;
import com.maxwell.pos.vo.base.DataGrid;

public interface CustomerDao extends SuperDao<Customer, Integer> {

	DataGrid getDataGrid(CustomerVO customerVO, Integer rows, Integer page,
			String sort, String order);

}
