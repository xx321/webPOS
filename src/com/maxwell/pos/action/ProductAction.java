package com.maxwell.pos.action;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.maxwell.pos.action.base.BaseAction;
import com.maxwell.pos.model.Category;
import com.maxwell.pos.model.Product;
import com.maxwell.pos.model.ProductItem;
import com.maxwell.pos.service.CategoryManager;
import com.maxwell.pos.service.ProductItemManager;
import com.maxwell.pos.service.ProductManager;
import com.maxwell.pos.util.NumberUtil;
import com.maxwell.pos.vo.ProductItemVO;
import com.maxwell.pos.vo.ProductVO;
import com.maxwell.pos.vo.base.DataGrid;
import com.maxwell.pos.vo.base.Json;
import com.opensymphony.xwork2.ActionContext;

@Component("productAction")
@Scope("prototype")
public class ProductAction extends BaseAction {

	private static final long serialVersionUID = 7243381200637891444L;
	private Map<String, Object> session = ActionContext.getContext().getSession();
	private ProductManager productManager;
	private ProductItemManager productItemManager;
	private CategoryManager categoryManager;
	private List<Product> products;
	private Integer id;
	private Product product;
	
	private String name;
	private String ids;
	private String message;
	
	private Integer categoryId;
	
	@Resource
	public void setProductManager(ProductManager productManager) {
		this.productManager = productManager;
	}
	@Resource
	public void setProductItemManager(ProductItemManager productItemManager) {
		this.productItemManager = productItemManager;
	}

	@Resource
	public void setCategoryManager(CategoryManager categoryManager) {
		this.categoryManager = categoryManager;
	}

	public List<Product> getProducts() {
		return products;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

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
	
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String load() {
		if (id == null) {
			return INPUT;
		}
		product = productManager.get(id);
		
		List<Category> categorys = categoryManager.list();
		session.put("categorys", categorys);
		
		if (product.getType() ==2) {
			String ids = getProductIds(product);
			Map<Integer, ProductItem> productItems = new LinkedHashMap<Integer, ProductItem>(0);			
			productItemManager.setProductItemsBy(ids, productItems);
			List<ProductItemVO> productItemVOs = getProductItemVOs(product);
			for (ProductItemVO productItemVO : productItemVOs) {
				productItemManager.editProductItemsBy(productItemVO, productItems);
			}
			
			session.put("productItems", productItems);
			
			categorys.remove(categoryManager.get(99));  //移除 組合商品類別  *
			return "editProductItem";
		}
		
		return SUCCESS;
	}
	
	private List<ProductItemVO> getProductItemVOs(Product product) {
		List<ProductItemVO> productItemVOs = new ArrayList<ProductItemVO>();
		
		for (ProductItem item : product.getProductItems()) {
			ProductItemVO itemVO = new ProductItemVO();
			itemVO.setProductId(item.getItem().getId());
			itemVO.setItemName(item.getItem().getName());
			itemVO.setQuantity(item.getQuantity());
			
			productItemVOs.add(itemVO);
		}
		return productItemVOs;
	}
	private String getProductIds(Product product) {
		StringBuffer sb = new StringBuffer();
		for (ProductItem p : product.getProductItems()) {
			sb.append(p.getItem().getId().toString());
			sb.append(",");
		}
		return sb.toString();
	}
	
	//商品添加與編輯 : 表單多出一維條碼編號，新增、修改時，都要先查看，資料的一維條碼編號是否重覆。
	public String update() {
		if ("預設".equals(product.getBarcodeNumber().trim()) || "".equals(product.getBarcodeNumber().trim())) {
			product.setBarcodeNumber(""); //預設的一維條碼 需要實作方法，自動設置。(未實作)
		}
		//檢查是否有重覆的條碼編號，假如有，編輯商品失敗!
		List<Product> products = productManager.findListByBarcodeNumber(product.getBarcodeNumber());
		if (!"".equals(product.getBarcodeNumber()) && products.size() >= 1 && !products.get(0).getId().equals(product.getId())) {
			message = "商品條碼重覆<br />請檢查商品條碼設定!";
			return SUCCESS;
		}
		
		Product oldProduct = productManager.get(product.getId());	
		BeanUtils.copyProperties(product, oldProduct,new String[]{"purchasePrice"});
		oldProduct.setPrice(NumberUtil.formatDouble(product.getPrice()));
			
		if (oldProduct.getType() == 2) {  //判斷為組合商品時，額外再做  組合商品裡的項目編輯
			@SuppressWarnings("unchecked")
			Map<Integer, ProductItem> productItems = (Map<Integer, ProductItem>) session.get("productItems");
			message = productItemManager.setProductItemsBy(productItems, oldProduct);
			if (!"".equals(message))
				return SUCCESS;
		}	
		
		productManager.update(oldProduct);
		return SUCCESS;
	}
	
	public void delete() {

		Json j = new Json();
		j.setSuccess(true);
		j.setMsg(productManager.delete(ids));
		super.writeJson(j);
		
	}
	
	public String addPrepare() {
		List<Category> categorys = categoryManager.list();  //取得所有的類別訊息
		categorys.remove(categoryManager.get(99));  //移除 組合商品類別  *
		session.put("categorys", categorys);
		return SUCCESS;
	}
	
	//商品添加與編輯 : 表單多出一維條碼編號，新增、修改時，都要先查看，資料的一維條碼編號是否重覆。
	public String add() {
		if ("預設".equals(product.getBarcodeNumber().trim()) || "".equals(product.getBarcodeNumber().trim())) {
			product.setBarcodeNumber(""); //預設的一維條碼 需要實作方法，自動設置。(未實作)
		}
		//檢查是否有重覆的條碼編號，假如有，新增商品失敗!
		if (!"".equals(product.getBarcodeNumber()) && productManager.findByBarcode(product.getBarcodeNumber()) !=null) {
			message = "商品條碼重覆<br />請檢查商品條碼設定!";
			return SUCCESS;
		}
		
		product.setPrice(NumberUtil.formatDouble(product.getPrice()));
		product.setStockNumber(0);
		product.setPurchasePrice(0.00);
		
		if (product.getType() == 2) { //判斷為組合商品時，額外再做  組合商品裡的項目編輯
			@SuppressWarnings("unchecked")
			Map<Integer, ProductItem> productItems = (Map<Integer, ProductItem>) session.get("productItems");
			
			for (ProductItem item : productItems.values()) {
				if (item.getQuantity() <=0)
					continue;
				item.setProduct(product);
				product.getProductItems().add(item);
			}
			
			if (product.getProductItems().size() <=0) {
				message = "組合商品操作失敗 !";
				return SUCCESS;
			}
		}
		
		productManager.add(product);
		
		return SUCCESS;
	}
	
	public void datagrid() {
		
		ProductVO productVO = new ProductVO();
		BeanUtils.copyProperties(this, productVO);
		
		DataGrid datagrid = productManager.getDataGrid(productVO, getRows(), getPage(), getSort(), getOrder());

		super.writeJson(datagrid);
	}
	
	public void stockDatagrid() {
		
		ProductVO productVO = new ProductVO();
		BeanUtils.copyProperties(this, productVO);
		
		productVO.setType(new Integer(1));
		
		DataGrid datagrid = productManager.getDataGrid(productVO, getRows(), getPage(), getSort(), getOrder());

		super.writeJson(datagrid);
	}
//	public String stockList() {
//		products = productManager.stockList();
//		return SUCCESS;
//	}
	
	public String updateStock() {//手動調整庫存數量時，連動更改平均成本, 未完整...
		Product oldProduct = productManager.get(product.getId());
		if (product.getStockNumber() == null)
			product.setStockNumber(new Integer(0));
		oldProduct.setStockNumber(product.getStockNumber());
		
		productManager.update(oldProduct);
		
		return SUCCESS;
	}

}
