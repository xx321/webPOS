package com.maxwell.pos.vo;

import java.util.Date;

public class TradeVO implements java.io.Serializable {

	private static final long serialVersionUID = -7554796428636975652L;
	private Integer id;
	private Date createTime;
	private Integer quantity;
	private Double total;
	private String username;
	private Integer status;   //前台  日結帳狀態  0 : 未結   1 : 已結   2 : 作廢
	private Integer type;     //前台  結帳方法      0 : 現金   1 : 發票
	
	private String ids;
	private String invoiceNumber;
	
	private String createtimeStart;
	private String createtimeEnd;
	
	public TradeVO() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getTotal() {
		return this.total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	public String getTypeStr() {
		if (type == null || type == 0)
			return "現金收入";
		return "發票收入";
	}
	
	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
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