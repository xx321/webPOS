package com.maxwell.pos.model;

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
@Table(name = "product")
public class Product implements java.io.Serializable {

	private static final long serialVersionUID = 8077305247246272700L;
	private Integer id;
	private Category category;
	private String name;
	private Double price;
	private Integer displayOrder;
	private String description;
	private Integer status;
	private Integer stockNumber;  //庫存數量
	private String unit;          //單位
	private Double purchasePrice; //進貨價格
	private Integer type;         //類型   0 : 非庫存商品  1 : 庫存商品        2 : 組合商品
	private String barcodeNumber; //一維條碼編號
	private Set<Tradeitem> tradeitems = new HashSet<Tradeitem>(0);
	private Set<GoodsItem> goodsItems = new HashSet<GoodsItem>(0);
	private Set<Sellitem> sellitems = new HashSet<Sellitem>(0);
	private Set<ProductItem> productItems = new HashSet<ProductItem>(0);
	
	private ProductBox productBox;

	public Product() {
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
	@JoinColumn(name = "category_id")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE) 
	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Column(name = "name", length = 40)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "price", precision = 11)
	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Column(name = "display_order")
	public Integer getDisplayOrder() {
		return this.displayOrder;
	}

	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
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

	@Column(name = "stock_number")
	public Integer getStockNumber() {
		return this.stockNumber;
	}

	public void setStockNumber(Integer stockNumber) {
		this.stockNumber = stockNumber;
	}

	@Column(name = "unit")
	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Column(name = "purchase_price", precision = 11)
	public Double getPurchasePrice() {
		return this.purchasePrice;
	}

	public void setPurchasePrice(Double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}
	
	@Column(name = "type")
	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	@Column(name = "barcode_number", length = 90)
	public String getBarcodeNumber() {
		return this.barcodeNumber;
	}

	public void setBarcodeNumber(String barcodeNumber) {
		this.barcodeNumber = barcodeNumber;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "product")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE) 
	public Set<Tradeitem> getTradeitems() {
		return this.tradeitems;
	}

	public void setTradeitems(Set<Tradeitem> tradeitems) {
		this.tradeitems = tradeitems;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "product")
	public Set<GoodsItem> getGoodsItems() {
		return this.goodsItems;
	}

	public void setGoodsItems(Set<GoodsItem> goodsItems) {
		this.goodsItems = goodsItems;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "product")
	public Set<Sellitem> getSellitems() {
		return this.sellitems;
	}

	public void setSellitems(Set<Sellitem> sellitems) {
		this.sellitems = sellitems;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "product")
	public Set<ProductItem> getProductItems() {
		return productItems;
	}

	public void setProductItems(Set<ProductItem> productItems) {
		this.productItems = productItems;
	}
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public ProductBox getProductBox() {
		return productBox;
	}

	public void setProductBox(ProductBox productBox) {
		this.productBox = productBox;
	}

}