package com.maxwell.pos.vo;

import java.util.Date;

public class PaymentVO implements java.io.Serializable {

	private static final long serialVersionUID = -848957661659830927L;
	private Integer id;
	private Double paymentAmount;
	private String paymentPersonnelName;	//付款人員
	private Date paymentTime;		//付款時間
	private Integer paymentMode;	//付款方式(0 現金支出、1 匯款方式)
	private String description;   
	
	private String intoGoodsIds;
	private String paymentTimeStr;
	
	
	public PaymentVO() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getPaymentAmount() {
		return this.paymentAmount;
	}

	public void setPaymentAmount(Double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public String getPaymentPersonnelName() {
		return paymentPersonnelName;
	}

	public void setPaymentPersonnelName(String paymentPersonnelName) {
		this.paymentPersonnelName = paymentPersonnelName;
	}

	public Date getPaymentTime() {
		return this.paymentTime;
	}

	public void setPaymentTime(Date paymentTime) {
		this.paymentTime = paymentTime;
	}

	public Integer getPaymentMode() {
		return this.paymentMode;
	}

	public void setPaymentMode(Integer paymentMode) {
		this.paymentMode = paymentMode;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getIntoGoodsIds() {
		return intoGoodsIds;
	}

	public void setIntoGoodsIds(String intoGoodsIds) {
		this.intoGoodsIds = intoGoodsIds;
	}

	public String getPaymentTimeStr() {
		return paymentTimeStr;
	}

	public void setPaymentTimeStr(String paymentTimeStr) {
		this.paymentTimeStr = paymentTimeStr;
	}

}