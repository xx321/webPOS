package com.maxwell.pos.vo.reportVO;

public class IncomeVO {

	private Double invoicesIncome;  //發票收入
	private Double cashIncome;      //現金收入
	private Double totalIncome;     //收入總計
	
	public Double getInvoicesIncome() {
		return invoicesIncome;
	}
	public void setInvoicesIncome(Double invoicesIncome) {
		this.invoicesIncome = invoicesIncome;
	}
	public Double getCashIncome() {
		return cashIncome;
	}
	public void setCashIncome(Double cashIncome) {
		this.cashIncome = cashIncome;
	}
	public Double getTotalIncome() {
		return totalIncome;
	}
	public void setTotalIncome(Double totalIncome) {
		this.totalIncome = totalIncome;
	}
	
	
}
