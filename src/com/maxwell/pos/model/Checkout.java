package com.maxwell.pos.model;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE) 
@Table(name = "checkout")
public class Checkout implements java.io.Serializable {

	private static final long serialVersionUID = 7225636152540889765L;
	private Integer id;
	private User user;
	private Integer cashCount;
	private Integer cashItem;
	private Double cashAmount;
	private Integer invoiceCount;
	private Integer invoiceItem;
	private Double invoiceAmount;
	private Integer totalCount;
	private Integer totalItem;
	private Double totalAmount;
	private Date createTime;
	
	private Set<Trade> trades = new LinkedHashSet<Trade>(0);
	public Checkout() {
	}

	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE) 
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "cash_count")
	public Integer getCashCount() {
		return this.cashCount;
	}

	public void setCashCount(Integer cashCount) {
		this.cashCount = cashCount;
	}

	@Column(name = "cash_item")
	public Integer getCashItem() {
		return this.cashItem;
	}

	public void setCashItem(Integer cashItem) {
		this.cashItem = cashItem;
	}

	@Column(name = "cash_amount", precision = 11)
	public Double getCashAmount() {
		return this.cashAmount;
	}

	public void setCashAmount(Double cashAmount) {
		this.cashAmount = cashAmount;
	}

	@Column(name = "invoice_count")
	public Integer getInvoiceCount() {
		return this.invoiceCount;
	}

	public void setInvoiceCount(Integer invoiceCount) {
		this.invoiceCount = invoiceCount;
	}

	@Column(name = "invoice_item")
	public Integer getInvoiceItem() {
		return this.invoiceItem;
	}

	public void setInvoiceItem(Integer invoiceItem) {
		this.invoiceItem = invoiceItem;
	}

	@Column(name = "invoice_amount", precision = 11)
	public Double getInvoiceAmount() {
		return this.invoiceAmount;
	}

	public void setInvoiceAmount(Double invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}

	@Column(name = "total_count")
	public Integer getTotalCount() {
		return this.totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	@Column(name = "total_item")
	public Integer getTotalItem() {
		return this.totalItem;
	}

	public void setTotalItem(Integer totalItem) {
		this.totalItem = totalItem;
	}

	@Column(name = "total_amount", precision = 11)
	public Double getTotalAmount() {
		return this.totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	@Column(name = "create_time", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "checkout")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE) 
	public Set<Trade> getTrades() {
		return trades;
	}

	public void setTrades(Set<Trade> trades) {
		this.trades = trades;
	}

}