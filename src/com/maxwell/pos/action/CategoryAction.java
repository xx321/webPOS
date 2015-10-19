package com.maxwell.pos.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.maxwell.pos.action.base.BaseAction;
import com.maxwell.pos.model.Category;
import com.maxwell.pos.service.CategoryManager;
import com.maxwell.pos.vo.CategoryVO;
import com.maxwell.pos.vo.base.DataGrid;
import com.maxwell.pos.vo.base.Json;

@Component("categoryAction")
@Scope("prototype")
public class CategoryAction extends BaseAction {

	private static final long serialVersionUID = -2197263246976922238L;
	private CategoryManager categoryManager;
	private List<Category> categorys;
	private Integer id;
	private Category category;
	private String name;
	private String ids;
	
	private String message;
	
	@Resource
	public void setCategoryManager(CategoryManager categoryManager) {
		this.categoryManager = categoryManager;
	}
	
	public List<Category> getCategorys() {
		return categorys;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	/*		新增的方法		*/
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	/*		新增的方法		*/
	public String list() {
//		categorys = categoryManager.list();
		return SUCCESS;
	}
	
	public String load() {
		if (id == null) {
			return INPUT;
		}
		category = categoryManager.get(id);
		return SUCCESS;
	}
	
	public String update() {
		categoryManager.update(category);
		return SUCCESS;
	}
	
	public void delete() {
//		if (id != null && id != 100) {
//			// 刪除該類別後，所有屬於該類別的商品會屬於編號為100的類別(未分類)
//			categoryManager.delete(id, 100);
//		}
		Json j = new Json();
		categoryManager.delete(ids);
		j.setSuccess(true);
		j.setMsg("删除成功!");
		super.writeJson(j);
	}
	
	public String add() {
		categoryManager.add(category);
		return SUCCESS;
	}
	
	public void datagrid() {
		
		CategoryVO categoryVO = new CategoryVO();
		BeanUtils.copyProperties(this, categoryVO);
		
		DataGrid datagrid = categoryManager.getDataGrid(categoryVO, getRows(), getPage(), getSort(), getOrder());

		super.writeJson(datagrid);
	}

}
