package com.maxwell.pos.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.maxwell.pos.dao.CustomerDao;
import com.maxwell.pos.dao.TradeDao;
import com.maxwell.pos.model.Customer;
import com.maxwell.pos.service.CustomerManager;
import com.maxwell.pos.vo.CustomerVO;
import com.maxwell.pos.vo.base.DataGrid;

@Component("customerManager")
public class CustomerManagerImpl implements CustomerManager {

	private CustomerDao customerDao;
	private TradeDao tradeDao;
	
	@Resource
	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}
	@Resource
	public void setTradeDao(TradeDao tradeDao) {
		this.tradeDao = tradeDao;
	}

	public void add(Customer customer) {
		customerDao.save(customer);
	}

	public Customer get(Integer id) {
		return customerDao.findById(id);
	}

	public void update(Customer customer) {
		customerDao.update(customer);
	}

	public String delete(String ids) {
		Customer customer = null;
		String message = "";
		if (ids != null) {
			for (String id : ids.split(",")) {
				customer = customerDao.findById(new Integer(id.trim()));
				if (customer == null) {
					message = "您所要刪除的客戶不存在 !!";
					continue;
				}
				if (tradeDao.exists(customer.getId())) {
					message = "您所要刪除的客戶已經在使用 !! 建議您將客戶狀態設定為\"關閉 \"。";
				}
				customerDao.delete(customer);
				message = "客戶已經成功刪除 !!";
			}
		}
		return message;
	}

	public DataGrid getDataGrid(CustomerVO customerVO, Integer rows,
			Integer page, String sort, String order) {
		
		return customerDao.getDataGrid(customerVO, rows, page, sort, order);
	}
	
	
}
