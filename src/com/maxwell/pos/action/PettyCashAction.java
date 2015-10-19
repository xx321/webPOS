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
import com.maxwell.pos.model.PettyCash;
import com.maxwell.pos.model.SpendingItem;
import com.maxwell.pos.service.PettyCashManager;
import com.maxwell.pos.service.SpendingItemManager;
import com.maxwell.pos.util.DateUtil;
import com.maxwell.pos.vo.PettyCashVO;
import com.maxwell.pos.vo.SpendingItemVO;
import com.maxwell.pos.vo.base.DataGrid;
import com.maxwell.pos.vo.base.Json;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

@Component("pettyCashAction")
@Scope("prototype")
public class PettyCashAction extends BaseAction implements ModelDriven<PettyCashVO> {

	private static final long serialVersionUID = -1510589787390880014L;
	private Map<String, Object> session = ActionContext.getContext().getSession();
	
	private PettyCashManager pettyCashManager;
	private SpendingItemManager spendingItemManager;
	@Resource
	public void setPettyCashManager(PettyCashManager pettyCashManager) {
		this.pettyCashManager = pettyCashManager;
	}
	@Resource
	public void setSpendingItemManager(SpendingItemManager spendingItemManager) {
		this.spendingItemManager = spendingItemManager;
	}

	private PettyCashVO pettyCashVO = new PettyCashVO();
	
	public PettyCashVO getModel() {
		return pettyCashVO;
	}
	
	private PettyCash pettyCash;
	
	public PettyCash getPettyCash() {
		return pettyCash;
	}

	public void setPettyCash(PettyCash pettyCash) {
		this.pettyCash = pettyCash;
	}

	private String message;
	
	public String getMessage() {
		return message;
	}
	
	public void pettyCashPrepare() {
		session.remove("spendingItems");
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("");
		super.writeJson(j);
	}

	public String add() {
		
		PettyCash pettyCash = pettyCashManager.findBy(session, pettyCashVO);
		
		@SuppressWarnings("unchecked")
		Map<Integer, SpendingItem> spendingItems = (Map<Integer, SpendingItem>) session.get("spendingItems");
		message = pettyCashManager.setSpendingItems(spendingItems, pettyCash);
		
		if (pettyCash.getSpendingItems().size()<=0 || !"儲存成功 !".equals(message)) {
			message = "儲存失敗 !";
			return SUCCESS;
		}
		
		pettyCashManager.add(pettyCash);
		
		session.remove("spendingItems");
		return SUCCESS;
	}
	
	public String load() {
		if (pettyCashVO.getId() == null) {
			return INPUT;
		}
		pettyCash = pettyCashManager.get(pettyCashVO.getId());
		
		String ids = getSubjectIds(pettyCash);
		Map<Integer, SpendingItem> spendingItems = new LinkedHashMap<Integer, SpendingItem>(0);
		
		spendingItemManager.setSpendingItemsBy(ids, spendingItems);
		
		List<SpendingItemVO> spendingItemVOs = getSpendingItemVOs(pettyCash);
		
		for (SpendingItemVO spendingItemVO : spendingItemVOs) {
			spendingItemManager.editSpendingItemBy(spendingItemVO, spendingItems);
		}

		pettyCashVO.setCreateTimeStr(DateUtil.toCharForJsp(pettyCash.getCreateTime()));
		pettyCashVO.setPaymentTimeStr(DateUtil.toCharForJsp(pettyCash.getPaymentTime()));
		
		session.put("spendingItems", spendingItems);
		session.put("tax", pettyCash.getTax());
		return SUCCESS;
	}

	private String getSubjectIds(PettyCash pettyCash) {
		StringBuffer sb = new StringBuffer();
		for (SpendingItem s : pettyCash.getSpendingItems()) {
			sb.append(s.getSubject().getId().toString());
			sb.append(",");
		}
		return sb.toString();
	}
	private List<SpendingItemVO> getSpendingItemVOs(PettyCash pettyCash) {
		List<SpendingItemVO> spendingItemVOs = new ArrayList<SpendingItemVO>();
		
		SpendingItemVO spendingItemVO = null;
		for (SpendingItem spendingItem : pettyCash.getSpendingItems()) {
			spendingItemVO = new SpendingItemVO();
			BeanUtils.copyProperties(spendingItem, spendingItemVO);
			spendingItemVO.setSubjectId(spendingItem.getSubject().getId());
			spendingItemVO.setSubjectName(spendingItem.getSubject().getName());
			
			spendingItemVOs.add(spendingItemVO);
		}
		return spendingItemVOs;
	}
	
	public String update() {
		PettyCash pettyCash = pettyCashManager.findBy(session, pettyCashVO);
		
		if (pettyCashVO.getId() == null)
			return INPUT;
		pettyCash.setId(pettyCashVO.getId());

		@SuppressWarnings("unchecked")
		Map<Integer, SpendingItem> spendingItems = (Map<Integer, SpendingItem>) session.get("spendingItems");
		message = pettyCashManager.setSpendingItems(spendingItems, pettyCash);
		
		if (pettyCash.getSpendingItems().size()<=0 || !"儲存成功 !".equals(message)) {
			message = "儲存失敗 !";
			return SUCCESS;
		}
		
		spendingItemManager.deleteByPettyCashId(pettyCashVO.getId());
		
		pettyCashManager.update(pettyCash);
		
		
		session.remove("spendingItems");
		session.remove("tax");
		return SUCCESS;
	}
	
	public void delete() {
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg(pettyCashManager.delete(pettyCashVO.getIds()));
		super.writeJson(j);
	}
	
	public void updateAudit() {
		if (pettyCashVO.getId() == null)
			return;
		pettyCash = pettyCashManager.get(pettyCashVO.getId());
		
		if (pettyCash.getStatus() == 0) {
			pettyCash.setStatus(new Integer(1));
		} else {
			pettyCash.setStatus(new Integer(0));
		}
		
		pettyCashManager.update(pettyCash);
		
		pettyCashVO = new PettyCashVO();
		BeanUtils.copyProperties(pettyCash, pettyCashVO);
		if (pettyCash.getSupplier() !=null)
			pettyCashVO.setSupplierName(pettyCash.getSupplier().getName());
		pettyCashVO.setCreateTimeStr(DateUtil.toCharForDataGrid(pettyCash.getCreateTime()));
		pettyCashVO.setPaymentTimeStr(DateUtil.toCharForDataGrid(pettyCash.getPaymentTime()));
		
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("審核成功 !");
		j.setObj(pettyCashVO);
		super.writeJson(j);
	}
	public void datagrid() {
		
		DataGrid datagrid = pettyCashManager.getDataGrid(pettyCashVO, getRows(), getPage(), getSort(), getOrder());
		
		super.writeJson(datagrid);
	}
	
}
