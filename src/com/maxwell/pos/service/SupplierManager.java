package com.maxwell.pos.service;

import com.maxwell.pos.model.Supplier;
import com.maxwell.pos.vo.SupplierVO;
import com.maxwell.pos.vo.base.DataGrid;

public interface SupplierManager {

	public void add(Supplier supplier);

	public DataGrid getDataGrid(SupplierVO supplierVO, Integer rows,
			Integer page, String sort, String order);

	public Supplier get(Integer id);

	public void update(Supplier supplier);

	public String delete(String ids);
	
}
