package com.maxwell.pos.vo.reportVO;

public class FeeVO implements CountI {

	private String name;      //費用名稱  最終會是subject.name
	private Double spending;  //費用支出
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getSpending() {
		return spending;
	}
	public void setSpending(Double spending) {
		this.spending = spending;
	}
}
