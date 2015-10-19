package com.maxwell.pos.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.maxwell.pos.dao.CustomerDao;
import com.maxwell.pos.model.Customer;
import com.maxwell.pos.vo.CustomerVO;
import com.maxwell.pos.vo.base.DataGrid;

@Component("customerDao")
public class CustomerDaoImpl extends SuperDaoImpl<Customer, Integer> implements CustomerDao {

	public DataGrid getDataGrid(CustomerVO customerVO, Integer rows,
			Integer page, String sort, String order) {
		DataGrid datagrid = new DataGrid();
		
		StringBuffer sb = new StringBuffer();
		List<Object> values = new ArrayList<Object>();
		
		sb.append("from Customer c where 1=1 ");
		addWhere(customerVO, sb, values);
		
		Long total = (Long) queryObject("select count(*) " + sb.toString(), values);
		datagrid.setTotal(total);
		
		addOrderBy(sb, sort, order);
		
		@SuppressWarnings("unchecked")
		List<Customer> customers = (List<Customer>) find(sb.toString(), values, rows, page);
		
		List<CustomerVO> customerVOs = new ArrayList<CustomerVO>();
		
		for (Customer customer : customers) {
			customerVO = new CustomerVO();
			BeanUtils.copyProperties(customer, customerVO);
			customerVOs.add(customerVO);
		}
		datagrid.setRows(customerVOs);
		
		return datagrid;
	}

	private void addWhere(CustomerVO customerVO, StringBuffer sb,
			List<Object> values) {
		
		if (customerVO.getName() != null && !customerVO.getName().trim().equals("")) {
			sb.append(" and c.name like ? ");
			values.add("%%" + customerVO.getName().trim() + "%%");
		}
		
	}

}
