package com.maxwell.pos.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE) 
@Table(name = "user")
public class User implements java.io.Serializable {

	private static final long serialVersionUID = 7900153178703364183L;
	private Integer id;
	private String username;
	private String account;
	private String password;
	private Integer purview;
	private Integer status;
	
	private Set<Trade> trades = new HashSet<Trade>(0);
	private Set<Checkout> checkouts = new HashSet<Checkout>(0);
	
	public User() {
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

	@Column(name = "username", length = 40)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "account", length = 40)
	public String getAccount() {
		return this.account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	@Column(name = "password", length = 40)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "purview")
	public Integer getPurview() {
		return this.purview;
	}

	public void setPurview(Integer purview) {
		this.purview = purview;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	@OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Trade> getTradeitems() {
		return this.trades;
	}

	public void setTradeitems(Set<Trade> trades) {
		this.trades = trades;
	}
	
	@OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Checkout> getCheckouts() {
		return checkouts;
	}

	public void setCheckouts(Set<Checkout> checkouts) {
		this.checkouts = checkouts;
	}
}