package com.maxwell.pos.dao.impl;

import org.springframework.stereotype.Component;

import com.maxwell.pos.dao.IncomeDao;
import com.maxwell.pos.model.Income;

@Component("incomeDao")
public class IncomeDaoImpl extends SuperDaoImpl<Income, Integer>
	implements IncomeDao {

	public Income getLastIncome() {
		String hql = "select max(id) from Income";
		Object id = queryObject(hql, null);
		if (id != null)
			return findById((Integer) id);
		return null;
	}

}
