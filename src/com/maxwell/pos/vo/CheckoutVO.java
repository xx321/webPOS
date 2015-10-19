package com.maxwell.pos.vo;

import java.util.Date;

import com.maxwell.pos.model.User;

public class CheckoutVO implements java.io.Serializable {

	private static final long serialVersionUID = 7225636152540889765L;
	private User user;
	private Integer cashItem;
	private Double cashAmount;
	private Integer invoiceItem;
	private Double invoiceAmount;
	private Integer totalItem;
	private Double totalAmount;
	private Date createTime;
	
	private Integer cashCount;
	private Integer invoiceCount;
	private Integer totalCount;
	
	private String createtimeStart;
	private String createtimeEnd;
	
	public CheckoutVO() {
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getCashItem() {
		return cashItem;
	}

	public void setCashItem(Integer cashItem) {
		this.cashItem = cashItem;
	}

	public Double getCashAmount() {
		return cashAmount;
	}

	public void setCashAmount(Double cashAmount) {
		this.cashAmount = cashAmount;
	}

	public Integer getInvoiceItem() {
		return invoiceItem;
	}

	public void setInvoiceItem(Integer invoiceItem) {
		this.invoiceItem = invoiceItem;
	}

	public Double getInvoiceAmount() {
		return invoiceAmount;
	}

	public void setInvoiceAmount(Double invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}

	public Integer getTotalItem() {
		return totalItem;
	}

	public void setTotalItem(Integer totalItem) {
		this.totalItem = totalItem;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getCashCount() {
		return cashCount;
	}

	public void setCashCount(Integer cashCount) {
		this.cashCount = cashCount;
	}

	public Integer getInvoiceCount() {
		return invoiceCount;
	}

	public void setInvoiceCount(Integer invoiceCount) {
		this.invoiceCount = invoiceCount;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

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

}