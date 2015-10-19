package com.maxwell.pos.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.maxwell.pos.dao.PettyCashDao;
import com.maxwell.pos.model.PettyCash;
import com.maxwell.pos.util.DateUtil;
import com.maxwell.pos.vo.PettyCashVO;
import com.maxwell.pos.vo.base.DataGrid;

@Component("pettyCashDao")
public class PettyCashDaoImpl extends SuperDaoImpl<PettyCash, Integer> implements
		PettyCashDao {

	public DataGrid getDataGrid(PettyCashVO pettyCashVO, Integer rows,
			Integer page, String sort, String order) {
		DataGrid datagrid = new DataGrid();
		
		StringBuffer sb = new StringBuffer();
		List<Object> values = new ArrayList<Object>();
		
		sb.append("from PettyCash p where 1=1 ");
		addWhere(pettyCashVO, sb, values);
		
		Long total = (Long) queryObject("select count(*) " + sb.toString(), values);
		datagrid.setTotal(total);
		
		addOrderBy(sb, sort, order);
		
		@SuppressWarnings("unchecked")
		List<PettyCash> pettyCashs = (List<PettyCash>) find(sb.toString(), values, rows, page);
		
		List<PettyCashVO> pettyCashVOs = new ArrayList<PettyCashVO>();
		for (PettyCash pettyCash : pettyCashs) {
			pettyCashVO = new PettyCashVO();
			BeanUtils.copyProperties(pettyCash, pettyCashVO);
			if (pettyCash.getSupplier() !=null)
				pettyCashVO.setSupplierName(pettyCash.getSupplier().getName());
			pettyCashVO.setCreateTimeStr(DateUtil.toCharForDataGrid(pettyCash.getCreateTime()));
			pettyCashVO.setPaymentTimeStr(DateUtil.toCharForDataGrid(pettyCash.getPaymentTime()));
			
			pettyCashVOs.add(pettyCashVO);
		}
		datagrid.setRows(pettyCashVOs);
		
		return datagrid;
	}

	private void addWhere(PettyCashVO pettyCashVO, StringBuffer sb,
			List<Object> values) {
		
		//數據表格  查詢條件 
		if (pettyCashVO.getIds() != null && !pettyCashVO.getIds().trim().equals("")) {
			Integer id = null;
			try {
				id= new Integer(pettyCashVO.getIds().trim());
			} catch (NumberFormatException e) {			
			}
			sb.append(" and p.id=? ");
			if (id != null) {
				values.add(id);
			} else {
				values.add(new Integer(-1));
			}
		}
		if (pettyCashVO.getSupplierName() != null && !pettyCashVO.getSupplierName().trim().equals("")) {
			sb.append(" and p.supplier.name like ? ");
			values.add("%%" + pettyCashVO.getSupplierName().trim() + "%%");
		}
		if (pettyCashVO.getPaymentAccount() != null && !pettyCashVO.getPaymentAccount().trim().equals("")) {
			sb.append(" and p.paymentAccount=? ");
			values.add(pettyCashVO.getPaymentAccount().trim());
		}
		if (pettyCashVO.getUsername() != null && !pettyCashVO.getUsername().trim().equals("")) {
			sb.append(" and p.username=? ");
			values.add(pettyCashVO.getUsername().trim());
		}
		if (pettyCashVO.getCreateTimeStr() != null && !pettyCashVO.getCreateTimeStr().trim().equals("")
				&& pettyCashVO.getCreateTimeEnd() != null && !pettyCashVO.getCreateTimeEnd().trim().equals("")) {
			Date dateStart = DateUtil.toDate(pettyCashVO.getCreateTimeStr().trim());
			Date dateEnd = DateUtil.toDate(pettyCashVO.getCreateTimeEnd().trim());
			
			if (dateStart != null && dateEnd !=null) {
				sb.append(" and p.createTime >=? and p.createTime <? ");
				values.add(dateStart);
				values.add(DateUtil.addDay(dateEnd,1));
			}
		}
		if (pettyCashVO.getStatus() != null) {
			sb.append(" and p.status=? ");
			values.add(pettyCashVO.getStatus());
		}
		
	}

	
}
