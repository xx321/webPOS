package com.maxwell.pos.model;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "invoice_for_front")
public class InvoiceForFront implements java.io.Serializable {

	private static final long serialVersionUID = 7016608925605325766L;
	private Integer id;
	private String invoiceCoding;
	private Integer invoiceNumber;
	private Integer quantity;
	
	private Timestamp updateTime;  //實現@Version : 樂觀鎖定

	public InvoiceForFront() {
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

	@Column(name = "invoice_coding", length = 40)
	public String getInvoiceCoding() {
		return this.invoiceCoding;
	}

	public void setInvoiceCoding(String invoiceCoding) {
		this.invoiceCoding = invoiceCoding;
	}

	@Column(name = "invoice_number")
	public Integer getInvoiceNumber() {
		return this.invoiceNumber;
	}

	public void setInvoiceNumber(Integer invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	@Column(name = "quantity")
	public Integer getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Version
	@Column(name = "update_time", length = 19)
	public Timestamp getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

}