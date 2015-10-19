package com.maxwell.pos.action;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.maxwell.pos.action.base.BaseAction;
import com.maxwell.pos.model.Sellitem;
import com.maxwell.pos.service.SellitemManager;
import com.maxwell.pos.vo.SellitemVO;
import com.maxwell.pos.vo.base.DataGrid;
import com.maxwell.pos.vo.base.Json;
import com.maxwell.pos.vo.footer.ItemFooter;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

@Component("sellitemAction")
@Scope("prototype")
public class SellitemAction extends BaseAction implements ModelDriven<SellitemVO> {

	private static final long serialVersionUID = -7580273584750475085L;
	private Map<String, Object> session = ActionContext.getContext().getSession();
	
	private SellitemManager sellitemManager;
	
	@Resource
	public void setSellitemManager(SellitemManager sellitemManager) {
		this.sellitemManager = sellitemManager;
	}
	
	private Map<Integer, Sellitem> sellitems;
	
	private SellitemVO sellitemVO = new SellitemVO();
	
	public SellitemVO getModel() {		
		return sellitemVO;
	}

	public void add() {
		sellitems = getSessionSellitems();
		
		sellitemManager.setSellitemsBy(sellitemVO.getIds(),sellitems);
	}

	public void edit() {
		sellitems = getSessionSellitems();
		
		sellitemManager.editSellitemsBy(sellitemVO,sellitems);
		
		Json json = new Json();
		json.setSuccess(true);
		super.writeJson(json);
	}
	
	public void sellitemDataGrid() {
		sellitems = getSessionSellitems();
		//這裡需要從 session 裡面，取得 營業稅的狀態 (未稅 或 含稅)
		// Double tax = session.get("tax");  ( 0 或 0.05 )
		//之後 去計算 footer 裡的 營業稅 和 總計。
		Double tax = (Double) session.get("tax");
		if (tax == null)
			tax = new Double(0.00);
		List<SellitemVO> sellitemVOs = new ArrayList<SellitemVO>();
		
		for (Sellitem item : sellitems.values()) {
			SellitemVO itemVO = new SellitemVO();
			itemVO.setProductId(item.getProduct().getId());
			itemVO.setProductName(item.getProduct().getName());
			itemVO.setQuantity(item.getQuantity());
			itemVO.setPurchasePrice(item.getPurchasePrice());
			itemVO.setTotal(Math.rint(item.getTotal()));
			
			itemVO.setUnit(item.getProduct().getUnit());
			
			sellitemVOs.add(itemVO);
		}
		
		Double totalAmount = countTotalAmount(sellitems);
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
		datagrid.setTotal((long) sellitemVOs.size());
		datagrid.setRows(sellitemVOs);
		datagrid.setFooter(footer);
		
		super.writeJson(datagrid);		
	}

	public void deleteSellitem() {
		sellitems = getSessionSellitems();
		sellitemManager.deleteSellitemBy(sellitemVO.getIds(),sellitems);
		
		Json json = new Json();
		json.setSuccess(true);
		super.writeJson(json);
	}
	
	public void getDatagridBySellId() {
		DataGrid datagrid = new DataGrid();
		if (sellitemVO.getSellId() == null) {
			datagrid.setRows(new ArrayList<Object>());
			super.writeJson(datagrid);
		}
		
		datagrid = sellitemManager.getDataGrid(sellitemVO, getRows(), getPage(), getSort(), getOrder());
		
		@SuppressWarnings("unchecked")
		List<SellitemVO> sellitemVOs = (List<SellitemVO>) datagrid.getRows();
		//添加 footer
		Double total = countTotal(sellitemVOs);
		Double totalAmount = countTotalAmount(sellitemVOs);
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

	private Double countTotal(List<SellitemVO> sellitemVOs) {
		Double total = 0.00;
		for(SellitemVO s : sellitemVOs) {
			total = total + s.getTotal();
		}
		return total;
	}

	private Double countTotalAmount(List<SellitemVO> sellitemVOs) {
		Double totalAmount = 0.00;
		for(SellitemVO s : sellitemVOs) {
			totalAmount = totalAmount + s.getTotalAmount();
		}
		return totalAmount;
	}

	@SuppressWarnings("unchecked")
	private Map<Integer, Sellitem> getSessionSellitems() {
		sellitems = (Map<Integer, Sellitem>) session.get("sellitems");
		if (sellitems == null) {
			sellitems = new LinkedHashMap<Integer, Sellitem>(0);
			session.put("sellitems", sellitems);
		}
		return sellitems;
	}

	
	private Double countTotalAmount(Map<Integer, Sellitem> sellitems) {
		List<Sellitem> listSellitems = new ArrayList<Sellitem>(sellitems.values());
		Double totalAmount = 0.00;
		for(Sellitem s : listSellitems) {
			totalAmount = totalAmount + s.getTotal();
		}
		return totalAmount;
	}
	
}
