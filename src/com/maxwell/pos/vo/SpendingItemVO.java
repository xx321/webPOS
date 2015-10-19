package com.maxwell.pos.vo;

public class SpendingItemVO implements java.io.Serializable {

	private static final long serialVersionUID = -88977609173675343L;

	private Integer id;
	private Integer subjectId;
	private String subjectName;
	private Double total;

	private String ids;
	private Integer pettyCashId;
	
	public SpendingItemVO() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public Double getTotal() {
		return this.total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public Integer getPettyCashId() {
		return pettyCashId;
	}

	public void setPettyCashId(Integer pettyCashId) {
		this.pettyCashId = pettyCashId;
	}

}