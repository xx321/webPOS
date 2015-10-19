package com.maxwell.pos.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.maxwell.pos.dao.SellDao;
import com.maxwell.pos.model.Sell;
import com.maxwell.pos.util.DateUtil;
import com.maxwell.pos.vo.SellVO;
import com.maxwell.pos.vo.base.DataGrid;

@Component("sellDao")
public class SellDaoImpl extends SuperDaoImpl<Sell, Integer> implements 
		SellDao {

	public DataGrid getDataGrid(SellVO sellVO, Integer rows, Integer page,
			String sort, String order) {
		DataGrid datagrid = new DataGrid();
		
		StringBuffer sb = new StringBuffer();
		List<Object> values = new ArrayList<Object>();
		
		sb.append("from Sell s where 1=1 ");
		addWhere(sellVO, sb, values);
		
		Long total = (Long) queryObject("select count(*) " + sb.toString(), values);
		datagrid.setTotal(total);
		
		addOrderBy(sb, sort, order);
		
		@SuppressWarnings("unchecked")
		List<Sell> sells = (List<Sell>) find(sb.toString(), values, rows, page);
		
		List<SellVO> sellVOs = new ArrayList<SellVO>();
		for (Sell sell : sells) {
			sellVO = new SellVO();
			BeanUtils.copyProperties(sell, sellVO);
			sellVO.setUserName(sell.getUser().getUsername());
			sellVO.setCustomerName(sell.getCustomer().getName());
			if (sell.getIncomePersonnel() != null)
				sellVO.setIncomePersonnelName(sell.getIncomePersonnel().getUsername());
			sellVO.setCreateTimeStr(DateUtil.toCharForDataGrid(sell.getCreateTime()));
			sellVO.setIncomeTimeStr(DateUtil.toCharForDataGrid(sell.getIncomeTime()));
			
			sellVOs.add(sellVO);
		}
		datagrid.setRows(sellVOs);
		
		return datagrid;
	}

	private void addWhere(SellVO sellVO, StringBuffer sb, List<Object> values) {
		
		if (sellVO.getClosed() != null) {  
			sb.append(" and s.closed=? ");
			values.add(sellVO.getClosed());
		}
		//運用JqueryEasyUi 的數字輸入框，省去把id當字串傳入的處理 
//		if (sellVO.getIds() != null && !sellVO.getIds().trim().equals("")) {
//			Integer id = null;
//			try {
//				id = new Integer(sellVO.getIds().trim());
//			} catch (NumberFormatException e) {
//			}
//			sb.append(" and s.id=? ");
//			if (id != null) {	
//				values.add(id);
//			} else {
//				values.add(new Integer(-1));
//			}
//		}
		if (sellVO.getId() != null) {
			sb.append(" and s.id=? ");
			values.add(sellVO.getId());
		}
		if (sellVO.getCustomerName() != null && !sellVO.getCustomerName().trim().equals("")) {
			sb.append(" and s.customer.name like ? ");
			values.add("%%" + sellVO.getCustomerName().trim() + "%%");
		}
		if (sellVO.getInvoiceNumber() != null && !sellVO.getInvoiceNumber().trim().equals("")) {
			sb.append(" and s.invoiceNumber=? ");
			values.add(sellVO.getInvoiceNumber().trim());
		}
		if (sellVO.getUserName() != null && !sellVO.getUserName().trim().equals("")) {
			sb.append(" and s.user.username=? ");
			values.add(sellVO.getUserName().trim());
		}
		if (sellVO.getCreateTimeStr() != null && !sellVO.getCreateTimeStr().trim().equals("")
				&& sellVO.getCreateTimeEnd() != null && !sellVO.getCreateTimeEnd().trim().equals("")) {
			Date dateStart = DateUtil.toDate(sellVO.getCreateTimeStr().trim());
			Date dateEnd = DateUtil.toDate(sellVO.getCreateTimeEnd().trim());
			
			if (dateStart != null && dateEnd !=null) {
				sb.append(" and s.createTime >=? and s.createTime <? ");
				values.add(dateStart);
				values.add(DateUtil.addDay(dateEnd,1));
			}
		}
		if (sellVO.getStatus() != null) {
			sb.append(" and s.status=? ");
			values.add(sellVO.getStatus());
		}
		
		if (sellVO.getIds() != null && !"".equals(sellVO.getIds().trim())) {
			sb.append(" and s.id in (");
			for(String id : sellVO.getIds().split(",")) {
				values.add(new Integer(id.trim()));
				sb.append("?,");
			}
			sb.deleteCharAt(sb.length()-1);
			sb.append(") ");
		}
	}

	public Double getInvoicesIncome(Date beginDate, Date endDate) {
		String hql = "select sum(s.totalAmount) from Sell s where s.createTime>=? and s.createTime<?";
		List<Object> values = new ArrayList<Object>();
		values.add(beginDate);
		values.add(endDate);
		Double result = (Double) queryObject(hql, values);
		if (result == null)
			return 0.0;
		return result;
	}

}
