package com.maxwell.pos.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jiangxd.excelTools.ExcelUtils;
import jiangxd.excelTools.JsGridReportBase;
import jiangxd.excelTools.TableData;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.maxwell.pos.action.base.BaseAction;
import com.maxwell.pos.model.User;
import com.maxwell.pos.service.TradeitemManager;
import com.maxwell.pos.util.DateUtil;
import com.maxwell.pos.vo.TradeitemVO;
import com.maxwell.pos.vo.base.DataGrid;
import com.opensymphony.xwork2.ActionContext;

@Component("tradeitemAction")
@Scope("prototype")
public class TradeitemAction extends BaseAction {

	private static final long serialVersionUID = -8739601368139991724L;
	private Map<String, Object> session = ActionContext.getContext().getSession();
	
	private static TradeitemManager tradeitemManager;
	
	@Resource
	public void setTradeitemManager(TradeitemManager tradeitemManager) {
		TradeitemAction.tradeitemManager = tradeitemManager;
	}
	
	private Integer totalItem;
	private Double totalAmount;
	private String createtimeStart;
	private String createtimeEnd;
	
	private Integer tradeId;
	
	public Integer getTotalItem() {
		return totalItem;
	}

	public void setTotalItem(Integer totalItem) {
		this.totalItem = totalItem;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getCreatetimeStart() {
		return createtimeStart;
	}

	public void setCreatetimeStart(String createtimeStart) {
		this.createtimeStart = createtimeStart;
	}

	public String getCreatetimeEnd() {
		return createtimeEnd;
	}

	public void setCreatetimeEnd(String createtimeEnd) {
		this.createtimeEnd = createtimeEnd;
	}
	
	public Integer getTradeId() {
		return tradeId;
	}

	public void setTradeId(Integer tradeId) {
		this.tradeId = tradeId;
	}

	//銷售統計
	public void datagrid() {
		
		Date begin = DateUtil.toDate(createtimeStart);
		Date end = DateUtil.toDate(createtimeEnd);
		if(begin == null || end == null || totalAmount == null || totalItem == null)
		{
			//這裡要設定message
			DataGrid datagrid = new DataGrid();
			datagrid.setRows(new ArrayList<Object>());
			super.writeJson(datagrid);
			return;
		}
		TradeitemVO tradeitemVO = new TradeitemVO();
		BeanUtils.copyProperties(this, tradeitemVO);
		//空指針 1.
		DataGrid datagrid = tradeitemManager.getDataGrid(tradeitemVO, getRows(), getPage(), getSort(), getOrder());
		
		super.writeJson(datagrid);	
	}
	//日結帳
	public void datagridByStatus() {
		
		TradeitemVO tradeitemVO = new TradeitemVO();
		tradeitemVO.setStatus(0);
		//空指針 2.
		DataGrid datagrid = tradeitemManager.getDataGrid(tradeitemVO, getRows(), getPage(), getSort(), getOrder());
		
		super.writeJson(datagrid);	
	}
	
	public void getDatagridByTradeId() {
		DataGrid datagrid = new DataGrid();
		if (tradeId == null) {
			datagrid.setRows(new ArrayList<Object>());
			super.writeJson(datagrid);
		}
		
		TradeitemVO tradeitemVO = new TradeitemVO();
		tradeitemVO.setTradeId(tradeId);
		datagrid = tradeitemManager.getDataGrid2(tradeitemVO, getRows(), getPage(), getSort(), getOrder());
		
		super.writeJson(datagrid);	
	}
	@SuppressWarnings("unchecked")
	public void exportExcel() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();

		TradeitemVO tradeitemVO = new TradeitemVO();
		BeanUtils.copyProperties(this, tradeitemVO);
		
        DataGrid datagrid = tradeitemManager.getDataGrid(tradeitemVO, null, null, "categoryName", "desc");
        
        //获取数据
        List<TradeitemVO> list = (List<TradeitemVO>) datagrid.getRows();
        
        
        String title = "銷售統計";
        User user = (User) session.get("user");
        String creator = user.getUsername();
        
        String[] hearders = new String[] {"類別", "商品名稱", "銷售數量", "銷售數量比重(百分比%)", "銷售金額", "銷售金額比重(百分比%)"};//表头数组
        String[] fields = new String[] {"categoryName", "productName", "quantity", "itemScaleStr", "total", "amountScaleStr"};//People对象属性数组
        TableData td = ExcelUtils.createTableData(list, ExcelUtils.createTableHeader(hearders),fields);
        
        td.compute();//执行小计计算
        
        JsGridReportBase report = new JsGridReportBase(request, response);
        report.exportToExcel(title, creator, td);
	}
}
