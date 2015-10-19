package com.maxwell.pos.vo;

import java.util.Date;

public class IncomeVO implements java.io.Serializable {

	private static final long serialVersionUID = 3022232848891134837L;
	private Integer id;
	private Double incomeAmount;
	private String incomePersonnel;	//收款人員
	private Date incomeTime;		//收款時間
	private Integer incomeMode;		//收款方式(0 現金支出、1 匯款方式)
	private String description;
	private String checkNumber;
	private Double checkAmount;
	private Date checkDate;
	
	private String sellIds;
	private String incomeTimeStr;
	private String checkDateStr;
	
	public IncomeVO() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getIncomeAmount() {
		return this.incomeAmount;
	}

	public void setIncomeAmount(Double incomeAmount) {
		this.incomeAmount = incomeAmount;
	}

	public String getIncomePersonnel() {
		return incomePersonnel;
	}

	public void setIncomePersonnel(String incomePersonnel) {
		this.incomePersonnel = incomePersonnel;
	}

	public Date getIncomeTime() {
		return this.incomeTime;
	}

	public void setIncomeTime(Date incomeTime) {
		this.incomeTime = incomeTime;
	}

	public Integer getIncomeMode() {
		return this.incomeMode;
	}

	public void setIncomeMode(Integer incomeMode) {
		this.incomeMode = incomeMode;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCheckNumber() {
		return checkNumber;
	}

	public void setCheckNumber(String checkNumber) {
		this.checkNumber = checkNumber;
	}

	public Double getCheckAmount() {
		return checkAmount;
	}

	public void setCheckAmount(Double checkAmount) {
		this.checkAmount = checkAmount;
	}

	public Date getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}

	public String getSellIds() {
		return sellIds;
	}

	public void setSellIds(String sellIds) {
		this.sellIds = sellIds;
	}

	public String getIncomeTimeStr() {
		return incomeTimeStr;
	}

	public void setIncomeTimeStr(String incomeTimeStr) {
		this.incomeTimeStr = incomeTimeStr;
	}

	public String getCheckDateStr() {
		return checkDateStr;
	}

	public void setCheckDateStr(String checkDateStr) {
		this.checkDateStr = checkDateStr;
	}

}