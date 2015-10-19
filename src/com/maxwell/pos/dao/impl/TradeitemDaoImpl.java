package com.maxwell.pos.dao.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.maxwell.pos.comparator.AmountScaleComparator;
import com.maxwell.pos.comparator.ItemScaleComparator;
import com.maxwell.pos.dao.TradeitemDao;
import com.maxwell.pos.model.Tradeitem;
import com.maxwell.pos.util.DateUtil;
import com.maxwell.pos.util.NumberUtil;
import com.maxwell.pos.vo.TradeitemVO;
import com.maxwell.pos.vo.base.DataGrid;

@Component("tradeitemDao")
public class TradeitemDaoImpl extends SuperDaoImpl<Tradeitem, Integer> implements TradeitemDao {
	
	//銷售統計用 datagrid
	public DataGrid getDataGrid(TradeitemVO tradeitemVO, Integer rows,
			Integer page, String sort, String order) {
		DataGrid datagrid = new DataGrid();
		
		StringBuffer sb = new StringBuffer();
		List<Object> values = new ArrayList<Object>();
		
		sb.append("from Tradeitem ti where 1=1 ");
		addWhere(tradeitemVO, sb, values);
		sb.append(" group by ti.product.id ");
		
		
		List<?> count = find("select count(*) " + sb.toString(), values, null, null);
		Long total = (long) count.size();
		datagrid.setTotal(total);
		
		if(!"amountScaleStr".equals(sort) && !"itemScaleStr".equals(sort))
			addOrderBy(sb, sort, order);
		
		List<?> list = find("select ti.product.category.name , ti.product.name , sum(ti.quantity) , sum(ti.total) "+sb.toString(), values, rows, page);
		List<TradeitemVO> tradeitemVOs = new ArrayList<TradeitemVO>();
		
		int totalItem = 0;
		double totalAmount = 0;
		if (tradeitemVO.getTotalItem() != null)
			totalItem = tradeitemVO.getTotalItem();
		if (tradeitemVO.getTotalAmount() != null)
			totalAmount = tradeitemVO.getTotalAmount();
		
		
		for(int i=0; i<list.size(); i++) {
			
			Object[] os = (Object[]) list.get(i);

			tradeitemVO = new TradeitemVO();
			tradeitemVO.setCategoryName((String) os[0]);
			tradeitemVO.setProductName((String) os[1]);
			tradeitemVO.setQuantity(((Long) os[2]).intValue());
			tradeitemVO.setTotal(NumberUtil.formatDouble((Double) os[3]));
			//計算銷售數量比重
			if(totalItem !=0) {
				Double itemScale = Math.round(tradeitemVO.getQuantity()*10000/totalItem)/100.0;
				tradeitemVO.setItemScale(itemScale);
			}
			//計算銷售金額比重
			if(totalAmount != 0) {
				Double amountScale =  Math.round(tradeitemVO.getTotal()*10000/totalAmount)/100.0;
				tradeitemVO.setAmountScale(amountScale);
			}
				
			tradeitemVOs.add(tradeitemVO);
		}
		
		//使用 Comparator 添加排序功能 (因為Query 查詢的字段裡 沒有scale)
		if("amountScaleStr".equals(sort)) {
			Collections.sort(tradeitemVOs, new AmountScaleComparator());
			if("desc".equals(order))
				Collections.reverse(tradeitemVOs);
		} else if("itemScaleStr".equals(sort)) {
			Collections.sort(tradeitemVOs, new ItemScaleComparator());
			if("desc".equals(order))
				Collections.reverse(tradeitemVOs);
		}
		
		datagrid.setRows(tradeitemVOs);
		
		return datagrid;
	}
	
	//一般用 datagrid
	public DataGrid getDataGrid2(TradeitemVO tradeitemVO, Integer rows,
			Integer page, String sort, String order) {
		DataGrid datagrid = new DataGrid();
		
		StringBuffer sb = new StringBuffer();
		List<Object> values = new ArrayList<Object>();
		
		sb.append("from Tradeitem ti where 1=1 ");
		addWhere(tradeitemVO, sb, values);
		
		Long total = (Long) queryObject("select count(*) " + sb.toString(), values);
		datagrid.setTotal(total);
		
		super.addOrderBy(sb, sort, order);
		
		@SuppressWarnings("unchecked")
		List<Tradeitem> tradeitems = (List<Tradeitem>) find(sb.toString(), values, rows, page);
		
		List<TradeitemVO> tradeitemVOs = new ArrayList<TradeitemVO>();
		
		for (Tradeitem tradeitem : tradeitems) {
			tradeitemVO = new TradeitemVO();
			BeanUtils.copyProperties(tradeitem, tradeitemVO);
			tradeitemVO.setProductName(tradeitem.getProduct().getName());
			tradeitemVO.setUnit(tradeitem.getProduct().getUnit());
			
			tradeitemVOs.add(tradeitemVO);
		}
		
		datagrid.setRows(tradeitemVOs);
		
		return datagrid;
	}
	
	//增加查詢功能 : 添加 where 條件  方法。(TradeitemVO 專用)    ★ addWhere 方法裡  兩個判斷 只能有一個成立。(如果有第三項判斷 ，另外再視情況)
	private void addWhere(TradeitemVO tradeitemVO, StringBuffer sb, List<Object> values) {
		
		//這裡的狀態 為 trade 的 status 如果為0 ， 直接 返回。 不再做其它的Where 條件判斷。
		if (tradeitemVO.getStatus() != null && tradeitemVO.getStatus() ==0) {
			sb.append(" and ti.trade.id in (select t.id from Trade t where t.status=?) ");
			values.add(tradeitemVO.getStatus());
			return;
		}
		
		if (tradeitemVO.getCreatetimeStart() != null && tradeitemVO.getCreatetimeEnd() != null) {
			sb.append(" and ti.trade.id in (select t.id from Trade t where t.status=1 and t.createTime >= ? and t.createTime <= ?) ");
			values.add(DateUtil.toDate(tradeitemVO.getCreatetimeStart()));
			values.add(DateUtil.addDay(DateUtil.toDate(tradeitemVO.getCreatetimeEnd()), 1));
		} 
		
		if (tradeitemVO.getTradeId() != null) {
			sb.append(" and ti.trade.id =? ");
			values.add(tradeitemVO.getTradeId());
		}
	}
	
	//增加排序功能 : 添加 order by 'xxx' (desc OR asc) 語句 
	public void addOrderBy(StringBuffer sb, String sort, String order) {
		if (sort != null && order != null) {
			sb.append(" order by ");
			
			if("categoryName".equals(sort))
				sb.append("1");
			else if("productName".equals(sort))
				sb.append("2");
			else if("quantity".equals(sort))
				sb.append("3");
			else if("total".equals(sort))
				sb.append("4");
			
			sb.append(" ");
			sb.append(order);
		}
	}

	@SuppressWarnings("unchecked")
	public List<Tradeitem> findByTradeId(Integer id) {
		final String hql = "from Tradeitem ti where ti.trade.id=? ";
		List<Object> values = new ArrayList<Object>();
		values.add(id);
		List<Tradeitem> tradeitems = (List<Tradeitem>) find(hql, values, null, null);
		return tradeitems;
	}

}

