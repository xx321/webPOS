package com.maxwell.pos.action;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.maxwell.pos.action.base.BaseAction;
import com.maxwell.pos.model.Category;
import com.maxwell.pos.model.ProductItem;
import com.maxwell.pos.service.CategoryManager;
import com.maxwell.pos.service.ProductItemManager;
import com.maxwell.pos.vo.ProductItemVO;
import com.maxwell.pos.vo.base.DataGrid;
import com.maxwell.pos.vo.base.Json;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;


@Component("productItemAction")
@Scope("prototype")
public class ProductItemAction extends BaseAction implements ModelDriven<ProductItemVO> {

	private static final long serialVersionUID = -6746723914944219520L;
	private Map<String, Object> session = ActionContext.getContext().getSession();
	private ProductItemManager productItemManager;
	private CategoryManager categoryManager;
	
	@Resource
	public void setProductItemManager(ProductItemManager productItemManager) {
		this.productItemManager = productItemManager;
	}
	@Resource
	public void setCategoryManager(CategoryManager categoryManager) {
		this.categoryManager = categoryManager;
	}
	
	private Map<Integer, ProductItem> productItems;
	
	private ProductItemVO productItemVO = new ProductItemVO();
	
	public ProductItemVO getModel() {
		return productItemVO;
	}
	
	public void productItemPrepare() {
		session.remove("productItems");
		session.remove("categorys");
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("");
		super.writeJson(j);
	}
	
	public String addPrepare() {
		List<Category> categorys = categoryManager.list();  //取得所有的類別訊息
		categorys.remove(categoryManager.get(99));  //移除 組合商品類別  *
		session.put("categorys", categorys);
		return SUCCESS;
	}
	
	public void add() {
		productItems = getSessionProductItems();
		
		productItemManager.setProductItemsBy(productItemVO.getIds(),productItems);
	}
	
	public void edit() {
		productItems = getSessionProductItems();
		
		productItemManager.editProductItemsBy(productItemVO,productItems);
		
		Json json = new Json();
		json.setSuccess(true);
		super.writeJson(json);
	}
	
	public void deleteProductItem() {
		productItems = getSessionProductItems();
		productItemManager.deleteProductItemBy(productItemVO.getIds(),productItems);
		
		Json json = new Json();
		json.setSuccess(true);
		super.writeJson(json);
	}
	
	public void productItemDataGrid() {
		productItems = getSessionProductItems();
		
		List<ProductItemVO> productItemVOs = new ArrayList<ProductItemVO>();
		
		for (ProductItem item : productItems.values()) {
			ProductItemVO itemVO = new ProductItemVO();
			itemVO.setProductId(item.getItem().getId());
			itemVO.setItemName(item.getItem().getName());
			itemVO.setQuantity(item.getQuantity());
			
			productItemVOs.add(itemVO);
		}
		
		DataGrid datagrid = new DataGrid();
		datagrid.setTotal((long) productItemVOs.size());
		datagrid.setRows(productItemVOs);
		
		super.writeJson(datagrid);		
	}
	@SuppressWarnings("unchecked")
	private Map<Integer, ProductItem> getSessionProductItems() {
		productItems = (Map<Integer, ProductItem>) session.get("productItems");
		if (productItems == null) {
			productItems = new LinkedHashMap<Integer, ProductItem>(0);
			session.put("productItems", productItems);
		}
		return productItems;
	}

}
