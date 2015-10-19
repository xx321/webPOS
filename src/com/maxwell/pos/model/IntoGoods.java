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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "into_goods")
public class IntoGoods implements java.io.Serializable {

	private static final long serialVersionUID = -7651353887051932656L;
	private Integer id;
	private User user;            //操作人員(key單人員)
	private Payment payment;	  //付款單編號(那一張付款單，結清這個進貨款項)
	private Supplier supplier;    //供應商
	private Checkout checkout;    //結帳狀態，為null 時 (未結) ，已結帳之後會加入checkout
	private String invoiceNumber; //發票編號
	private Double total;         //進貨總金額
	private Double tax;		  //稅率 (0:含稅 或 0.05:未稅<稅外加>)	
	private Double salesTax;      //營業稅 
	private Double totalAmount;   //稅後總金額
	private Date createTime;      //進貨時間
	private String description;
	private Integer status;		//狀態(貨款狀態 0 未付、1 已償付)
	private Integer closed;		//結案狀態 (採購進貨狀態 0 採購中...、1 確認入庫)
	private User paymentPersonnel;	//付款人員
	private Date paymentTime;		//付款時間
	private Integer paymentMode;	//付款方式(0 現金支出、1 匯款方式)
	private Set<GoodsItem> goodsItems = new HashSet<GoodsItem>(0);  //進貨項目明細

	public IntoGoods() {
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
	@JoinColumn(name = "payment_id")
	public Payment getPayment() {
		return this.payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "supplier_id")
	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
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
	@JoinColumn(name = "payment_personnel")
	public User getPaymentPersonnel() {
		return paymentPersonnel;
	}

	public void setPaymentPersonnel(User paymentPersonnel) {
		this.paymentPersonnel = paymentPersonnel;
	}
	
	@Column(name = "payment_time", length = 19)
	public Date getPaymentTime() {
		return paymentTime;
	}

	public void setPaymentTime(Date paymentTime) {
		this.paymentTime = paymentTime;
	}

	@Column(name = "payment_mode")
	public Integer getPaymentMode() {
		return this.paymentMode;
	}

	public void setPaymentMode(Integer paymentMode) {
		this.paymentMode = paymentMode;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "intoGoods")
	public Set<GoodsItem> getGoodsItems() {
		return this.goodsItems;
	}

	public void setGoodsItems(Set<GoodsItem> goodsItems) {
		this.goodsItems = goodsItems;
	}

}