package com.maxwell.pos.vo;

public class TradeitemVO implements java.io.Serializable {

	private static final long serialVersionUID = 5880429915296029559L;

	private String categoryName;
	private Integer productId;
	private String productName;
	private Integer quantity;
	private Double purchasePrice;
	private Double total;
	private Double itemScale;
	private Double amountScale;

	private Integer totalItem;
	private Double totalAmount;
	private String createtimeStart;
	private String createtimeEnd;
	
	private Integer status;
	
	private String unit;
	
	private Integer tradeId;
	
	public TradeitemVO() {
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
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
		return purchasePrice;
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

	public Double getItemScale() {
		return itemScale;
	}

	public void setItemScale(Double itemScale) {
		this.itemScale = itemScale;
	}

	public Double getAmountScale() {
		return amountScale;
	}

	public void setAmountScale(Double amountScale) {
		this.amountScale = amountScale;
	}

	public String getItemScaleStr() {
		return itemScale + "%";
	}

	public String getAmountScaleStr() {
		return amountScale + "%";
	}

	public Integer getTotalItem() {
		return totalItem;
	}

	public void setTotalItem(Integer totalItem) {
		this.totalItem = totalItem;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Integer getTradeId() {
		return tradeId;
	}

	public void setTradeId(Integer tradeId) {
		this.tradeId = tradeId;
	}
	
}