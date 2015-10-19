package com.maxwell.pos.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.maxwell.pos.dao.CategoryDao;
import com.maxwell.pos.dao.GoodsItemDao;
import com.maxwell.pos.dao.SellDao;
import com.maxwell.pos.dao.SpendingItemDao;
import com.maxwell.pos.dao.SubjectDao;
import com.maxwell.pos.dao.TradeDao;
import com.maxwell.pos.model.Category;
import com.maxwell.pos.model.Subject;
import com.maxwell.pos.service.ReportManager;
import com.maxwell.pos.vo.reportVO.CostVO;
import com.maxwell.pos.vo.reportVO.FeeVO;
import com.maxwell.pos.vo.reportVO.IncomeVO;


@Component("reportManager")
public class ReportManagerImpl implements ReportManager {

	private TradeDao tradeDao;
	private SellDao sellDao;
	
	private CategoryDao categoryDao;
	private GoodsItemDao goodsItemDao;
	
	private SubjectDao subjectDao;
	private SpendingItemDao spendingItemDao;
	
	@Resource
	public void setTradeDao(TradeDao tradeDao) {
		this.tradeDao = tradeDao;
	}
	@Resource
	public void setSellDao(SellDao sellDao) {
		this.sellDao = sellDao;
	}
	@Resource
	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}
	@Resource
	public void setGoodsItemDao(GoodsItemDao goodsItemDao) {
		this.goodsItemDao = goodsItemDao;
	}
	@Resource
	public void setSubjectDao(SubjectDao subjectDao) {
		this.subjectDao = subjectDao;
	}
	@Resource
	public void setSpendingItemDao(SpendingItemDao spendingItemDao) {
		this.spendingItemDao = spendingItemDao;
	}
	
	public IncomeVO getIncomeBy(Date beginDate, Date endDate) {
		IncomeVO incomeVO = new IncomeVO();
		Double tradeInvoicesIncome = tradeDao.getInvoicesIncome(beginDate, endDate);
		Double sellInvoicesIncome = sellDao.getInvoicesIncome(beginDate, endDate);
		incomeVO.setInvoicesIncome(Math.rint(tradeInvoicesIncome + sellInvoicesIncome));
		Double totalIncome = tradeDao.getCashIncome(beginDate, endDate);
		incomeVO.setCashIncome(Math.rint(totalIncome));
		incomeVO.setTotalIncome(incomeVO.getInvoicesIncome()+incomeVO.getCashIncome());
		return incomeVO;
	}
	
	public List<CostVO> getCostsBy(Date beginDate, Date endDate) {

		Map<Integer, CostVO> costMap = new HashMap<Integer, CostVO>();

		List<Category> categorys = categoryDao.findAll();
		
		for (Category c : categorys) {
			if (c.getId() >=99)   //category.id >=99  的類別 都屬於 進貨成本 (商品用類別
				continue;
			CostVO costVO = new CostVO();
			costVO.setName(c.getName());
			costVO.setSpending(0.0);
			
			costMap.put(c.getId(), costVO);
		}
		
		CostVO goodsCostVO = new CostVO();
		goodsCostVO.setName("進貨成本");
		goodsCostVO.setSpending(0.0);
		
		costMap.put(new Integer(0), goodsCostVO);
		
		List<Object[]> results = goodsItemDao.getCostsBy(beginDate, endDate);
		for (Object[] o :results) {
			CostVO costVO = costMap.get(o[0]);
			if (o[1] == null)
				continue;
			if (costVO == null) 
				costVO = costMap.get(new Integer(0));
			costVO.setSpending(Math.rint(costVO.getSpending() + (Double) o[1]));

		}

		List<CostVO> costVOs = new ArrayList<CostVO>();
		costVOs.addAll(costMap.values());
		return costVOs;
	}
	
	public List<FeeVO> getFeesBy(Date beginDate, Date endDate) {

		Map<Integer, FeeVO> feeMap = new HashMap<Integer, FeeVO>();
		
		List<Subject> subjects = subjectDao.findAll();
		for (Subject s : subjects) {
			FeeVO feeVO = new FeeVO();
			feeVO.setName(s.getName());
			feeVO.setSpending(0.0);
			
			feeMap.put(s.getId(), feeVO);
		}
		
		List<Object[]> results = spendingItemDao.getFeesBy(beginDate, endDate);
		for (Object[] o :results) {			//返回的 results  o[0] = s.subject.id , o[1] = sum(s.totalAmount) : s = SpendingItem
			FeeVO feeVO = feeMap.get(o[0]);
			if (o[1] != null && feeVO != null)
				feeVO.setSpending(Math.rint(feeVO.getSpending() + (Double) o[1]));
		}
		
		List<FeeVO> feeVOs = new ArrayList<FeeVO>();
		feeVOs.addAll(feeMap.values());
		return feeVOs; 
	}
	

}
