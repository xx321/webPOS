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

import com.maxwell.pos.dao.SpendingItemDao;
import com.maxwell.pos.model.SpendingItem;
import com.maxwell.pos.vo.SpendingItemVO;
import com.maxwell.pos.vo.base.DataGrid;

@Component("spendingItemDao")
public class SpendingItemDaoImpl extends SuperDaoImpl<SpendingItem, Integer> implements
		SpendingItemDao {

	@SuppressWarnings("unchecked")
	public boolean exists(Integer subjectId) {
		String hql = "from SpendingItem s where s.subject.id=?";
		
		List<SpendingItem> spendingItems = getHibernateTemplate().find(hql, new Object[]{ subjectId });
		
		if (spendingItems !=null && spendingItems.size() >0)
			return true;
		return false;
	}

	public DataGrid getDataGrid(SpendingItemVO spendingItemVO, Integer rows,
			Integer page, String sort, String order) {
		DataGrid datagrid = new DataGrid();
		
		StringBuffer sb = new StringBuffer();
		List<Object> values = new ArrayList<Object>();
		
		sb.append("from SpendingItem s where 1=1 ");
		addWhere(spendingItemVO, sb, values);
		
		Long total = (Long) queryObject("select count(*) " + sb.toString(), values);
		datagrid.setTotal(total);
		
		addOrderBy(sb, sort, order);
		
		@SuppressWarnings("unchecked")
		List<SpendingItem> spendingItems = (List<SpendingItem>) find(sb.toString(), values, rows, page);
		
		List<SpendingItemVO> spendingItemVOs = new ArrayList<SpendingItemVO>();
		for (SpendingItem spendingItem : spendingItems) {
			spendingItemVO = new SpendingItemVO();
			BeanUtils.copyProperties(spendingItem, spendingItemVO);
			spendingItemVO.setSubjectId(spendingItem.getSubject().getId());
			spendingItemVO.setSubjectName(spendingItem.getSubject().getName());
			
			spendingItemVOs.add(spendingItemVO);
		}
		datagrid.setRows(spendingItemVOs);
		
		return datagrid;
	}

	private void addWhere(SpendingItemVO spendingItemVO, StringBuffer sb,
			List<Object> values) {
		
		if (spendingItemVO.getPettyCashId() != null) {
			sb.append(" and s.pettyCash.id =? ");
			values.add(spendingItemVO.getPettyCashId());
		}
		
	}

	public void deleteByPettyCashId(final Integer id) {
		final String hql = "delete SpendingItem s where s.pettyCash.id=:id";
		this.getHibernateTemplate().execute(new HibernateCallback() {
			
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				Query query = session.createQuery(hql);
				query.setParameter("id", id);
				return query.executeUpdate();
			}
		});
	}
	
	
	//	select subject_id, sum(total_amount) 
	//	from spending_item 
	//	where petty_cash_id in (select id from petty_cash)
	//	group by subject_id;
	@SuppressWarnings("unchecked")
	public List<Object[]> getFeesBy(Date beginDate, Date endDate) {
		StringBuffer sb = new StringBuffer();
		sb.append("select s.subject.id, sum(s.totalAmount) ");
		sb.append("from SpendingItem s ");
		sb.append("where s.pettyCash.id in (select p.id from PettyCash p where p.createTime>=? and p.createTime<?) ");
		sb.append("group by s.subject.id");
		
		return getHibernateTemplate().find(sb.toString(),
				new Object[] { beginDate, endDate });
	}

	
}
