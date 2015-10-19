package com.maxwell.pos.vo;

public class TradeInvoiceVO implements java.io.Serializable {

	private static final long serialVersionUID = -675431807098599776L;
	private Integer id;
	private String companyCode;
	private String invoiceNumber;

	public TradeInvoiceVO() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCompanyCode() {
		return this.companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getInvoiceNumber() {
		return this.invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

}