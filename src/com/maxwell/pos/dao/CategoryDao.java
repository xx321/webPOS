package com.maxwell.pos.dao;

import java.util.List;

import com.maxwell.pos.model.Category;
import com.maxwell.pos.vo.CategoryVO;
import com.maxwell.pos.vo.base.DataGrid;

public interface CategoryDao extends SuperDao<Category, Integer> {

	List<Category> findListByOrder();

	DataGrid getDataGrid(CategoryVO categoryVO, Integer rows, Integer page,
			String sort, String order);

	List<Category> findAllByDisplay();


}
