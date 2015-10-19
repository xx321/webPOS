package com.maxwell.pos.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.maxwell.pos.dao.SubjectDao;
import com.maxwell.pos.model.Subject;
import com.maxwell.pos.vo.SubjectVO;
import com.maxwell.pos.vo.base.DataGrid;

@Component("subjectDao")
public class SubjectDaoImpl extends SuperDaoImpl<Subject, Integer> implements
		SubjectDao {

	public DataGrid getDataGrid(SubjectVO subjectVO, Integer rows,
			Integer page, String sort, String order) {
		DataGrid datagrid = new DataGrid();
		
		StringBuffer sb = new StringBuffer();
		List<Object> values = new ArrayList<Object>();
		
		sb.append("from Subject s where 1=1 ");
		addWhere(subjectVO, sb, values);
		
		Long total = (Long) queryObject("select count(*) " + sb.toString(), values);
		datagrid.setTotal(total);
		
		addOrderBy(sb, sort, order);
		
		@SuppressWarnings("unchecked")
		List<Subject> subjects = (List<Subject>) find(sb.toString(), values, rows, page);
		
		List<SubjectVO> subjectVOs = new ArrayList<SubjectVO>();
		
		for (Subject subject : subjects) {
			subjectVO = new SubjectVO();
			BeanUtils.copyProperties(subject, subjectVO);
			subjectVOs.add(subjectVO);
		}
		datagrid.setRows(subjectVOs);
		
		return datagrid;
	}

	private void addWhere(SubjectVO subjectVO, StringBuffer sb,
			List<Object> values) {
		
		if (subjectVO.getName() != null && !subjectVO.getName().trim().equals("")) {
			sb.append(" and s.name like ? ");
			values.add("%%" + subjectVO.getName().trim() + "%%");
		}
		
		if (subjectVO.getStatus() != null) {
			sb.append(" and s.status =? ");
			values.add(subjectVO.getStatus());
		}
		
	}
	
}
