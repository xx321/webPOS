package com.maxwell.pos.vo.base;

import java.util.List;

/**
 * easyui的datagrid模型
 * 
 * @author 孙宇
 * 
 */
public class DataGrid implements java.io.Serializable {

	private static final long serialVersionUID = 5257155792011380714L;
	private Long total;// 总记录数
	private List<?> rows;// 每行记录
	private List<?> footer;

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}

	public List<?> getFooter() {
		return footer;
	}

	public void setFooter(List<?> footer) {
		this.footer = footer;
	}

}
