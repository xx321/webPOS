package com.maxwell.pos.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.maxwell.pos.dao.SupplierDao;
import com.maxwell.pos.model.Supplier;
import com.maxwell.pos.vo.SupplierVO;
import com.maxwell.pos.vo.base.DataGrid;

@Component("supplierDao")
public class SupplierDaoImpl extends SuperDaoImpl<Supplier, Integer> implements 
	SupplierDao {

	public DataGrid getDataGrid(SupplierVO supplierVO, Integer rows,
			Integer page, String sort, String order) {
		DataGrid datagrid = new DataGrid();
		
		StringBuffer sb = new StringBuffer();
		List<Object> values = new ArrayList<Object>();
		
		sb.append("from Supplier s where 1=1 ");
		addWhere(supplierVO, sb, values);
		
		Long total = (Long) queryObject("select count(*) " + sb.toString(), values);
		datagrid.setTotal(total);
		
		addOrderBy(sb, sort, order);
		
		@SuppressWarnings("unchecked")
		List<Supplier> suppliers = (List<Supplier>) find(sb.toString(), values, rows, page);
		
		List<SupplierVO> supplierVOs = new ArrayList<SupplierVO>();
		
		for (Supplier supplier : suppliers) {
			supplierVO = new SupplierVO();
			BeanUtils.copyProperties(supplier, supplierVO);
			supplierVOs.add(supplierVO);
		}
		datagrid.setRows(supplierVOs);
		
		return datagrid;
	}

	private void addWhere(SupplierVO supplierVO, StringBuffer sb,
			List<Object> values) {
		
		if (supplierVO.getName() != null && !supplierVO.getName().trim().equals("")) {
			sb.append(" and s.name like ? ");
			values.add("%%" + supplierVO.getName().trim() + "%%");
		}
		
	}

	
}
