package com.maxwell.pos.vo.reportVO;

public class CostVO implements CountI {

	private String name;      //成本名稱 最終會是category.name
	private Double Spending;  //成本支出
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getSpending() {
		return Spending;
	}
	public void setSpending(Double spending) {
		Spending = spending;
	}

}
