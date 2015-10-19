package com.maxwell.pos.vo;

public class CategoryVO implements java.io.Serializable {

	private static final long serialVersionUID = -1397499716514921927L;
	private Integer id;
	private String name;
	private Integer displayOrder;
	private String description;
	private Integer status;

	public CategoryVO() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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

}