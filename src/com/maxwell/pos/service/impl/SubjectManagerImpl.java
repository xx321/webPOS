package com.maxwell.pos.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.maxwell.pos.dao.SpendingItemDao;
import com.maxwell.pos.dao.SubjectDao;
import com.maxwell.pos.model.Subject;
import com.maxwell.pos.service.SubjectManager;
import com.maxwell.pos.vo.SubjectVO;
import com.maxwell.pos.vo.base.DataGrid;

@Component("subjectManager")
public class SubjectManagerImpl implements SubjectManager {

	private SubjectDao subjectDao;
	private SpendingItemDao spendingItemDao;
	
	@Resource
	public void setSubjectDao(SubjectDao subjectDao) {
		this.subjectDao = subjectDao;
	}

	@Resource
	public void setSpendingItemDao(SpendingItemDao spendingItemDao) {
		this.spendingItemDao = spendingItemDao;
	}


	public void add(Subject subject) {
		subjectDao.save(subject);
	}

	public Subject get(Integer id) {
		return subjectDao.findById(id);
	}


	public void update(Subject subject) {
		subjectDao.update(subject);
	}
	
	public String delete(String ids) {
		Subject subject = null;
		String message = "";
		if (ids != null) {
			for (String id : ids.split(",")) {
				subject = subjectDao.findById(new Integer(id.trim()));
				if (subject == null) {
					message = "您所要刪除的項目不存在!!";
					continue;
				}
				if (spendingItemDao.exists(subject.getId())) {
					message = "您所要刪除的項目已經在使用!! 建議您將項目狀態設定為\"關閉\"。";
					continue;
				}
				subjectDao.delete(subject);
				message = "項目已經成功刪除!!";
			}
		}
		return message;
	}
	public DataGrid getDataGrid(SubjectVO subjectVO, Integer rows,
			Integer page, String sort, String order) {
	
		return subjectDao.getDataGrid(subjectVO, rows, page, sort, order);
	}

}
