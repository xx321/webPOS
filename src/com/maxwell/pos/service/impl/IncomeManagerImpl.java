package com.maxwell.pos.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.maxwell.pos.dao.IncomeDao;
import com.maxwell.pos.model.Income;
import com.maxwell.pos.model.Sell;
import com.maxwell.pos.model.User;
import com.maxwell.pos.service.IncomeManager;
import com.maxwell.pos.util.Arith;
import com.maxwell.pos.util.DateUtil;
import com.maxwell.pos.vo.IncomeVO;

@Component("incomeManager")
public class IncomeManagerImpl implements IncomeManager {
	
	private IncomeDao incomeDao;
	
	@Resource
	public void setIncomeDao(IncomeDao incomeDao) {
		this.incomeDao = incomeDao;
	}

	public void add(Income income) {
		incomeDao.save(income);
	}
	//根據傳進來的參數 ， 設定 Income，返回。
	public Income get(IncomeVO incomeVO, List<Sell> sellList,
			Map<String, Object> session) {
		Sell sell = null;
		Income income = new Income();
		BeanUtils.copyProperties(incomeVO, income);
		
		Double totalAmount = countTotalAmount(sellList);
		income.setIncomeAmount(Arith.round(totalAmount,0));
		income.setIncomePersonnel((User) session.get("user"));
		income.setIncomeTime(DateUtil.toDate(incomeVO.getIncomeTimeStr()));
		income.setCheckDate(DateUtil.toDate(incomeVO.getCheckDateStr()));
		
		int length = sellList.size();
		for(int i=0; i<length; i++) {
			//銷貨單結清
			sell = sellList.get(i);
			sell.setStatus(new Integer(1));
			sell.setIncomePersonnel(income.getIncomePersonnel());
			sell.setIncomeTime(income.getIncomeTime());
			
			sell.setIncome(income);
			
			income.getSells().add(sell);
		}
		return income;
	}

	private Double countTotalAmount(List<Sell> sellList) {
		Double totalAmount = 0.00;
		for(Sell s : sellList) {
			totalAmount = totalAmount + s.getTotalAmount();
		}
		return totalAmount;
	}

	public Income getLastIncome() {
		return incomeDao.getLastIncome();
	}
	
	
}
