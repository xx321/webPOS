package com.maxwell.pos.vo;

import java.util.Date;

public class IntoGoodsVO implements java.io.Serializable {

	private static final long serialVersionUID = -7651353887051932656L;
	private Integer id;
	private String userName;		//操作人員(key單人員)
	private String supplierName;	//供應商
	private String invoiceNumber; //發票編號
	private Double total;         //合計
	private Double tax;			  //稅率
	private Double salesTax;	  //營業稅	
	private Double totalAmount;   //總計
	private Date createTime;      //進貨時間
	private String description;   
	private Integer status;       //狀態(貨款狀態 0 未付、1 已償付)
	private String paymentPersonnelName; //付款人員
	private Date paymentTime;          //付款時間
	private Integer paymentMode;	//付款方式(0 現金支出、1 匯款方式)
	
	private Integer closed;       //結案狀態 (採購進貨狀態 0 採購中...、1 確認入庫)	
	private Integer supplierId;
	
	private String ids;
	
	private String createTimeStr;
	private String paymentTimeStr;
	
	private String createTimeEnd;
	
	public IntoGoodsVO() {
	}

	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public String getSid() {//進貨單編號(xx + id)
		return "IN" + this.id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
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
				return "已付款";
		return "未償付";
	}
	
	public String getPaymentPersonnelName() {
		return paymentPersonnelName;
	}

	public void setPaymentPersonnelName(String paymentPersonnelName) {
		this.paymentPersonnelName = paymentPersonnelName;
	}

	public Date getPaymentTime() {
		return paymentTime;
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
	
	public String getPaymentModeStr() {
		if (paymentMode != null) {
			if (paymentMode == 1)
				return "匯款方式";
		}
		return "現金支出";
	}
	public Integer getClosed() {
		return closed;
	}

	public void setClosed(Integer closed) {
		this.closed = closed;
	}

	public Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
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

	public String getPaymentTimeStr() {
		return paymentTimeStr;
	}

	public void setPaymentTimeStr(String paymentTimeStr) {
		this.paymentTimeStr = paymentTimeStr;
	}

	public String getCreateTimeEnd() {
		return createTimeEnd;
	}

	public void setCreateTimeEnd(String createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}
	
}