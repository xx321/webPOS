package com.maxwell.pos.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "trade_invoice")
public class TradeInvoice implements java.io.Serializable {

	private static final long serialVersionUID = -675431807098599776L;
	private Integer id;
	private Trade trade;
	private String companyCode;
	private String invoiceNumber;
	private Integer status;   //前台  發票列印狀態  0 : 未列印   1 : 已列印
	
	public TradeInvoice() {
	}
	
	@Id
	@GenericGenerator(name ="pkGenerator",strategy="foreign" ,parameters={@Parameter(name = "property", value = "trade")})
    @GeneratedValue(generator="pkGenerator")
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tradeInvoice")
	public Trade getTrade() {
		return this.trade;
	}

	public void setTrade(Trade trade) {
		this.trade = trade;
	}

	@Column(name = "company_code", length = 40)
	public String getCompanyCode() {
		return this.companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	@Column(name = "invoice_number", length = 40)
	public String getInvoiceNumber() {
		return this.invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	
	@Column(name = "status")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
}