package com.maxwell.pos.vo;

public class ProductVO implements java.io.Serializable {

	private static final long serialVersionUID = 8077305247246272700L;
	private Integer id;
	private String categoryName;
	private String name;
	private Double price;
	private Integer displayOrder;
	private String description;
	private Integer status;
	private Integer stockNumber;  //庫存數量
	private String unit;          //單位
	private Double purchasePrice; //進貨價格
	private Integer type;         //類型   0 : 非庫存商品  1 : 庫存商品        2 : 組合商品

	private Integer boxId;
	private String boxName;
	private String boxUnit;
	
	private Integer categoryId;
	
	public ProductVO() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getDisplayOrder() {
		return this.displayOrder;
	}

	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public String getStatusStr() {
		if (status != null)
			if (status == 1)
				return "開啟";
		return "關閉";
	}
	
	public Integer getStockNumber() {
		return stockNumber;
	}

	public void setStockNumber(Integer stockNumber) {
		this.stockNumber = stockNumber;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Double getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(Double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	public String getTypeStr() {
		String result = "非庫存商品";
		if (type != null)
			switch (type) {
			case 1:
				result = "庫存商品";
				break;
			case 2:
				result = "組合商品";
				break;
			}
		return result;
	}
	
	public Integer getBoxId() {
		return boxId;
	}

	public void setBoxId(Integer boxId) {
		this.boxId = boxId;
	}

	public String getBoxName() {
		return boxName;
	}

	public void setBoxName(String boxName) {
		this.boxName = boxName;
	}

	public String getBoxUnit() {
		return boxUnit;
	}

	public void setBoxUnit(String boxUnit) {
		this.boxUnit = boxUnit;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	
}