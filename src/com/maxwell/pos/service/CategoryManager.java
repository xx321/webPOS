package com.maxwell.pos.service;

import java.util.List;

import com.maxwell.pos.model.Category;
import com.maxwell.pos.vo.CategoryVO;
import com.maxwell.pos.vo.base.DataGrid;

public interface CategoryManager {
	public List<Category> list();
	
	public Category get(Integer id);
	
	public void update(Category category);
	
	public void delete(String ids);
	
	public void add(Category category);

	public List<Category> getListOrderBy();

	public DataGrid getDataGrid(CategoryVO categoryVO, Integer rows, Integer page, String sort, String order);

}
