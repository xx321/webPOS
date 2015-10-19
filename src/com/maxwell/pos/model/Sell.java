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
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "sell")
public class Sell implements java.io.Serializable {

	private static final long serialVersionUID = 6217939089680907452L;
	private Integer id;
	private User user;
	private Income income;			//收款單編號(那一張收款單，結清這個銷貨單款項)
	private Customer customer;
	private Checkout checkout;
	private String invoiceNumber;
	private Double total;
	private Double tax;
	private Double salesTax;
	private Double totalAmount;
	private Date createTime;
	private String description;
	private Integer status;        //狀態(貨款狀態 0 未收、1 已收款)
	private Integer closed;        //結案狀態 (報價銷售狀態 0 報價中...、1 銷售完成)
	private User incomePersonnel;  //收款人員
	private Date incomeTime;       //收款時間
	private String delivery;       //送貨地址
	private String sid;
	private Set<Sellitem> sellitems = new HashSet<Sellitem>(0);

	public Sell() {
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
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "income_id")
	public Income getIncome() {
		return income;
	}

	public void setIncome(Income income) {
		this.income = income;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id")
	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "checkout_id")
	public Checkout getCheckout() {
		return this.checkout;
	}

	public void setCheckout(Checkout checkout) {
		this.checkout = checkout;
	}

	@Column(name = "invoice_number", length = 40)
	public String getInvoiceNumber() {
		return this.invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	@Column(name = "total", precision = 11)
	public Double getTotal() {
		return this.total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}
	
	@Column(name = "tax", precision = 11)
	public Double getTax() {
		return this.tax;
	}

	public void setTax(Double tax) {
		this.tax = tax;
	}
	
	@Column(name = "sales_tax", precision = 11)
	public Double getSalesTax() {
		return salesTax;
	}

	public void setSalesTax(Double salesTax) {
		this.salesTax = salesTax;
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

	@Column(name = "description")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name = "status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "closed")
	public Integer getClosed() {
		return this.closed;
	}

	public void setClosed(Integer closed) {
		this.closed = closed;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "income_personnel")
	public User getIncomePersonnel() {
		return incomePersonnel;
	}

	public void setIncomePersonnel(User incomePersonnel) {
		this.incomePersonnel = incomePersonnel;
	}
	
	@Column(name = "income_time", length = 19)
	public Date getIncomeTime() {
		return incomeTime;
	}

	public void setIncomeTime(Date incomeTime) {
		this.incomeTime = incomeTime;
	}
	
	@Column(name = "delivery", length = 90)
	public String getDelivery() {
		return delivery;
	}

	public void setDelivery(String delivery) {
		this.delivery = delivery;
	}
	
	@Column(name = "sid", length = 90)
	public String getSid() {
		return this.sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sell")
	public Set<Sellitem> getSellitems() {
		return this.sellitems;
	}

	public void setSellitems(Set<Sellitem> sellitems) {
		this.sellitems = sellitems;
	}

}