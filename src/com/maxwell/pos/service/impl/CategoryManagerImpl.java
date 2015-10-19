package com.maxwell.pos.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.maxwell.pos.dao.CategoryDao;
import com.maxwell.pos.dao.ProductDao;
import com.maxwell.pos.model.Category;
import com.maxwell.pos.model.Product;
import com.maxwell.pos.service.CategoryManager;
import com.maxwell.pos.vo.CategoryVO;
import com.maxwell.pos.vo.base.DataGrid;

@Component("categoryManager")
public class CategoryManagerImpl implements CategoryManager {
	private CategoryDao categoryDao;
	private ProductDao productDao;
	
	@Resource
	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}
	@Resource
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	public List<Category> list() {
		return categoryDao.findAllByDisplay();
	}

	public Category get(Integer id) {
		return categoryDao.findById(id);
	}

	public void update(Category category) {
		categoryDao.update(category);
	}

	public void delete(String ids) {
		Category category = categoryDao.findById(100);
		if (ids != null && !"".equals(ids.trim())) {
			for (String id : ids.split(",")) {
				if (new Integer(id.trim()) >100) {
					List<Product> list = productDao.findForDeleteBy(new Integer(id.trim()));
					if (list !=null && list.size()!=0)
						for (Product p : list) {
							p.setCategory(category);
							productDao.update(p);
						}
					categoryDao.delete(categoryDao.findById(new Integer(id.trim())));
				}
			}
		}
	}
	
	public void add(Category category) {
		categoryDao.save(category);
	}
	
	
	
	public List<Category> getListOrderBy() {
		return categoryDao.findListByOrder();
	}
	
	public DataGrid getDataGrid(CategoryVO categoryVO, Integer rows, Integer page, String sort, String order) {
		return categoryDao.getDataGrid(categoryVO, rows, page, sort, order);
	}
	
}
