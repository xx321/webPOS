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
import com.maxwell.pos.model.Product;
import com.maxwell.pos.model.Trade;
import com.maxwell.pos.model.Tradeitem;

import com.maxwell.pos.service.CategoryManager;
import com.maxwell.pos.service.ProductManager;
import com.maxwell.pos.util.NumberUtil;
import com.maxwell.pos.vo.TradeitemVO;
import com.maxwell.pos.vo.base.DataGrid;
import com.maxwell.pos.vo.base.Json;

import com.opensymphony.xwork2.ActionContext;

@Component("homeAction")
@Scope("prototype")
public class HomeAction extends BaseAction {
	
	private static final long serialVersionUID = 4480600841396297428L;
	private Map<String, Object> session = ActionContext.getContext().getSession();
	
	private CategoryManager categoryManager;
	private ProductManager productManager;
	
	private Map<Integer, Tradeitem> tradeItems;
	private Trade trade;
	private List<Category> categorys;
	private List<Product> products;

	private Integer categoryId;
	private Integer productId;
	private String barcodeNumber;
//	private Integer totalItem;
//	private Double totalAmount;
	
	private String quantity;
	
	private String ids;
	
	@Resource
	public void setCategoryManager(CategoryManager categoryManager) {
		this.categoryManager = categoryManager;
	}
	@Resource
	public void setProductManager(ProductManager productManager) {
		this.productManager = productManager;
	}

	public Map<Integer, Tradeitem> getTradeItems() {
		return tradeItems;
	}
	public void setTradeItems(Map<Integer, Tradeitem> tradeItems) {
		this.tradeItems = tradeItems;
	}
	public Trade getTrade() {
		return trade;
	}
	public List<Category> getCategorys() {
		return categorys;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public void setBarcodeNumber(String barcodeNumber) {
		this.barcodeNumber = barcodeNumber;
	}
//	public Integer getTotalItem() {
//		return totalItem;
//	}
//	public Double getTotalAmount() {
//		return totalAmount;
//	}
	
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	
	public String list() {
		
		session.remove("checkout");
		
		categorys = categoryManager.getListOrderBy();  //取得使用中的類別 訊息
		session.put("categorys", categorys);
		
		products = productManager.getListByCategoryId(100);
		session.put("products", products);
		
		tradeItems = new LinkedHashMap<Integer, Tradeitem>(0);
		session.put("tradeItems", tradeItems);
////		
//		totalItem = 0;
//		session.put("totalItem", totalItem);
//		totalAmount = 0.00;
//		session.put("totalAmount", totalAmount);
		
		
		return SUCCESS;
	}
	
	public void loadProducts() {

		if(categoryId != null) {
			products = productManager.getListByCategoryId(categoryId);
		}
		
		String divStr = formatDivStr(products);
		
		Json json = new Json();
		json.setSuccess(true);
		json.setMsg(divStr);
		super.writeJson(json);
	}
	//硬編碼 : 注意 ，完全不建議 使用這種方法 !  用來做前台無刷新 div 轉換 。
	private String formatDivStr(List<Product> products) {
		StringBuffer sb = new StringBuffer();
		sb.append("<ul id=\"htcList\">");
		sb.append("\n</ul>");
		sb.append("\n<ul id=\"Searchresult\" style=\"display:none;\">");
		for (Product p : products) {
			sb.append("\n<li><a class=\"css_commodity\"");
			sb.append(" href=\"javascript:void(0)\" onclick=\"submitByMenu(");
			sb.append(p.getId());
			sb.append(")\"> ");
			sb.append(p.getName());
			sb.append(" </a>\n</li>");
		}
		sb.append("\n</ul>");
		return sb.toString();
	}
	//購買商品 : 數據表格
	@SuppressWarnings("unchecked")
	public void tradeItemDataGrid() {
		tradeItems = (Map<Integer, Tradeitem>) session.get("tradeItems");
		
		List<TradeitemVO> tradeitemVOs = new ArrayList<TradeitemVO>();
		
		for (Tradeitem item : tradeItems.values()) {
			TradeitemVO itemVO = copyItem(item);	
			tradeitemVOs.add(itemVO);
		}
		
		//添加 footer 的範例
//		List<ItemFooter> footer = new ArrayList<ItemFooter>();
//		ItemFooter itemFooter = new ItemFooter();
//		itemFooter.setQuantity((Integer) session.get("totalItem"));
//		itemFooter.setTotal((Integer) session.get("totalAmount"));
//		footer.add(itemFooter);
		
		
		DataGrid datagrid = new DataGrid();	
		datagrid.setTotal((long) tradeitemVOs.size());
		datagrid.setRows(tradeitemVOs);
//		datagrid.setFooter(footer);
		
		super.writeJson(datagrid);
	}
	private TradeitemVO copyItem(Tradeitem item) {
		TradeitemVO itemVO = new TradeitemVO();
		itemVO.setProductId(item.getProduct().getId());
		itemVO.setProductName(item.getProduct().getName());
		itemVO.setQuantity(item.getQuantity());
		itemVO.setPurchasePrice(item.getPurchasePrice());
		itemVO.setTotal(item.getTotal());		
		itemVO.setUnit(item.getProduct().getUnit());
		return itemVO;
	}
	@SuppressWarnings("unchecked")
	public void loadTradeItem() {
		
		Json json = new Json();
		
		//實現條碼機掃描功能。
		if (productId == null && barcodeNumber != null) {
			if ("".equals(barcodeNumber.trim()))
				return;
			Product product = productManager.findByBarcode(barcodeNumber);
			if (product == null) {
				json.setSuccess(false);
				json.setMsg("掃描的條碼編號無效<br />請檢查商品條碼設定!");
				super.writeJson(json);
				return;
			}
			productId = product.getId();
		}

		
		tradeItems = (Map<Integer, Tradeitem>) session.get("tradeItems");
		Tradeitem tradeItem = tradeItems.get(productId);
		
		if (tradeItem == null) {
			Product product = productManager.get(productId);
			
			tradeItem = new Tradeitem();
			tradeItem.setProduct(product);
			tradeItem.setQuantity(new Integer(0));
			tradeItem.setPurchasePrice(product.getPrice());
			tradeItems.put(productId, tradeItem);
		}
		if (quantity == null || "".equals(quantity.trim()) || "0".equals(quantity.trim())) {
			return;
		} else if ("+1".equals(quantity.trim())) {
			tradeItem.setQuantity(tradeItem.getQuantity()+1);
		} else {		
			tradeItem.setQuantity(new Integer(quantity.trim()));
		}
		
		tradeItem.setTotal(NumberUtil.formatDouble(tradeItem.getQuantity()*tradeItem.getPurchasePrice()));
////	
//		totalItem = countTotalItem(tradeItems);
//		session.put("totalItem", totalItem);
//		totalAmount = countTotalAmount(tradeItems);
//		session.put("totalAmount", totalAmount);
		
		
		
		json.setSuccess(true);
		json.setObj(copyItem(tradeItem));  //這裡設定的obj = 返回的 new itemVO;
		super.writeJson(json);
	}
	
	@SuppressWarnings("unchecked")
	public void deleteTradeItem() {

		tradeItems = (Map<Integer, Tradeitem>) session.get("tradeItems");

		if (ids == null || ids.trim().equals(""))
			return;
		for (String productId : ids.split(",")) {
			tradeItems.remove(new Integer(productId.trim()));
		}
////		
//		totalItem = countTotalItem(tradeItems);
//		session.put("totalItem", totalItem);
//		totalAmount = countTotalAmount(tradeItems);
//		session.put("totalAmount", totalAmount);
//		
		Json json = new Json();
		json.setSuccess(true);
		super.writeJson(json);
	}
////
//	private Integer countTotalItem(Map<Integer, Tradeitem> tradeItems) {
//		List<Tradeitem> listTradeitems = new ArrayList<Tradeitem>(tradeItems.values());
//		Integer totalItem = 0;
//		for(Tradeitem t : listTradeitems) {
//			totalItem = totalItem + t.getQuantity();
//		}
//		return totalItem;
//	}
//	private Double countTotalAmount(Map<Integer, Tradeitem> tradeItems) {
//		List<Tradeitem> listTradeitems = new ArrayList<Tradeitem>(tradeItems.values());
//		Double totalAmount = 0.00;
//		for(Tradeitem t : listTradeitems) {
//			totalAmount = totalAmount + t.getTotal();
//		}
//		return NumberUtil.formatDouble(totalAmount);
//	}
//	
}
