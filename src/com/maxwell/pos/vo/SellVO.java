package com.maxwell.pos.vo;

import java.util.Date;

public class SellVO implements java.io.Serializable {

	private static final long serialVersionUID = 6217939089680907452L;
	private Integer id;
	private String userName;
	private String customerName;
	private String invoiceNumber;
	private Double total;
	private Double tax;
	private Double salesTax;
	private Double totalAmount;
	private Date createTime;
	private String description;
	private Integer status;
	private String incomePersonnelName;  
	private Date incomeTime;       
	private String delivery;      
	
	private Integer closed;
	private Integer customerId;

	private String ids;
	
	private String createTimeStr;
	private String incomeTimeStr;
	
	private String createTimeEnd;
	
	public SellVO() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getSid() {//銷貨單編號(xx + id)
		return "SE" + this.id;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getInvoiceNumber() {
		return this.invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public Double getTotal() {
		return this.total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Double getTax() {
		return tax;
	}

	public void setTax(Double tax) {
		this.tax = tax;
	}

	public Double getSalesTax() {
		return salesTax;
	}

	public void setSalesTax(Double salesTax) {
		this.salesTax = salesTax;
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
	
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public String getStatusStr() {
		if (status != null)
			if (status == 1)
				return "已收款";
		return "未收款";
	}

	public String getIncomePersonnelName() {
		return incomePersonnelName;
	}

	public void setIncomePersonnelName(String incomePersonnelName) {
		this.incomePersonnelName = incomePersonnelName;
	}

	public Date getIncomeTime() {
		return incomeTime;
	}

	public void setIncomeTime(Date incomeTime) {
		this.incomeTime = incomeTime;
	}

	public String getDelivery() {
		return delivery;
	}

	public void setDelivery(String delivery) {
		this.delivery = delivery;
	}

	public Integer getClosed() {
		return this.closed;
	}

	public void setClosed(Integer closed) {
		this.closed = closed;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getCreateTimeStr() {
		return createTimeStr;
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}

	public String getIncomeTimeStr() {
		return incomeTimeStr;
	}

	public void setIncomeTimeStr(String incomeTimeStr) {
		this.incomeTimeStr = incomeTimeStr;
	}

	public String getCreateTimeEnd() {
		return createTimeEnd;
	}

	public void setCreateTimeEnd(String createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}

}