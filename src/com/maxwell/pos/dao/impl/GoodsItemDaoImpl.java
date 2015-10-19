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

import com.maxwell.pos.dao.GoodsItemDao;
import com.maxwell.pos.model.GoodsItem;
import com.maxwell.pos.vo.GoodsItemVO;
import com.maxwell.pos.vo.base.DataGrid;

@Component("goodsItemDao")
public class GoodsItemDaoImpl extends SuperDaoImpl<GoodsItem, Integer>
		implements GoodsItemDao {

	public DataGrid getDataGrid(GoodsItemVO goodsItemVO, Integer rows,
			Integer page, String sort, String order) {
		DataGrid datagrid = new DataGrid();
		
		StringBuffer sb = new StringBuffer();
		List<Object> values = new ArrayList<Object>();
		
		sb.append("from GoodsItem g where 1=1 ");
		addWhere(goodsItemVO, sb, values);
		
		Long total = (Long) queryObject("select count(*) " + sb.toString(), values);
		datagrid.setTotal(total);
		
		addOrderBy(sb, sort, order);
		
		@SuppressWarnings("unchecked")
		List<GoodsItem> goodItems = (List<GoodsItem>) find(sb.toString(), values, rows, page);
		
		List<GoodsItemVO> goodsItemVOs = new ArrayList<GoodsItemVO>();
		
		for (GoodsItem goodItem : goodItems) {
			goodsItemVO = new GoodsItemVO();
			BeanUtils.copyProperties(goodItem, goodsItemVO);
			goodsItemVO.setProductName(goodItem.getProduct().getName());
			goodsItemVO.setUnit(goodItem.getProduct().getUnit());
			
			goodsItemVOs.add(goodsItemVO);
		}
		datagrid.setRows(goodsItemVOs);
		
		return datagrid;
	}

	private void addWhere(GoodsItemVO goodsItemVO, StringBuffer sb,
			List<Object> values) {
		
		if (goodsItemVO.getIntoGoodsId() != null) {
			sb.append(" and g.intoGoods.id = ? ");
			values.add(goodsItemVO.getIntoGoodsId());
		}
		
	}

	public void deleteByIntoGoodsId(final Integer id) {
		final String hql = "delete GoodsItem g where g.intoGoods.id=:id";
		this.getHibernateTemplate().execute(new HibernateCallback() {

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				query.setParameter("id", id);
				return query.executeUpdate();
			}});
	}

	@SuppressWarnings("unchecked")
	public List<GoodsItem> findByIntoGoodsId(Integer id) {
		final String hql = "from GoodsItem g where g.intoGoods.id=? ";
		List<Object> values = new ArrayList<Object>();
		values.add(id);
		List<GoodsItem> goodsItems = (List<GoodsItem>) find(hql, values, null, null);
		return goodsItems;
	}
	
//	select product_id, sum(total_amount)
//	from goods_item
//	where into_goods_id in (select id from into_goods)
//	group by product_id;
	@SuppressWarnings("unchecked")
	public List<Object[]> getCostsBy(Date beginDate, Date endDate) {
		StringBuffer sb = new StringBuffer();
		sb.append("select g.product.category.id, sum(g.totalAmount) ");
		sb.append("from GoodsItem g ");
		sb.append("where g.intoGoods.id in (select i.id from IntoGoods i where i.createTime>=? and i.createTime<?) ");
		sb.append("group by g.product.category.id");
		
		return getHibernateTemplate().find(sb.toString(),
				new Object[] { beginDate, endDate });
	}
	
	
}
