package com.maxwell.pos.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.maxwell.pos.action.base.BaseAction;
import com.maxwell.pos.model.Customer;
import com.maxwell.pos.service.CustomerManager;
import com.maxwell.pos.vo.CustomerVO;
import com.maxwell.pos.vo.base.DataGrid;
import com.maxwell.pos.vo.base.Json;
import com.opensymphony.xwork2.ModelDriven;

@Component("customerAction")
@Scope("prototype")
public class CustomerAction extends BaseAction implements ModelDriven<CustomerVO>{

	private static final long serialVersionUID = -2445454779120852438L;

	private CustomerManager customerManager;
	
	private Customer customer;
	
	@Resource
	public void setCustomerManager(CustomerManager customerManager) {
		this.customerManager = customerManager;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	private CustomerVO customerVO = new CustomerVO();
	
	public CustomerVO getModel() {
		return customerVO;
	}
	
	public String add() {
		customerManager.add(customer);
		return SUCCESS;
	}
	
	public String load() {
		if (customerVO.getId() == null) {
			return INPUT;
		}
		customer = customerManager.get(customerVO.getId());
		return SUCCESS;
	}
	
	public String update() {
		customerManager.update(customer);
		return SUCCESS;
	}
	
	public void delete() {
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg(customerManager.delete(customerVO.getIds()));
		super.writeJson(j);
	}
	public void datagrid() {
		
		DataGrid datagrid = customerManager.getDataGrid(customerVO, getRows(), getPage(), getSort(), getOrder());

		super.writeJson(datagrid);
	}
}
