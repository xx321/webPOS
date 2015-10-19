package com.maxwell.pos.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.maxwell.pos.dao.PettyCashDao;
import com.maxwell.pos.dao.SubjectDao;
import com.maxwell.pos.dao.SupplierDao;
import com.maxwell.pos.model.PettyCash;
import com.maxwell.pos.model.SpendingItem;
import com.maxwell.pos.model.Subject;
import com.maxwell.pos.service.PettyCashManager;
import com.maxwell.pos.util.DateUtil;
import com.maxwell.pos.vo.PettyCashVO;
import com.maxwell.pos.vo.base.DataGrid;

@Component("pettyCashManager")
public class PettyCashManagerImpl implements PettyCashManager {

	private PettyCashDao pettyCashDao;
	private SupplierDao supplierDao;
	private SubjectDao subjectDao;
	
	@Resource
	public void setPettyCashDao(PettyCashDao pettyCashDao) {
		this.pettyCashDao = pettyCashDao;
	}
	@Resource
	public void setSupplierDao(SupplierDao supplierDao) {
		this.supplierDao = supplierDao;
	}
	@Resource
	public void setSubjectDao(SubjectDao subjectDao) {
		this.subjectDao = subjectDao;
	}

	public PettyCash findBy(Map<String, Object> session, PettyCashVO pettyCashVO) {
		
		PettyCash pettyCash = new PettyCash();
		BeanUtils.copyProperties(pettyCashVO, pettyCash);
		if (pettyCashVO.getSupplierId() != null) 
			pettyCash.setSupplier(supplierDao.findById(pettyCashVO.getSupplierId()));
		pettyCash.setCreateTime(DateUtil.toDate(pettyCashVO.getCreateTimeStr()));
		pettyCash.setPaymentTime(DateUtil.toDate(pettyCashVO.getPaymentTimeStr()));
		
		return pettyCash;
	}

	public String setSpendingItems(Map<Integer, SpendingItem> spendingItems, PettyCash pettyCash) {
		if (pettyCash.getTax() == null)
			pettyCash.setTax(new Double(0.0));
		
		String message = "";
		Subject subject = null;
		for (SpendingItem item : spendingItems.values()) {
			if (item.getTotal() ==0) {
				continue;
			}
			subject = subjectDao.findById(item.getSubject().getId());
			if (subject == null) {
				continue;
			}
			
			item.setTotalAmount(item.getTotal() * (1+pettyCash.getTax()));
			item.setSubject(subject);
			item.setPettyCash(pettyCash);
			pettyCash.getSpendingItems().add(item);
			message = "儲存成功 !";
		}
		
		return message;
	}
	
	public void add(PettyCash pettyCash) {
		pettyCashDao.save(pettyCash);
	}
	
	public DataGrid getDataGrid(PettyCashVO pettyCashVO, Integer rows,
			Integer page, String sort, String order) {
		
		return pettyCashDao.getDataGrid(pettyCashVO, rows, page, sort, order);
	}
	
	public PettyCash get(Integer id) {
		return pettyCashDao.findById(id);
	}
	
	public void update(PettyCash pettyCash) {
		pettyCashDao.update(pettyCash);
	}
	public String delete(String ids) {
		PettyCash pettyCash = null;
		String message = "";
		if (ids != null) {
			for (String id : ids.split(",")) {
				pettyCash = pettyCashDao.findById(new Integer(id.trim()));
				if (pettyCash == null) {
					message =  "您所要刪除的訊息不存在 !!";
					continue;
				}
				pettyCashDao.delete(pettyCash);
				message = "成功刪除 !!";
			}
		}
		return message;
	}

}
