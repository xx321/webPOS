package com.maxwell.pos.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.maxwell.pos.dao.SpendingItemDao;
import com.maxwell.pos.dao.SubjectDao;
import com.maxwell.pos.model.SpendingItem;
import com.maxwell.pos.model.Subject;
import com.maxwell.pos.service.SpendingItemManager;
import com.maxwell.pos.vo.SpendingItemVO;
import com.maxwell.pos.vo.base.DataGrid;

@Component("spendingItemManager")
public class SpendingItemManagerImpl implements SpendingItemManager {

	private SpendingItemDao spendingItemDao;
	private SubjectDao subjectDao;
	@Resource
	public void setSpendingItemDao(SpendingItemDao spendingItemDao) {
		this.spendingItemDao = spendingItemDao;
	}
	@Resource
	public void setSubjectDao(SubjectDao subjectDao) {
		this.subjectDao = subjectDao;
	}


	public void setSpendingItemsBy(String ids, Map<Integer, SpendingItem> spendingItems) {
		SpendingItem spendingItem = null;
		if (ids == null || "".equals(ids.trim()))
			return;
		for (String subjectId : ids.split(",")) {
			spendingItem = spendingItems.get(new Integer(subjectId.trim()));
			if (spendingItem != null)
				continue;
			
			Subject subject = subjectDao.findById(new Integer(subjectId.trim()));
			if (subject == null)
				continue;
			
			spendingItem = new SpendingItem();
			spendingItem.setSubject(subject);
			spendingItem.setTotal(new Double(0.00));
			
			spendingItems.put(new Integer(subjectId.trim()), spendingItem);
		}
		
	}
	
	public void editSpendingItemBy(SpendingItemVO spendingItemVO, Map<Integer, SpendingItem> spendingItems) {
		SpendingItem spendingItem = spendingItems.get(spendingItemVO.getSubjectId());
		if (spendingItem == null)
			return;
		spendingItem.setTotal(spendingItemVO.getTotal());
	}

	public void deleteSpendingItemBy(String ids, Map<Integer, SpendingItem> spendingItems) {
		if (ids == null)
			return;
		for (String subjectId : ids.split(",")) {
			spendingItems.remove(new Integer(subjectId.trim()));
		}
	}


	public DataGrid getDataGrid(SpendingItemVO spendingItemVO, Integer rows,
			Integer page, String sort, String order) {
		
		return spendingItemDao.getDataGrid(spendingItemVO, rows, page, sort, order);
	}
	public void deleteByPettyCashId(Integer id) {
		spendingItemDao.deleteByPettyCashId(id);
	}
	
	
}
