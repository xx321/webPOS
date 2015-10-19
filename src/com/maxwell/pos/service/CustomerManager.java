package com.maxwell.pos.service;

import com.maxwell.pos.model.Customer;
import com.maxwell.pos.vo.CustomerVO;
import com.maxwell.pos.vo.base.DataGrid;

public interface CustomerManager {

	public void add(Customer customer);

	public Customer get(Integer id);

	public void update(Customer customer);

	public String delete(String ids);

	public DataGrid getDataGrid(CustomerVO customerVO, Integer rows, Integer page,
			String sort, String order);

}
