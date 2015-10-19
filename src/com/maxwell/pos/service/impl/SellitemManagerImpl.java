package com.maxwell.pos.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.maxwell.pos.dao.ProductDao;
import com.maxwell.pos.dao.SellitemDao;
import com.maxwell.pos.model.Product;
import com.maxwell.pos.model.Sellitem;
import com.maxwell.pos.service.SellitemManager;
import com.maxwell.pos.vo.SellitemVO;
import com.maxwell.pos.vo.base.DataGrid;

@Component("sellitemManager")
public class SellitemManagerImpl implements SellitemManager {
	
	private SellitemDao sellitemDao;
	private ProductDao productDao;
	
	@Resource
	public void setSellitemDao(SellitemDao sellitemDao) {
		this.sellitemDao = sellitemDao;
	}
	
	@Resource
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}
	public void setSellitemsBy(String ids, Map<Integer, Sellitem> sellitems) {
		Sellitem sellitem = null;
		if (ids == null || "".equals(ids.trim()))
			return;
		for (String productId : ids.split(",")) {
			sellitem = sellitems.get(new Integer(productId.trim()));
			if (sellitem != null)
				continue;
			
			Product product = productDao.findById(new Integer(productId.trim()));
			if (product == null)
				continue;
			
			sellitem = new Sellitem();
			sellitem.setProduct(product);
			sellitem.setQuantity(new Integer(0));
			sellitem.setPurchasePrice(product.getPrice());
			sellitem.setTotal(new Double(0.00));
			
			sellitems.put(new Integer(productId.trim()), sellitem);
		}
		
	}

	public void editSellitemsBy(SellitemVO sellitemVO, Map<Integer, Sellitem> sellitems) {
		Sellitem sellitem = sellitems.get(sellitemVO.getProductId());
		if (sellitem == null)
			return;
		sellitem.setQuantity(sellitemVO.getQuantity());
		sellitem.setPurchasePrice(sellitemVO.getPurchasePrice());
		sellitem.setTotal(Math.rint(sellitemVO.getTotal()));
	}

	public void deleteSellitemBy(String ids, Map<Integer, Sellitem> sellitems) {
		if (ids == null)
			return;
		for (String productId : ids.split(",")) {
			sellitems.remove(new Integer(productId.trim()));
		}
	}

	public DataGrid getDataGrid(SellitemVO sellitemVO, Integer rows,
			Integer page, String sort, String order) {
		
		return sellitemDao.getDataGrid(sellitemVO, rows, page, sort, order);
	}

	public void deleteBySellId(Integer id) {
		sellitemDao.deleteBySellId(id);
	}
	
	
}
