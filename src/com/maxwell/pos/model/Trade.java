package com.maxwell.pos.model;

import java.util.Date;
import java.util.HashSet;
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
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;


@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE) 
@Table(name = "trade")
public class Trade implements java.io.Serializable {

	private static final long serialVersionUID = -7554796428636975652L;
	private Integer id;
	private User user;
	private Checkout checkout;
	private Integer quantity;
	private Double total;
	private Date createTime;
	private Integer status;   //前台  日結帳狀態  0 : 未結   1 : 已結   2 : 作廢
	private Integer type;     //前台  結帳方法      0 : 現金   1 : 發票
	
	private TradeInvoice tradeInvoice;
	
	private Set<Tradeitem> tradeitems = new HashSet<Tradeitem>(0);

	public Trade() {
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "checkout_id")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE) 
	public Checkout getCheckout() {
		return checkout;
	}

	public void setCheckout(Checkout checkout) {
		this.checkout = checkout;
	}

	@Column(name = "quantity")
	public Integer getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Column(name = "total", precision = 11)
	public Double getTotal() {
		return this.total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	@Column(name = "create_time", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Column(name = "status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "type")
	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public TradeInvoice getTradeInvoice() {
		return tradeInvoice;
	}

	public void setTradeInvoice(TradeInvoice tradeInvoice) {
		this.tradeInvoice = tradeInvoice;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "trade")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE) 
	public Set<Tradeitem> getTradeitems() {
		return this.tradeitems;
	}

	public void setTradeitems(Set<Tradeitem> tradeitems) {
		this.tradeitems = tradeitems;
	}
	
}