package com.maxwell.pos.dao;

import com.maxwell.pos.model.Income;

public interface IncomeDao extends SuperDao<Income, Integer> {

	Income getLastIncome();

}
