package com.maxwell.pos.vo;

public class SellitemVO implements java.io.Serializable {

	private static final long serialVersionUID = -8298170443866336314L;
	private Integer id;
	private Integer productId;
	private String productName;
	private Integer quantity;
	private Double purchasePrice;
	private Double total;
	private Double totalAmount;
	
	private String unit;
	
	private String ids;
	private Integer sellId;

	public SellitemVO() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPurchasePrice() {
		return this.purchasePrice;
	}

	public void setPurchasePrice(Double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public Double getTotal() {
		return this.total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}
	
	public Double getTotalAmount() {
		return this.totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public Integer getSellId() {
		return sellId;
	}

	public void setSellId(Integer sellId) {
		this.sellId = sellId;
	}

}