package com.maxwell.pos.action;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.maxwell.pos.action.base.BaseAction;
import com.maxwell.pos.model.GoodsItem;
//import com.maxwell.pos.model.IntoGoods;
//import com.maxwell.pos.model.Product;
//import com.maxwell.pos.model.Tradeitem;
//import com.maxwell.pos.model.User;
import com.maxwell.pos.service.GoodsItemManager;
//import com.maxwell.pos.service.IntoGoodsManager;
//import com.maxwell.pos.service.ProductManager;
import com.maxwell.pos.vo.GoodsItemVO;
import com.maxwell.pos.vo.base.DataGrid;
import com.maxwell.pos.vo.base.Json;
import com.maxwell.pos.vo.footer.ItemFooter;
//import com.maxwell.pos.util.NumberUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

@Component("goodsItemAction")
@Scope("prototype")
public class GoodsItemAction extends BaseAction implements ModelDriven<GoodsItemVO> {

	private static final long serialVersionUID = 3498966589670542955L;
	private Map<String, Object> session = ActionContext.getContext().getSession();

	private GoodsItemManager goodsItemManager;
	
	private Map<Integer, GoodsItem> goodsItems;

	@Resource
	public void setGoodsItemManager(GoodsItemManager goodsItemManager) {
		this.goodsItemManager = goodsItemManager;
	}
	
	private GoodsItemVO goodsItemVO = new GoodsItemVO();
	
	public GoodsItemVO getModel() {
		return goodsItemVO;
	}
	
	public void add() {
		goodsItems = getSessionGoodsItems();

		goodsItemManager.setGoodsItemsBy(goodsItemVO.getIds(),goodsItems);
	}
	
	public void edit() {
		goodsItems = getSessionGoodsItems();
		
		goodsItemManager.editGoodsItemsBy(goodsItemVO,goodsItems);
		
		Json json = new Json();
		json.setSuccess(true);
		super.writeJson(json);
	}
	
	public void goodsItemDataGrid() {
		goodsItems = getSessionGoodsItems();
		
		Double tax = (Double) session.get("tax");
		if (tax == null)
			tax = new Double(0.00);
		List<GoodsItemVO> goodsItemVOs = new ArrayList<GoodsItemVO>();
		
		for (GoodsItem item : goodsItems.values()) {
			GoodsItemVO itemVO = new GoodsItemVO();
			itemVO.setProductId(item.getProduct().getId());
			itemVO.setProductName(item.getProduct().getName());
			itemVO.setQuantity(item.getQuantity());
			itemVO.setPurchasePrice(item.getPurchasePrice());
			itemVO.setTotal(Math.rint(item.getTotal()));
			
			itemVO.setUnit(item.getProduct().getUnit());
			
			goodsItemVOs.add(itemVO);
		}
		
		//添加 footer 的範例
		Double totalAmount = countTotalAmount(goodsItems);
		List<ItemFooter> footer = new ArrayList<ItemFooter>();
		ItemFooter itemFooter = new ItemFooter();
		itemFooter.setPurchasePrice("合計");
		itemFooter.setTotal(Math.rint(totalAmount));
		footer.add(itemFooter);
		itemFooter = new ItemFooter();
		itemFooter.setPurchasePrice("營業稅");
		itemFooter.setTotal(Math.rint(totalAmount * tax));
		footer.add(itemFooter);
		itemFooter = new ItemFooter();
		itemFooter.setPurchasePrice("總計");
		itemFooter.setTotal(Math.rint(totalAmount * (1+tax)));
		footer.add(itemFooter);
		
		DataGrid datagrid = new DataGrid();
		datagrid.setTotal((long) goodsItemVOs.size());
		datagrid.setRows(goodsItemVOs);
		datagrid.setFooter(footer);
		
		super.writeJson(datagrid);
	}

	public void deleteGoodsItem() {
		goodsItems = getSessionGoodsItems();	
		goodsItemManager.deleteGoodsItemBy(goodsItemVO.getIds(),goodsItems);
		
		Json json = new Json();
		json.setSuccess(true);
		super.writeJson(json);
	}
	
	public void getDatagridByIntoGoodsId() {
		DataGrid datagrid = new DataGrid();
		if (goodsItemVO.getIntoGoodsId() == null) {
			datagrid.setRows(new ArrayList<Object>());
			super.writeJson(datagrid);
		}

		datagrid = goodsItemManager.getDataGrid(goodsItemVO, getRows(), getPage(), getSort(), getOrder());
		

		@SuppressWarnings("unchecked")
		List<GoodsItemVO> goodsItemVOs = (List<GoodsItemVO>) datagrid.getRows();
		//添加 footer
		Double total = countTotal(goodsItemVOs);
		Double totalAmount = countTotalAmount(goodsItemVOs);
		Double salesTax = totalAmount - total;
		
		List<ItemFooter> footer = new ArrayList<ItemFooter>();
		ItemFooter itemFooter = new ItemFooter();
		itemFooter.setPurchasePrice("合計");
		itemFooter.setTotal(Math.rint(total));
		footer.add(itemFooter);
		itemFooter = new ItemFooter();
		itemFooter.setPurchasePrice("營業稅");
		itemFooter.setTotal(Math.rint(salesTax));
		footer.add(itemFooter);
		itemFooter = new ItemFooter();
		itemFooter.setPurchasePrice("總計");
		itemFooter.setTotal(Math.rint(totalAmount));
		footer.add(itemFooter);
		
		datagrid.setFooter(footer);
		
		super.writeJson(datagrid);
	}
	
	private Double countTotal(List<GoodsItemVO> goodsItemVOs) {
		Double total = 0.00;
		for(GoodsItemVO g : goodsItemVOs) {
			total = total + g.getTotal();
		}
		return total;
	}

	private Double countTotalAmount(List<GoodsItemVO> goodsItemVOs) {
		Double totalAmount = 0.00;
		for(GoodsItemVO g : goodsItemVOs) {
			totalAmount = totalAmount + g.getTotalAmount();
		}
		return totalAmount;
	}

	@SuppressWarnings("unchecked")
	private Map<Integer, GoodsItem> getSessionGoodsItems() {
		goodsItems = (Map<Integer, GoodsItem>) session.get("goodsItems");
		if (goodsItems == null) {
			goodsItems = new LinkedHashMap<Integer, GoodsItem>(0);
			session.put("goodsItems", goodsItems);
		}
		return goodsItems;
	}

	private Double countTotalAmount(Map<Integer, GoodsItem> goodsItems) {
		List<GoodsItem> listGoodsItems = new ArrayList<GoodsItem>(goodsItems.values());
		Double totalAmount = 0.00;
		for(GoodsItem g : listGoodsItems) {
			totalAmount = totalAmount + g.getTotal();
		}
		return totalAmount;
	}
	
	
}
