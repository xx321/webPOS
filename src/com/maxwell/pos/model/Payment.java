package com.maxwell.pos.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "payment", catalog = "pos")
public class Payment implements java.io.Serializable {

	private static final long serialVersionUID = -848957661659830927L;
	private Integer id;
	private Double paymentAmount;
	private User paymentPersonnel;	//付款人員
	private Date paymentTime;		//付款時間
	private Integer paymentMode;	//付款方式(0 現金支出、1 匯款方式)
	private String description;
	
	private Set<IntoGoods> intoGoodses = new HashSet<IntoGoods>(0);  //付款項目明細

	public Payment() {
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

	@Column(name = "payment_amount", precision = 11)
	public Double getPaymentAmount() {
		return this.paymentAmount;
	}

	public void setPaymentAmount(Double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "payment_personnel")
	public User getPaymentPersonnel() {
		return paymentPersonnel;
	}

	public void setPaymentPersonnel(User paymentPersonnel) {
		this.paymentPersonnel = paymentPersonnel;
	}
	
	@Column(name = "payment_time", length = 19)
	public Date getPaymentTime() {
		return this.paymentTime;
	}

	public void setPaymentTime(Date paymentTime) {
		this.paymentTime = paymentTime;
	}

	@Column(name = "payment_mode")
	public Integer getPaymentMode() {
		return this.paymentMode;
	}

	public void setPaymentMode(Integer paymentMode) {
		this.paymentMode = paymentMode;
	}
	
	@Column(name = "description")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "payment")
	public Set<IntoGoods> getIntoGoodses() {
		return this.intoGoodses;
	}

	public void setIntoGoodses(Set<IntoGoods> intoGoodses) {
		this.intoGoodses = intoGoodses;
	}

}