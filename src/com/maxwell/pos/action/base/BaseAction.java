package com.maxwell.pos.action.base;

import java.io.IOException;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 基礎ACTION,其它ACTION繼承此ACTION來獲得writeJson和ActionSupport的功能
 * 
 */
public class BaseAction extends ActionSupport{

	private static final long serialVersionUID = -8324690232744099462L;

	private Integer page;
	private Integer rows;
	private String sort;
	private String order;
	
	public Integer getPage() {
		if (page == null || page < 1) {
			page = 1;
		}
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRows() {
		if (rows == null || rows < 1) {
			rows = 10;
		}
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	/**
	 * 將對象轉換成JSON字符串，並且傳回前台
	 * 
	 * @param Object
	 * @throws IOException
	 */
	public void writeJson(Object object) {
		try {
			String json = JSON.toJSONStringWithDateFormat(object,
					"yyyy-MM-dd HH:mm:ss");
			ServletActionContext.getResponse().setContentType(
					"text/html;charset=utf-8");
			ServletActionContext.getResponse().getWriter().write(json);
			ServletActionContext.getResponse().getWriter().flush();
			ServletActionContext.getResponse().getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
