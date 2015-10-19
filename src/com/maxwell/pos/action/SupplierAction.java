package com.maxwell.pos.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.maxwell.pos.action.base.BaseAction;
import com.maxwell.pos.model.Supplier;
import com.maxwell.pos.service.SupplierManager;
import com.maxwell.pos.vo.SupplierVO;
import com.maxwell.pos.vo.base.DataGrid;
import com.maxwell.pos.vo.base.Json;
import com.opensymphony.xwork2.ModelDriven;

@Component("supplierAction")
@Scope("prototype")
public class SupplierAction extends BaseAction implements ModelDriven<SupplierVO> {

	private static final long serialVersionUID = 1061306752031057134L;
	
	private SupplierManager supplierManager;
	
	private Supplier supplier;

	@Resource
	public void setSupplierManager(SupplierManager supplierManager) {
		this.supplierManager = supplierManager;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	private SupplierVO supplierVO = new SupplierVO();
	
	public SupplierVO getModel() {
		return supplierVO;
	}
	
	public String add() {
		supplierManager.add(supplier);
		return SUCCESS;
	}
	
	public String load() {
		if (supplierVO.getId() == null) {
			return INPUT;
		}
		supplier = supplierManager.get(supplierVO.getId());
		return SUCCESS;
	}
	
	public String update() {
		supplierManager.update(supplier);
		return SUCCESS;
	}
	
	public void delete() {
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg(supplierManager.delete(supplierVO.getIds()));
		super.writeJson(j);
	}
	
	public void datagrid() {
		
		DataGrid datagrid = supplierManager.getDataGrid(supplierVO, getRows(), getPage(), getSort(), getOrder());

		super.writeJson(datagrid);
	}

}
