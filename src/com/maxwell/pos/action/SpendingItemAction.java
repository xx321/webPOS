package com.maxwell.pos.action;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.maxwell.pos.action.base.BaseAction;
import com.maxwell.pos.model.SpendingItem;
import com.maxwell.pos.service.SpendingItemManager;
import com.maxwell.pos.util.NumberUtil;
import com.maxwell.pos.vo.SpendingItemVO;
import com.maxwell.pos.vo.base.DataGrid;
import com.maxwell.pos.vo.base.Json;
import com.maxwell.pos.vo.footer.SpendingItemFooter;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

@Component("spendingItemAction")
@Scope("prototype")
public class SpendingItemAction extends BaseAction implements ModelDriven<SpendingItemVO>{

	private static final long serialVersionUID = -3501240291032215157L;
	private Map<String, Object> session = ActionContext.getContext().getSession();
	
	private SpendingItemManager spendingItemManager;
	@Resource
	public void setSpendingItemManager(SpendingItemManager spendingItemManager) {
		this.spendingItemManager = spendingItemManager;
	}
	
	private Map<Integer, SpendingItem> spendingItems;

	private SpendingItemVO spendingItemVO = new SpendingItemVO();
	
	public SpendingItemVO getModel() {
		return spendingItemVO;
	}
	
	public void add() {
		spendingItems = getSessionSpendingItems();
		
		spendingItemManager.setSpendingItemsBy(spendingItemVO.getIds(), spendingItems);
	}

	public void edit() {
		spendingItems = getSessionSpendingItems();
		
		spendingItemManager.editSpendingItemBy(spendingItemVO, spendingItems);
		
		Json json = new Json();
		json.setSuccess(true);
		super.writeJson(json);
	}
	
	public void spendingItemDataGrid() {
		spendingItems = getSessionSpendingItems();
		
		Double tax = (Double) session.get("tax");
		if (tax == null)
			tax = new Double(0.00);
		List<SpendingItemVO> spendingItemVOs = new ArrayList<SpendingItemVO>();
		
		for (SpendingItem item : spendingItems.values()) {
			SpendingItemVO itemVO = new SpendingItemVO();
			itemVO.setSubjectId(item.getSubject().getId());
			itemVO.setSubjectName(item.getSubject().getName());
			itemVO.setTotal(item.getTotal());
			
			spendingItemVOs.add(itemVO);
		}
		
		Double totalAmount = countTotalAmount(spendingItems);
		List<SpendingItemFooter> footer = new ArrayList<SpendingItemFooter>();
		SpendingItemFooter itemFooter = new SpendingItemFooter();
		itemFooter.setSubjectName("合計");
		itemFooter.setTotal(totalAmount);
		footer.add(itemFooter);
		itemFooter = new SpendingItemFooter();
		itemFooter.setSubjectName("營業稅");
		itemFooter.setTotal(NumberUtil.formatDouble(totalAmount * tax));
		footer.add(itemFooter);
		itemFooter = new SpendingItemFooter();
		itemFooter.setSubjectName("總計");
		itemFooter.setTotal(Math.rint(totalAmount * (1+tax)));
		footer.add(itemFooter);
		
		DataGrid datagrid = new DataGrid();
		datagrid.setTotal((long) spendingItemVOs.size());
		datagrid.setRows(spendingItemVOs);
		datagrid.setFooter(footer);
		
		super.writeJson(datagrid);
	}
	
	public void deleteSpendingItem() {
		spendingItems = getSessionSpendingItems();
		spendingItemManager.deleteSpendingItemBy(spendingItemVO.getIds(), spendingItems);
		
		Json json = new Json();
		json.setSuccess(true);
		super.writeJson(json);
	}

	public void getDatagridByPettyCashId() {
		DataGrid datagrid = new DataGrid();
		if (spendingItemVO.getPettyCashId() == null) {
			datagrid.setRows(new ArrayList<Object>());
			super.writeJson(datagrid);
		}
		
		datagrid = spendingItemManager.getDataGrid(spendingItemVO, getRows(), getPage(), getSort(), getOrder());
		super.writeJson(datagrid);
	}
	@SuppressWarnings("unchecked")
	private Map<Integer, SpendingItem> getSessionSpendingItems() {
		spendingItems = (Map<Integer, SpendingItem>) session.get("spendingItems");
		if (spendingItems == null) {
			spendingItems = new LinkedHashMap<Integer, SpendingItem>(0);
			session.put("spendingItems", spendingItems);
		}
		return spendingItems;
	}
	
	private Double countTotalAmount(Map<Integer, SpendingItem> spendingItems) {
		List<SpendingItem> listSpendingItems = new ArrayList<SpendingItem>(spendingItems.values());
		Double totalAmount = 0.00;
		for (SpendingItem s : listSpendingItems) {
			totalAmount = totalAmount + s.getTotal();
		}
		return NumberUtil.formatDouble(totalAmount);
	}
}
