package com.maxwell.pos.vo;

public class UserVO implements java.io.Serializable {

	private static final long serialVersionUID = 7900153178703364183L;
	private Integer id;
	private String username;
	private String account;
	private String password;
	private Integer purview;
	private Integer status;

	public UserVO() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAccount() {
		return this.account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getPurview() {
		return this.purview;
	}

	public void setPurview(Integer purview) {
		this.purview = purview;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getPurviewStr() {
		if (purview != null)
			if (purview == 1)
				return "管理員";
		return "操作員";
	}

	public String getStatusStr() {
		if (status != null)
			if (status == 1)
				return "在職";
		return "離職";
	}
	
}