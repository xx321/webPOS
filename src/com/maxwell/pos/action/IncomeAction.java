package com.maxwell.pos.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.maxwell.pos.action.base.BaseAction;
import com.maxwell.pos.model.Income;
import com.maxwell.pos.model.Sell;
import com.maxwell.pos.service.IncomeManager;
import com.maxwell.pos.service.SellManager;
import com.maxwell.pos.vo.IncomeVO;
import com.maxwell.pos.vo.SellVO;
import com.maxwell.pos.vo.base.Json;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

@Component("incomeAction")
@Scope("prototype")
public class IncomeAction extends BaseAction implements ModelDriven<IncomeVO> {

	private static final long serialVersionUID = 6951143486681501259L;
	private Map<String, Object> session = ActionContext.getContext().getSession();
	
	private IncomeManager incomeManager;
	private SellManager sellManager;
	
	@Resource
	public void setIncomeManager(IncomeManager incomeManager) {
		this.incomeManager = incomeManager;
	}
	@Resource
	public void setSellManager(SellManager sellManager) {
		this.sellManager = sellManager;
	}

	private IncomeVO incomeVO = new IncomeVO();
	
	public IncomeVO getModel() {
		return incomeVO;
	}

	public void add() {
		Json j = new Json();
		Sell sell = null;
		if (incomeVO.getSellIds() == null || "".equals(incomeVO.getSellIds().trim())) {
			j.setSuccess(false);
			j.setMsg("收款失敗! <br /> 請選擇收款項目");
			super.writeJson(j);
			return;
		}
		
		List<Sell> sellList = new ArrayList<Sell>();
		
		for(String sellId : incomeVO.getSellIds().split(",")) { //根據傳入的id 字串取得所有的銷貨單資訊
			sell = sellManager.get(new Integer(sellId.trim()));
			sellList.add(sell);
		}
		int length = sellList.size();
		for(int i=0; i<length; i++) { //判斷選擇的客戶是否相同
			sell = sellList.get(i);
			if(!sellList.get(0).getCustomer().getId().equals(sell.getCustomer().getId())) {
				j.setMsg("收款失敗 ! <br /> 請選擇相同客戶");
				super.writeJson(j);
				return;
			}
			if(sell.getStatus() ==1) {
				j.setSuccess(false);
				j.setMsg("收款失敗 ! <br /> 項目不可重覆收款");
				super.writeJson(j);
				return;
			}
		}
		
		Income income = incomeManager.get(incomeVO, sellList, session);
		
		List<SellVO> sellVOs = new ArrayList<SellVO>();
		sellManager.copyProperties(income.getSells(), sellVOs);
		//儲存收款單
		incomeManager.add(income);
		j.setSuccess(true);
		j.setMsg("收款成功 !");
		j.setObj(sellVOs);
		super.writeJson(j);
	}
	
	public void exportLastIncomeExcel() throws Exception {
	
		//未實作，複制至PaymentAction匯出Excel的方法
		
	}
	
	public void exportExcel(Income income) throws Exception {
		
		//未實作
			
	}
	
//	private TableHeaderMetaData getTableHeader(String[] hearders){
//		
//		//未實作
//			
//		return null;	
//	}
}
