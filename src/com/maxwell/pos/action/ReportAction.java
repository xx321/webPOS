package com.maxwell.pos.action;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.maxwell.pos.action.base.BaseAction;
import com.maxwell.pos.service.ReportManager;
import com.maxwell.pos.util.DateUtil;
import com.maxwell.pos.vo.reportVO.CostVO;
import com.maxwell.pos.vo.reportVO.CountI;
import com.maxwell.pos.vo.reportVO.FeeVO;
import com.maxwell.pos.vo.reportVO.IncomeVO;


@Component("reportAction")
@Scope("prototype")
public class ReportAction extends BaseAction {

	private static final long serialVersionUID = -6522925553093457526L;
	private ReportManager reportManager;
	
	@Resource
	public void setReportManager(ReportManager reportManager) {
		this.reportManager = reportManager;
	}
	/*     前台輸入項          */
	private String createtimeStart;
	private String createtimeEnd;
	
	public String getCreatetimeStart() {
		return createtimeStart;
	}
	
	public void setCreatetimeStart(String createtimeStart) {
		this.createtimeStart = createtimeStart;
	}
	
	public String getCreatetimeEnd() {
		return createtimeEnd;
	}
	
	public void setCreatetimeEnd(String createtimeEnd) {
		this.createtimeEnd = createtimeEnd;
	}
	
	/*              後台輸出項                               */
	
	private IncomeVO incomeVO;        //收入
	private List<CostVO> costVOs;     //營業成本項目
	private Double costTotal;         //營業成本總計
	private List<FeeVO> feeVOs;       //營業費用項目
	private Double feeTotal;          //營業費用總計
	
	private Double grossProfit;       //營業毛利
	private Double operatingIncome;   //營業淨利
	private Double incomeBfTaxes;     //稅前損益
	
	public IncomeVO getIncomeVO() {
		return incomeVO;
	}
	public List<CostVO> getCostVOs() {
		return costVOs;
	}
	public Double getCostTotal() {
		return costTotal;
	}
	public List<FeeVO> getFeeVOs() {
		return feeVOs;
	}
	public Double getFeeTotal() {
		return feeTotal;
	}
	public Double getGrossProfit() {
		return grossProfit;
	}
	public Double getOperatingIncome() {
		return operatingIncome;
	}
	public Double getIncomeBfTaxes() {
		return incomeBfTaxes;
	}
	/*              後台輸出項                               */
	
	
	public String findIncomeStatement() {
		
		Date beginDate = DateUtil.toDate(createtimeStart);
		Date endDate = DateUtil.addDay(DateUtil.toDate(createtimeEnd), 1);
		
		incomeVO = reportManager.getIncomeBy(beginDate, endDate);
		
		costVOs = reportManager.getCostsBy(beginDate, endDate);
		costTotal = countTotal(costVOs);
		
		feeVOs = reportManager.getFeesBy(beginDate, endDate);
		feeTotal = countTotal(feeVOs);
		
		grossProfit = incomeVO.getTotalIncome() - costTotal;
		operatingIncome = grossProfit - feeTotal;
		
		//稅前損益 = 營業淨利 (因為這裡沒有加入 ， 營業外收入。!!!!
		incomeBfTaxes = operatingIncome;
		
		return SUCCESS;
	}


	private Double countTotal(List<?extends CountI> input) {
		Double result = 0.0;
		for (CountI c : input) {
			if (c.getSpending() !=null)
				result = result + c.getSpending();
		}
		return Math.rint(result);
	}
}
