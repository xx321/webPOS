package com.maxwell.pos.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.BeanUtils;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Component;

import com.maxwell.pos.dao.IntoGoodsDao;
import com.maxwell.pos.model.IntoGoods;
import com.maxwell.pos.util.DateUtil;
import com.maxwell.pos.vo.IntoGoodsVO;
import com.maxwell.pos.vo.base.DataGrid;

@Component("intoGoodsDao")
public class IntoGoodsDaoImpl extends SuperDaoImpl<IntoGoods, Integer>
		implements IntoGoodsDao {

	public boolean exists(Integer supplierId) {
		String hql = "from IntoGoods i where i.supplier.id=?";
		
		@SuppressWarnings("unchecked")
		List<IntoGoods> intoGoods = getHibernateTemplate().find(hql, new Object[]{ supplierId });
		
		if (intoGoods != null && intoGoods.size() >0)
			return true;
		return false;
	}

	public DataGrid getDataGrid(IntoGoodsVO intoGoodsVO, Integer rows,
			Integer page, String sort, String order) {
		DataGrid datagrid = new DataGrid();
		
		StringBuffer sb = new StringBuffer();
		List<Object> values = new ArrayList<Object>();
		
		sb.append("from IntoGoods i where 1=1 ");
		addWhere(intoGoodsVO, sb, values);
		
		Long total = (Long) queryObject("select count(*) " + sb.toString(), values);
		datagrid.setTotal(total);
		
		addOrderBy(sb, sort, order);
		
		@SuppressWarnings("unchecked")
		List<IntoGoods> intoGoodes = (List<IntoGoods>) find(sb.toString(), values, rows, page);
		
		List<IntoGoodsVO> intoGoodsVOs = new ArrayList<IntoGoodsVO>();
		for (IntoGoods intoGoods : intoGoodes) {
			intoGoodsVO = new IntoGoodsVO();
			BeanUtils.copyProperties(intoGoods, intoGoodsVO);
			intoGoodsVO.setUserName(intoGoods.getUser().getUsername());
			intoGoodsVO.setSupplierName(intoGoods.getSupplier().getName());
			if (intoGoods.getPaymentPersonnel() != null)
				intoGoodsVO.setPaymentPersonnelName(intoGoods.getPaymentPersonnel().getUsername());
			intoGoodsVO.setCreateTimeStr(DateUtil.toCharForDataGrid(intoGoods.getCreateTime()));
			intoGoodsVO.setPaymentTimeStr(DateUtil.toCharForDataGrid(intoGoods.getPaymentTime()));
			
			intoGoodsVOs.add(intoGoodsVO);
		}
		datagrid.setRows(intoGoodsVOs);
		
		return datagrid;
	}

	private void addWhere(IntoGoodsVO intoGoodsVO, StringBuffer sb,
			List<Object> values) {
		// 這裡實作，搜尋條件的方法。
		if (intoGoodsVO.getClosed() != null) {  //從模型裡傳來的 結案狀態來判斷 是採購單 或 進貨單。
			sb.append(" and i.closed=? ");
			values.add(intoGoodsVO.getClosed());
		}
		//運用JqueryEasyUi 的數字輸入框，省去把id當字串傳入的處理 
//		if (intoGoodsVO.getIds() != null && !intoGoodsVO.getIds().trim().equals("")) {
//			Integer id = null;
//			try {
//				id= new Integer(intoGoodsVO.getIds().trim());
//			} catch (NumberFormatException e) {			
//			}
//			sb.append(" and i.id=? ");
//			if (id != null) {
//				values.add(id);
//			} else {
//				values.add(new Integer(-1));
//			}
//		}
		if (intoGoodsVO.getId() != null) {
			sb.append(" and i.id=? ");
			values.add(intoGoodsVO.getId());
		}
		if (intoGoodsVO.getSupplierName() != null && !intoGoodsVO.getSupplierName().trim().equals("")) {
			sb.append(" and i.supplier.name like ? ");
			values.add("%%" + intoGoodsVO.getSupplierName().trim() + "%%");
		}
		if (intoGoodsVO.getInvoiceNumber() != null && !intoGoodsVO.getInvoiceNumber().trim().equals("")) {
			sb.append(" and i.invoiceNumber=? ");
			values.add(intoGoodsVO.getInvoiceNumber().trim());
		}
		if (intoGoodsVO.getUserName() != null && !intoGoodsVO.getUserName().trim().equals("")) {
			sb.append(" and i.user.username=? ");
			values.add(intoGoodsVO.getUserName().trim());
		}
		if (intoGoodsVO.getCreateTimeStr() != null && !intoGoodsVO.getCreateTimeStr().trim().equals("")
				&& intoGoodsVO.getCreateTimeEnd() !=null && !intoGoodsVO.getCreateTimeEnd().trim().equals("")) {
			Date dateStart = DateUtil.toDate(intoGoodsVO.getCreateTimeStr().trim());
			Date dateEnd = DateUtil.toDate(intoGoodsVO.getCreateTimeEnd().trim());
			
			if (dateStart != null && dateEnd !=null) {
				sb.append(" and i.createTime >=? and i.createTime <? ");
				values.add(dateStart);
				values.add(DateUtil.addDay(dateEnd,1));
			}
		}
		if (intoGoodsVO.getStatus() != null) {
			sb.append(" and i.status=? ");
			values.add(intoGoodsVO.getStatus());
		}
		if (intoGoodsVO.getIds() != null && !"".equals(intoGoodsVO.getIds().trim())) {
			sb.append(" and i.id in (");
			for(String id : intoGoodsVO.getIds().split(",")) {
				values.add(new Integer(id.trim()));
				sb.append("?,");
			}
			sb.deleteCharAt(sb.length()-1);
			sb.append(") ");
		}
	}

	@SuppressWarnings("unchecked")
	public List<IntoGoods> findListByIds(final List<Integer> ids) {
		final String hql = "from IntoGoods i where i.id in (:ids) ";
		return (List<IntoGoods>) this.getHibernateTemplate().execute(new HibernateCallback() {

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				query.setParameterList("ids", ids);		
				return query.list();
			}
		});
	}

	
}
