package com.maxwell.pos.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.BeanUtils;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Component;

import com.maxwell.pos.dao.SellitemDao;
import com.maxwell.pos.model.Sellitem;
import com.maxwell.pos.vo.SellitemVO;
import com.maxwell.pos.vo.base.DataGrid;

@Component("sellitemDao")
public class SellitemDaoImpl extends SuperDaoImpl<Sellitem, Integer> implements
		SellitemDao {

	public DataGrid getDataGrid(SellitemVO sellitemVO, Integer rows,
			Integer page, String sort, String order) {
		DataGrid datagrid = new DataGrid();
		
		StringBuffer sb = new StringBuffer();
		List<Object> values = new ArrayList<Object>();
		
		sb.append("from Sellitem s where 1=1 ");
		addWhere(sellitemVO, sb, values);
		
		Long total = (Long) queryObject("select count(*) " + sb.toString(), values);
		datagrid.setTotal(total);
		
		addOrderBy(sb, sort, order);
		
		@SuppressWarnings("unchecked")
		List<Sellitem> sellitems = (List<Sellitem>) find(sb.toString(), values, rows, page);
		
		List<SellitemVO> sellitemVOs = new ArrayList<SellitemVO>();
		
		for (Sellitem sellitem : sellitems) {
			sellitemVO = new SellitemVO();
			BeanUtils.copyProperties(sellitem, sellitemVO);
			sellitemVO.setProductName(sellitem.getProduct().getName());
			sellitemVO.setUnit(sellitem.getProduct().getUnit());
			
			sellitemVOs.add(sellitemVO);
		}
		
		datagrid.setRows(sellitemVOs);
		
		return datagrid;
	}

	private void addWhere(SellitemVO sellitemVO, StringBuffer sb,
			List<Object> values) {
		
		if (sellitemVO.getSellId() != null) {
			sb.append(" and s.sell.id = ? ");
			values.add(sellitemVO.getSellId());
		}
		
	}

	public void deleteBySellId(final Integer id) {
		final String hql = "delete Sellitem s where s.sell.id=:id";
		this.getHibernateTemplate().execute(new HibernateCallback() {
			
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				Query query = session.createQuery(hql);
				query.setParameter("id", id);
				return query.executeUpdate();
			}
		});
	}
	
	@SuppressWarnings("unchecked")
	public List<Sellitem> findBySellId(Integer id) {
		final String hql = "from Sellitem s where s.sell.id=? ";
		List<Object> values = new ArrayList<Object>();
		values.add(id);
		List<Sellitem> sellitems = (List<Sellitem>) find(hql, values, null, null);
		return sellitems;
	}

}
