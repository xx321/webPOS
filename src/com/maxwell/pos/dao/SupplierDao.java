package com.maxwell.pos.dao;

import com.maxwell.pos.model.Supplier;
import com.maxwell.pos.vo.SupplierVO;
import com.maxwell.pos.vo.base.DataGrid;

public interface SupplierDao extends SuperDao<Supplier, Integer> {

	public DataGrid getDataGrid(SupplierVO supplierVO, Integer rows, Integer page,
			String sort, String order);

}
