package com.maxwell.pos.vo;

public class InvoiceForFrontVO implements java.io.Serializable {

	private static final long serialVersionUID = 7016608925605325766L;
	private Integer id;
	private String invoiceCoding;
	private Integer invoiceNumber;
	private Integer quantity;       //使用數量

	public InvoiceForFrontVO() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getInvoiceCoding() {
		return this.invoiceCoding;
	}

	public void setInvoiceCoding(String invoiceCoding) {
		this.invoiceCoding = invoiceCoding;
	}

	public Integer getInvoiceNumber() {
		return this.invoiceNumber;
	}

	public void setInvoiceNumber(Integer invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public Integer getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}