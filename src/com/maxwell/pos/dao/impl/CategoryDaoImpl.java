package com.maxwell.pos.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.maxwell.pos.dao.CategoryDao;
import com.maxwell.pos.model.Category;
import com.maxwell.pos.vo.CategoryVO;
import com.maxwell.pos.vo.base.DataGrid;

@Component("categoryDao")
public class CategoryDaoImpl extends SuperDaoImpl<Category, Integer> implements
		CategoryDao {

	@SuppressWarnings("unchecked")
	public List<Category> findListByOrder() {
		return getHibernateTemplate().find(
				"from Category as c where c.status=1 order by c.displayOrder");
	}
	
	public DataGrid getDataGrid(CategoryVO categoryVO, Integer rows,
			Integer page, String sort, String order) {
		DataGrid datagrid = new DataGrid();
		
		StringBuffer sb = new StringBuffer();
		List<Object> values = new ArrayList<Object>();
		
		sb.append("from Category c where 1=1 ");
		addWhere(categoryVO, sb, values);
		
		Long total = (Long) queryObject("select count(*) " + sb.toString(), values);
		datagrid.setTotal(total);
		
		addOrderBy(sb, sort, order);
		
		@SuppressWarnings("unchecked")
		List<Category> categorys = (List<Category>) find(sb.toString(), values, rows, page);
		
		List<CategoryVO> categoryVOs = new ArrayList<CategoryVO>();
		
		for (Category category : categorys) {
			categoryVO = new CategoryVO();
			BeanUtils.copyProperties(category, categoryVO);
			categoryVOs.add(categoryVO);
		}
		datagrid.setRows(categoryVOs);
		
		return datagrid;
	}
	
	//增加查詢功能 : 添加 where 條件  方法。(CategoryDemo 專用)
	private void addWhere(CategoryVO categoryVO, StringBuffer sb, List<Object> values) {
		
		if (categoryVO.getName() != null && !categoryVO.getName().trim().equals("")) {
			sb.append(" and c.name like ? ");
			values.add("%%" + categoryVO.getName().trim() + "%%");
		}
		
		//直接轉換成 字串形式，不必帶參。
//		if (categoryVO.getName() != null && !categoryVO.getName().trim().equals("")) {
//		sb.append(" and c.name like '%%");
//		sb.append(categoryVO.getName().trim());
//		sb.append("%%'");
//	}
		//以下 是查詢的參數  帶有時間範圍  的方法。
		//時間做為條件時，需要加一個List 來整合參數。
//		if (categorydemo.getCreateTimeStart() != null) {
//			sb.append(" and c.createTime>=? ");
//			values.add(categorydemo).getCreateTimeStart());
//		}
//		if (categorydemo.getCreateTimeEnd() != null) {
//			sb.append(" and c.createTime<=? ");
//			values.add(categorydemo).getCreateTimeEnd());
//		}
	}

	@SuppressWarnings("unchecked")
	public List<Category> findAllByDisplay() {
		return getHibernateTemplate().find(
				"from Category as c where c.id !=99 order by c.displayOrder");
	}


}
