package com.maxwell.pos.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.maxwell.pos.dao.IntoGoodsDao;
import com.maxwell.pos.dao.SupplierDao;
import com.maxwell.pos.model.Supplier;
import com.maxwell.pos.service.SupplierManager;
import com.maxwell.pos.vo.SupplierVO;
import com.maxwell.pos.vo.base.DataGrid;

@Component("supplierManager")
public class SupplierManagerImpl implements SupplierManager {

	private SupplierDao supplierDao;
	private IntoGoodsDao intoGoodsDao;
	
	@Resource
	public void setSupplierDao(SupplierDao supplierDao) {
		this.supplierDao = supplierDao;
	}
	@Resource
	public void setIntoGoodsDao(IntoGoodsDao intoGoodsDao) {
		this.intoGoodsDao = intoGoodsDao;
	}

	public void add(Supplier supplier) {
		supplierDao.save(supplier);
	}
	
	public Supplier get(Integer id) {
		return supplierDao.findById(id);
	}

	public void update(Supplier supplier) {
		supplierDao.update(supplier);
	}
	
	public String delete(String ids) {
		Supplier supplier = null;
		String message = "";
		if (ids != null) {
			for (String id : ids.split(",")) {
				supplier = supplierDao.findById(new Integer(id.trim()));
				if (supplier == null) {
					message =  "您所要刪除的廠商不存在 !!";
					continue;
				}
				if (intoGoodsDao.exists(supplier.getId())) {
					message = "您所要刪除的廠商已經在使用 !! 建議您將廠商狀態設定為\"關閉 \"。";
					continue;
				}
				supplierDao.delete(supplier);
				message = "廠商已經成功刪除 !!";
			}
		}
		return message;
	}
	
	public DataGrid getDataGrid(SupplierVO supplierVO, Integer rows,
			Integer page, String sort, String order) {

		return supplierDao.getDataGrid(supplierVO, rows, page, sort, order);
	}

	
}
