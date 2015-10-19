package com.maxwell.pos.vo;

public class ProductItemVO implements java.io.Serializable {

	private static final long serialVersionUID = -8662644964461632377L;
	private Integer id;
	private Integer productId;
	private String itemName;
	private Integer quantity;
	
	private String ids;

	public ProductItemVO() {
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
	
	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Integer getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

}