package com.maxwell.pos.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.maxwell.pos.dao.ProductDao;
import com.maxwell.pos.dao.TradeDao;
import com.maxwell.pos.dao.TradeitemDao;
import com.maxwell.pos.model.Product;
import com.maxwell.pos.model.ProductItem;
import com.maxwell.pos.model.Trade;
import com.maxwell.pos.model.Tradeitem;
import com.maxwell.pos.service.TradeManager;
import com.maxwell.pos.vo.TradeVO;
import com.maxwell.pos.vo.base.DataGrid;

@Component("tradeManager")
public class TradeManagerImpl implements TradeManager {

	private TradeDao tradeDao;
	private TradeitemDao tradeitemDao;
	private ProductDao productDao;
	
	@Resource
	public void setTradeDao(TradeDao tradeDao) {
		this.tradeDao = tradeDao;
	}
	@Resource
	public void setTradeitemDao(TradeitemDao tradeitemDao) {
		this.tradeitemDao = tradeitemDao;
	}

	@Resource
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}
	
	public void add(Trade trade) {
		tradeDao.save(trade);
	}

//	public List<Trade> getListBy(Date beginDate, Date endDate) {
//		return tradeDao.getTradeBy(beginDate,endDate);
//	}

	public DataGrid getDataGrid(TradeVO tradeVO, Integer rows, Integer page,
			String sort, String order) {
		
		return tradeDao.getDataGrid(tradeVO, rows, page, sort, order);
	}
	
	//交易作廢功能 
	public String updateInvalid(String ids) {
		Trade trade = null;
		String message = "";
		if (ids != null) {
			for (String id : ids.split(",")) {
				trade = tradeDao.findById(new Integer(id.trim()));
				if (trade == null) {
					message = "您所要處理的訊息不存在 !!";
					continue;
				}
				trade.setStatus(new Integer(2)); //當交易 狀態 設定為 2 時 : 代表交易作廢
				
				updateSales(trade);  //還原 交易的商品庫存數。
				
				tradeDao.update(trade);
				message = "完成作廢交易操作 !!";
			}
		}
		return message;
	}
	
	
	//作廢交易，還原 出售的商品 庫存數。
	private void updateSales(Trade trade) {
		List<Tradeitem> tradeitems = tradeitemDao.findByTradeId(trade.getId());
		Product product = null;
		for (Tradeitem item : tradeitems) {
			product = productDao.findById(item.getProduct().getId());
			
			caseType(product,item.getQuantity());  //判斷商品 庫存性質
			
		}
		tradeitems = null;
		product = null;
	}
	private void caseType(Product product, Integer quantity) {  //根據商品 庫存性質  做庫存還原。 1 : 庫存商品    2 : 組合商品   其它 : 默認為非庫存商品(系統構思為0時)
		
		switch (product.getType()) {
		case 1 :
			product.setStockNumber(product.getStockNumber() + quantity);
			productDao.update(product);
			break;
		case 2 : 
			for (ProductItem pItem : product.getProductItems()) {
				caseType(pItem.getItem(),pItem.getQuantity()*quantity);
				break;
			}
			default :
				break;
		}
	}

}
