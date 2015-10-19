package com.maxwell.pos.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.maxwell.pos.dao.TradeitemDao;
import com.maxwell.pos.service.TradeitemManager;
import com.maxwell.pos.vo.TradeitemVO;
import com.maxwell.pos.vo.base.DataGrid;
 
@Component("tradeitemManager")
public class TradeitemManagerImpl implements TradeitemManager {

	private TradeitemDao tradeitemDao;

	@Resource
	public void setTradeitemDao(TradeitemDao tradeitemDao) {
		this.tradeitemDao = tradeitemDao;
	}

	public DataGrid getDataGrid(TradeitemVO tradeitemVO, Integer rows,
			Integer page, String sort, String order) {
		
		return tradeitemDao.getDataGrid(tradeitemVO, rows, page, sort, order);
	}

	public DataGrid getDataGrid2(TradeitemVO tradeitemVO, Integer rows,
			Integer page, String sort, String order) {
		
		return tradeitemDao.getDataGrid2(tradeitemVO, rows, page, sort, order);
	}

}
