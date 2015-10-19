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
import com.maxwell.pos.model.GoodsItem;
import com.maxwell.pos.model.IntoGoods;
import com.maxwell.pos.model.User;
import com.maxwell.pos.service.GoodsItemManager;
import com.maxwell.pos.service.IntoGoodsManager;
import com.maxwell.pos.util.DateUtil;
import com.maxwell.pos.util.NumberUtil;
import com.maxwell.pos.vo.GoodsItemVO;
import com.maxwell.pos.vo.IntoGoodsVO;
import com.maxwell.pos.vo.base.DataGrid;
import com.maxwell.pos.vo.base.Json;
import com.maxwell.pos.vo.footer.AccountsFooter;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

@Component("intoGoodsAction")
@Scope("prototype")
public class IntoGoodsAction extends BaseAction implements ModelDriven<IntoGoodsVO> {

	private static final long serialVersionUID = -5375325397679762385L;
	private Map<String, Object> session = ActionContext.getContext().getSession();
	
	private IntoGoodsManager intoGoodsManager;
	private GoodsItemManager goodsItemManager;

	@Resource
	public void setIntoGoodsManager(IntoGoodsManager intoGoodsManager) {
		this.intoGoodsManager = intoGoodsManager;
	}
	@Resource
	public void setGoodsItemManager(GoodsItemManager goodsItemManager) {
		this.goodsItemManager = goodsItemManager;
	}

	private IntoGoodsVO intoGoodsVO = new IntoGoodsVO();
	
	public IntoGoodsVO getModel() {
		return intoGoodsVO;
	}

	private IntoGoods intoGoods;
	
	public IntoGoods getIntoGoods() {
		return intoGoods;
	}

	public void setIntoGoods(IntoGoods intoGoods) {
		this.intoGoods = intoGoods;
	}

	private String message;
	
	private String chineseAmount;
	
	public String getMessage() {
		return message;
	}
	
	private List<IntoGoods> listIntoGoods;
	
	public List<IntoGoods> getListIntoGoods() {
		return listIntoGoods;
	}
	public void setListIntoGoods(List<IntoGoods> listIntoGoods) {
		this.listIntoGoods = listIntoGoods;
	}
	
	public List<IntoGoodsVO> listIntoGoodsVOs;

	public List<IntoGoodsVO> getListIntoGoodsVOs() {
		return listIntoGoodsVOs;
	}
	public void setListIntoGoodsVOs(List<IntoGoodsVO> listIntoGoodsVOs) {
		this.listIntoGoodsVOs = listIntoGoodsVOs;
	}
	
	public void intoGoodsPrepare() {
		session.remove("goodsItems");
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("");
		super.writeJson(j);
	}
	
	public String add() {	
		if (intoGoodsVO.getSupplierId() == null)
			return INPUT;
		
		IntoGoods intoGoods = intoGoodsManager.findBy(session, intoGoodsVO);
		
		@SuppressWarnings("unchecked")
		Map<Integer, GoodsItem> goodsItems = (Map<Integer, GoodsItem>) session.get("goodsItems");
		message = intoGoodsManager.purchase(goodsItems, intoGoods);
		
		if (intoGoods.getGoodsItems().size()<=0 || !"".equals(message))
			return SUCCESS;
		intoGoodsManager.add(intoGoods);
		
		//添加完畢之後，移除session 裡的進貨項目。
		session.remove("goodsItems");
		return SUCCESS;
	}
	
	public String load() {
		if (intoGoodsVO.getId() == null) {
			return INPUT;
		}
		intoGoods = intoGoodsManager.get(intoGoodsVO.getId());
		
		String ids = getProductIds(intoGoods);
		Map<Integer, GoodsItem> goodsItems = new LinkedHashMap<Integer, GoodsItem>(0);
		
		goodsItemManager.setGoodsItemsBy(ids,goodsItems);
		
		List<GoodsItemVO> goodsItemVOs = getGoodsItemVOs(intoGoods);
		
		for (GoodsItemVO goodsItemVO : goodsItemVOs) {
			
			goodsItemManager.editGoodsItemsBy(goodsItemVO, goodsItems);
			
		}

		intoGoodsVO.setCreateTimeStr(DateUtil.toCharForJsp(intoGoods.getCreateTime()));
		
		session.put("goodsItems", goodsItems);
		session.put("tax", intoGoods.getTax());
		session.put("closed", intoGoods.getClosed());
		return SUCCESS;
	}
	
	private String getProductIds(IntoGoods intoGoods) {
		StringBuffer sb = new StringBuffer();
		for (GoodsItem g : intoGoods.getGoodsItems()) {
			sb.append(g.getProduct().getId().toString());
			sb.append(",");
		}
		return sb.toString();
	}
	
	private List<GoodsItemVO> getGoodsItemVOs(IntoGoods intoGoods) {
		List<GoodsItemVO> goodsItemVOs = new ArrayList<GoodsItemVO>();
		
		GoodsItemVO goodsItemVO = null;
		for (GoodsItem goodItem : intoGoods.getGoodsItems()) {
			goodsItemVO = new GoodsItemVO();
			BeanUtils.copyProperties(goodItem, goodsItemVO);
			goodsItemVO.setProductId(goodItem.getProduct().getId());
			goodsItemVO.setProductName(goodItem.getProduct().getName());
			goodsItemVO.setUnit(goodItem.getProduct().getUnit());
			
			goodsItemVOs.add(goodsItemVO);
		}
		return goodsItemVOs;
	}
	
	public String update() {
		
		IntoGoods intoGoods = intoGoodsManager.findBy(session, intoGoodsVO);
		
		if (intoGoodsVO.getId() == null)
			return INPUT;
		
		//id!=null 表示為更新狀態，需要先刪除之前採購單裡的商品，(存新的進去)
		intoGoods.setId(intoGoodsVO.getId());

		
		Integer closed = (Integer) session.get("closed");
		if (closed ==1)
			intoGoodsManager.updatePurchase(intoGoods);
		
		@SuppressWarnings("unchecked")
		Map<Integer, GoodsItem> goodsItems = (Map<Integer, GoodsItem>) session.get("goodsItems");
		message = intoGoodsManager.purchase(goodsItems, intoGoods);
		
		
		if (intoGoods.getGoodsItems().size()<=0 || !"".equals(message)) {
			if (closed ==1)
				intoGoodsManager.deletePurchase(intoGoods);
			return SUCCESS;
		}
		goodsItemManager.deleteByIntoGoodsId(intoGoodsVO.getId());	
		
		
		intoGoodsManager.update(intoGoods);
		
		//更新完畢之後，移除session 裡的進貨項目。
		session.remove("goodsItems");
		session.remove("tax");
		session.remove("closed");
		return SUCCESS;
	}
	
	public void delete() {
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg(intoGoodsManager.delete(intoGoodsVO.getIds()));
		super.writeJson(j);
	}

	public void updateAudit() {
		if (intoGoodsVO.getId() == null) {
			return;
		}
		
		intoGoods = intoGoodsManager.get(intoGoodsVO.getId());
		if (intoGoods.getStatus() == 0) {
			intoGoods.setStatus(new Integer(1));
			intoGoods.setPaymentPersonnel((User) session.get("user"));
			intoGoods.setPaymentTime(new java.util.Date());
		} else {
			intoGoods.setStatus(new Integer(0));
			intoGoods.setPaymentPersonnel(null);
			intoGoods.setPaymentTime(null);
		}
		intoGoodsManager.update(intoGoods);
		
		intoGoodsVO = new IntoGoodsVO();	
		intoGoodsManager.copyProperties(intoGoods, intoGoodsVO);
		
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("審核成功 !");
		j.setObj(intoGoodsVO);
		super.writeJson(j);
	}
	
	public void updateMoreAudit() {
		if (intoGoodsVO.getIds() == null) {
			return;
		}
		
		listIntoGoodsVOs = new ArrayList<IntoGoodsVO>();
		
		for (String id : intoGoodsVO.getIds().split(",")) {
			intoGoods = intoGoodsManager.get(new Integer(id.trim()));
			if (intoGoods.getStatus() == 0) {
				intoGoods.setStatus(new Integer(1));
				intoGoods.setPaymentPersonnel((User) session.get("user"));
				intoGoods.setPaymentTime(new java.util.Date());
			} else {
				intoGoods.setStatus(new Integer(0));
				intoGoods.setPaymentPersonnel(null);
				intoGoods.setPaymentTime(null);
			}
			intoGoodsManager.update(intoGoods);
			
			intoGoodsVO = new IntoGoodsVO();
			intoGoodsManager.copyProperties(intoGoods, intoGoodsVO);
			
			listIntoGoodsVOs.add(intoGoodsVO);
		}
		
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("批量審核成功 !");
		j.setObj(listIntoGoodsVOs);
		super.writeJson(j);	
	}
	
	public void datagrid() {
		
		intoGoodsVO.setClosed(new Integer(1)); 
		intoGoodsVO.setStatus(new Integer(0));
		DataGrid datagrid = intoGoodsManager.getDataGrid(intoGoodsVO, getRows(), getPage(), getSort(), getOrder());
		
		super.writeJson(datagrid);
	}
	
	public void Purchasedatagrid() {
		
		intoGoodsVO.setClosed(new Integer(0));
		DataGrid datagrid = intoGoodsManager.getDataGrid(intoGoodsVO, getRows(), getPage(), getSort(), getOrder());
		
		super.writeJson(datagrid);
	}
	
	public String getChineseAmount() {
		return chineseAmount;
	}
	public void setChineseAmount(String chineseAmount) {
		this.chineseAmount = chineseAmount;
	}
	
	public String print() {
		if (intoGoodsVO.getId() == null)
			return INPUT;
		
		intoGoods = intoGoodsManager.get(intoGoodsVO.getId());
		
		if (intoGoods == null)
			return INPUT;
		
		if (intoGoods.getTotalAmount() != null)
			chineseAmount = NumberUtil.digitUppercase(intoGoods.getTotalAmount());
		
		return SUCCESS;		
	}
	
	public String printMore() {
		if (intoGoodsVO.getIds() == null || "".equals(intoGoodsVO.getIds().trim()))
			return INPUT;
		
		listIntoGoods = intoGoodsManager.getListByIds(intoGoodsVO.getIds());
		
		if (listIntoGoods == null || listIntoGoods.size() == 0)
			return INPUT;
		
		return SUCCESS;
	}
	
	public void getDatagridByIntoGoodsIds() {
		//表格一進入時，希望表格的資料是空的。(設定ids = "0"，分割之後只找 id in (0) 的資料，所以為空)
		if (intoGoodsVO.getIds() == null || "".equals(intoGoodsVO.getIds().trim()))
			intoGoodsVO.setIds("0");
		DataGrid datagrid = intoGoodsManager.getDataGrid(intoGoodsVO, getRows(), getPage(), getSort(), getOrder());
		
		//添加 footer
		@SuppressWarnings("unchecked")
		Double totalAmount = countTotalAmount((List<IntoGoodsVO>) datagrid.getRows());
		List<AccountsFooter> footer = new ArrayList<AccountsFooter>();
		AccountsFooter accountsFooter = new AccountsFooter();
		accountsFooter.setCreateTimeStr("付款金額");
		accountsFooter.setTotalAmount(Math.rint(totalAmount));
		footer.add(accountsFooter);
		
		datagrid.setFooter(footer);
		
		super.writeJson(datagrid);
	}
	
	private Double countTotalAmount(List<IntoGoodsVO> rows) {
		Double totalAmount = 0.00;
		for(IntoGoodsVO i : rows) {
			totalAmount = totalAmount + i.getTotalAmount();
		}
		return totalAmount;
	}
}
