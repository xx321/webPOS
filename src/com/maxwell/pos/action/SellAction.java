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
import com.maxwell.pos.model.Sell;
import com.maxwell.pos.model.Sellitem;
import com.maxwell.pos.model.User;
import com.maxwell.pos.service.SellManager;
import com.maxwell.pos.service.SellitemManager;
import com.maxwell.pos.util.Arith;
import com.maxwell.pos.util.DateUtil;
import com.maxwell.pos.util.NumberUtil;
import com.maxwell.pos.vo.SellVO;
import com.maxwell.pos.vo.SellitemVO;
import com.maxwell.pos.vo.base.DataGrid;
import com.maxwell.pos.vo.base.Json;
import com.maxwell.pos.vo.footer.AccountsFooter;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

@Component("sellAction")
@Scope("prototype")
public class SellAction extends BaseAction implements ModelDriven<SellVO> {

	private static final long serialVersionUID = -506250658889926346L;
	private Map<String, Object> session = ActionContext.getContext().getSession();
	
	private SellManager sellManager;
	private SellitemManager sellitemManager;

	@Resource
	public void setSellManager(SellManager sellManager) {
		this.sellManager = sellManager;
	}
	@Resource
	public void setSellitemManager(SellitemManager sellitemManager) {
		this.sellitemManager = sellitemManager;
	}

	private SellVO sellVO = new SellVO();
	
	public SellVO getModel() {
		return sellVO;
	}
	
	private Sell sell;	
	
	public Sell getSell() {
		return sell;
	}

	public void setSell(Sell sell) {
		this.sell = sell;
	}
	
	private String chineseAmount;
	
	public String getChineseAmount() {
		return chineseAmount;
	}

	private String message;
	
	public String getMessage() {
		return message;
	}
	
	private List<SellVO> listSellVOs;

	public List<SellVO> getListSellVOs() {
		return listSellVOs;
	}
	public void setListSellVOs(List<SellVO> listSellVOs) {
		this.listSellVOs = listSellVOs;
	}
	
	public void sellPrepare() {
		session.remove("sellitems");
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("");
		super.writeJson(j);
	}
	
	public String add() {
		if (sellVO.getCustomerId() == null)
			return INPUT;
		
		Sell sell = sellManager.findBy(session, sellVO);
		
		
		//銷售商品 (裡面判斷  庫存數量 是否足夠 && 連動調整庫存數量)。
		@SuppressWarnings("unchecked")
		Map<Integer, Sellitem> sellitems = (Map<Integer, Sellitem>) session.get("sellitems");
		message = sellManager.sales(sellitems, sell);  
		
		if (sell.getSellitems().size()<=0 || !"".equals(message)) //判斷沒有商品 或 庫存數量不足 ，直接返回。
			return SUCCESS;
		
		
		sellManager.add(sell);
		
		session.remove("sellitems");
		return SUCCESS;
	}
	
	public String load() {
		if (sellVO.getId() == null) {
			return INPUT;
		}
		sell = sellManager.get(sellVO.getId());
		
		String ids = getProductIds(sell);
		Map<Integer, Sellitem> sellitems = new LinkedHashMap<Integer, Sellitem>(0);
		
		sellitemManager.setSellitemsBy(ids, sellitems);
		
		List<SellitemVO> sellitemVOs = getSellitemVOs(sell);
		
		for (SellitemVO sellitemVO : sellitemVOs) {
			sellitemManager.editSellitemsBy(sellitemVO, sellitems);
		}
		
		sellVO.setCreateTimeStr(DateUtil.toCharForJsp(sell.getCreateTime()));
		
		session.put("sellitems", sellitems);
		session.put("tax", sell.getTax());        //sellitemAction!sellitemDataGrid
		session.put("closed", sell.getClosed());  //sellAction!update	
		return SUCCESS;
	}
	
	private String getProductIds(Sell sell) {
		StringBuffer sb = new StringBuffer();
		for (Sellitem s : sell.getSellitems()) {
			sb.append(s.getProduct().getId().toString());
			sb.append(",");
		}
		return sb.toString();
	}
	private List<SellitemVO> getSellitemVOs(Sell sell) {
		List<SellitemVO> sellitemVOs = new ArrayList<SellitemVO>();
		
		SellitemVO sellitemVO = null;
		for (Sellitem sellitem : sell.getSellitems()) {
			sellitemVO = new SellitemVO();
			BeanUtils.copyProperties(sellitem, sellitemVO);
			sellitemVO.setProductId(sellitem.getProduct().getId());
			sellitemVO.setProductName(sellitem.getProduct().getName());
			sellitemVO.setUnit(sellitem.getProduct().getUnit());
			
			sellitemVOs.add(sellitemVO);
		}
		return sellitemVOs;
	}
	
	public String update() {
		
		Sell sell = sellManager.findBy(session, sellVO);
		
		if (sellVO.getId() == null) 
			return INPUT;
		
		sell.setId(sellVO.getId());

		
		//更新銷售單之前 必需還原銷售的商品數量。(加回去)
		Integer closed = (Integer) session.get("closed");
		if (closed ==1) //closed ==1 表示更新之前 就是銷售單。
			sellManager.updateSales(sell);  
		
		//銷售商品 (裡面判斷  庫存數量 是否足夠 && 連動調整庫存數量)。
		@SuppressWarnings("unchecked")
		Map<Integer, Sellitem> sellitems = (Map<Integer, Sellitem>) session.get("sellitems");
		message = sellManager.sales(sellitems, sell);
		
		if (sell.getSellitems().size()<=0 || !"".equals(message)) { //判斷沒有商品 或 庫存數量不足 ，直接返回。
			if (closed == 1)
				sellManager.deleteSales(sell);
			return SUCCESS;
		}	
		
		//刪掉舊的銷售商品		
		sellitemManager.deleteBySellId(sellVO.getId());
			
		sellManager.update(sell);
		
		session.remove("sellitems");
		session.remove("tax");
		session.remove("closed");
		return SUCCESS;
	}
	
	public void delete() {
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg(sellManager.delete(sellVO.getIds()));
		super.writeJson(j);
	}

	public void updateAudit() { //審核功能 : 更改 收款狀態。
		
		if (sellVO.getId() == null) {
			return;
		}
		sell = sellManager.get(sellVO.getId());
		
		if (sell.getStatus() == 0) {
			sell.setStatus(new Integer(1));
			sell.setIncomePersonnel((User) session.get("user"));
			sell.setIncomeTime(new java.util.Date());
		} else {
			sell.setStatus(new Integer(0));
			sell.setIncomePersonnel(null);
			sell.setIncomeTime(null);
		}
		
		sellManager.update(sell);
		
		sellVO = new SellVO();
		BeanUtils.copyProperties(sell, sellVO);
		sellVO.setUserName(sell.getUser().getUsername());
		sellVO.setCustomerName(sell.getCustomer().getName());
		if (sell.getIncomePersonnel() != null)
			sellVO.setIncomePersonnelName(sell.getIncomePersonnel().getUsername());
		sellVO.setCreateTimeStr(DateUtil.toCharForDataGrid(sell.getCreateTime()));
		sellVO.setIncomeTimeStr(DateUtil.toCharForDataGrid(sell.getIncomeTime()));
		
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("審核成功 !");
		j.setObj(sellVO);
		super.writeJson(j);
	}
	
	public void updateMoreAudit() {
		if (sellVO.getIds() == null) {
			return;
		}
		
		listSellVOs = new ArrayList<SellVO>();
		
		for (String id : sellVO.getIds().split(",")) {
			sell = sellManager.get(new Integer(id.trim()));
			
			if (sell.getStatus() == 0) {
				sell.setStatus(new Integer(1));
				sell.setIncomePersonnel((User) session.get("user"));
				sell.setIncomeTime(new java.util.Date());
			} else {
				sell.setStatus(new Integer(0));
				sell.setIncomePersonnel(null);
				sell.setIncomeTime(null);
			}
			
			sellManager.update(sell);
			
			sellVO = new SellVO();
			BeanUtils.copyProperties(sell, sellVO);
			sellVO.setUserName(sell.getUser().getUsername());
			sellVO.setCustomerName(sell.getCustomer().getName());
			if (sell.getIncomePersonnel() != null)
				sellVO.setIncomePersonnelName(sell.getIncomePersonnel().getUsername());
			sellVO.setCreateTimeStr(DateUtil.toCharForDataGrid(sell.getCreateTime()));
			sellVO.setIncomeTimeStr(DateUtil.toCharForDataGrid(sell.getIncomeTime()));
			
			listSellVOs.add(sellVO);
		}
		
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("批量審核成功 !");
		j.setObj(listSellVOs);
		super.writeJson(j);
	}
	
	public void datagrid() {
		
		sellVO.setClosed(new Integer(1));
		DataGrid datagrid = sellManager.getDataGrid(sellVO, getRows(), getPage(), getSort(), getOrder());
		
		super.writeJson(datagrid);
	}
	
	public void Quotationdatagrid() {
		
		sellVO.setClosed(new Integer(0));
		DataGrid datagrid = sellManager.getDataGrid(sellVO, getRows(), getPage(), getSort(), getOrder());
		
		super.writeJson(datagrid);
	}
	
	public String print() {
		if (sellVO.getId() == null)
			return INPUT;
		
		sell = sellManager.get(sellVO.getId());	
		
		if (sell == null)
			return INPUT;

		if (sell.getTotalAmount() != null)
			chineseAmount = NumberUtil.digitUppercase(sell.getTotalAmount());
		
		return SUCCESS;
	}
	
	public void getDatagridBySellIds() {
		//表格一進入時，希望表格的資料是空的。(設定ids = "0"，分割之後只找 id in (0) 的資料，所以為空)
		if (sellVO.getIds() == null || "".equals(sellVO.getIds().trim()))
			sellVO.setIds("0");
		DataGrid datagrid = sellManager.getDataGrid(sellVO, getRows(), getPage(), getSort(), getOrder());
		
		//添加 footer
		@SuppressWarnings("unchecked")
		Double totalAmount = contTotalAmount((List<SellVO>) datagrid.getRows());
		List<AccountsFooter> footer = new ArrayList<AccountsFooter>();
		AccountsFooter accountsFooter = new AccountsFooter();
		accountsFooter.setCreateTimeStr("收款金額");
		accountsFooter.setTotalAmount(Arith.round(totalAmount, 0));
		footer.add(accountsFooter);
		
		datagrid.setFooter(footer);
		
		super.writeJson(datagrid);
	}
	
	private Double contTotalAmount(List<SellVO> rows) {
		Double totalAmount = 0.00;
		for(SellVO s : rows) {
			totalAmount = totalAmount + s.getTotalAmount();
		}
		return totalAmount;
	}
}
