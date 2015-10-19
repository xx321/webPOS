package com.maxwell.pos.vo.base;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class InvoiceInfo {

	private Integer id;
	private Date createTime;
	private Integer quantity;
	private Integer total;
	
	private String companyCode; //買受人 統一編號
	
	private Set<InvoiceItem> item = new HashSet<InvoiceItem>(0);;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public Set<InvoiceItem> getItem() {
		return item;
	}
	public void setItem(Set<InvoiceItem> item) {
		this.item = item;
	}
	
	
}
