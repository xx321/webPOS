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
@Table(name = "income", catalog = "pos")
public class Income implements java.io.Serializable {

	private static final long serialVersionUID = 3022232848891134837L;
	private Integer id;
	private Double incomeAmount;
	private User incomePersonnel;	//收款人員
	private Date incomeTime;		//收款時間
	private Integer incomeMode;		//收款方式(0 現金支出、1 匯款方式)
	private String description;
	private String checkNumber;
	private Double checkAmount;
	private Date checkDate;
	
	private Set<Sell> sells = new HashSet<Sell>(0);  //收款項目明細
	
	public Income() {
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

	@Column(name = "income_amount", precision = 11)
	public Double getIncomeAmount() {
		return this.incomeAmount;
	}

	public void setIncomeAmount(Double incomeAmount) {
		this.incomeAmount = incomeAmount;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "income_personnel")
	public User getIncomePersonnel() {
		return incomePersonnel;
	}

	public void setIncomePersonnel(User incomePersonnel) {
		this.incomePersonnel = incomePersonnel;
	}
	
	@Column(name = "income_time", length = 19)
	public Date getIncomeTime() {
		return this.incomeTime;
	}

	public void setIncomeTime(Date incomeTime) {
		this.incomeTime = incomeTime;
	}

	@Column(name = "income_mode")
	public Integer getIncomeMode() {
		return this.incomeMode;
	}

	public void setIncomeMode(Integer incomeMode) {
		this.incomeMode = incomeMode;
	}

	@Column(name = "description")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "check_number", length = 20)
	public String getCheckNumber() {
		return this.checkNumber;
	}

	public void setCheckNumber(String checkNumber) {
		this.checkNumber = checkNumber;
	}

	@Column(name = "check_amount", precision = 11)
	public Double getCheckAmount() {
		return this.checkAmount;
	}

	public void setCheckAmount(Double checkAmount) {
		this.checkAmount = checkAmount;
	}

	@Column(name = "check_date", length = 19)
	public Date getCheckDate() {
		return this.checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "income")
	public Set<Sell> getSells() {
		return this.sells;
	}

	public void setSells(Set<Sell> sells) {
		this.sells = sells;
	}

}