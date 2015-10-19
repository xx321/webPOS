package com.maxwell.pos.service;

import java.util.List;
import java.util.Map;

import com.maxwell.pos.model.Income;
import com.maxwell.pos.model.Sell;
import com.maxwell.pos.vo.IncomeVO;

public interface IncomeManager {

	void add(Income income);
	
	Income get(IncomeVO incomeVO, List<Sell> sellList,
			Map<String, Object> session);
	
	Income getLastIncome();
}
