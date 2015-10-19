package com.maxwell.pos.vo;

public class ProductBoxVO implements java.io.Serializable {

	private static final long serialVersionUID = -6426742731186071180L;
	private Integer id;
	private String productName;
	private String boxName;
	private Integer quantity;

	public ProductBoxVO() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getBoxName() {
		return boxName;
	}

	public void setBoxName(String boxName) {
		this.boxName = boxName;
	}

	public Integer getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}