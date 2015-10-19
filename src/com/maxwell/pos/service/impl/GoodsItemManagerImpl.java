package com.maxwell.pos.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.maxwell.pos.dao.GoodsItemDao;
import com.maxwell.pos.dao.ProductDao;
import com.maxwell.pos.model.GoodsItem;
import com.maxwell.pos.model.Product;
import com.maxwell.pos.service.GoodsItemManager;
import com.maxwell.pos.vo.GoodsItemVO;
import com.maxwell.pos.vo.base.DataGrid;

@Component("goodsItemManager")
public class GoodsItemManagerImpl implements GoodsItemManager {

	private GoodsItemDao goodsItemDao;
	private ProductDao productDao;

	@Resource
	public void setGoodsItemDao(GoodsItemDao goodsItemDao) {
		this.goodsItemDao = goodsItemDao;
	}

	@Resource
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	public void add(GoodsItem goodsItem) {
		goodsItemDao.save(goodsItem);
	}

	public void setGoodsItemsBy(String ids, Map<Integer, GoodsItem> goodsItems) {
		GoodsItem goodsItem = null;
		if (ids == null || "".equals(ids.trim()))
			return;
		for (String productId : ids.split(",")) {
			goodsItem = goodsItems.get(new Integer(productId.trim()));
			if (goodsItem != null)
				continue;

			Product product = productDao.findById(new Integer(productId.trim()));
			if (product == null)
				continue;

			goodsItem = new GoodsItem();
			goodsItem.setProduct(product);
			goodsItem.setQuantity(new Integer(0));
			goodsItem.setPurchasePrice(product.getPurchasePrice());
//			goodsItem.setTotal(NumberUtil.formatDouble(goodsItem.getQuantity()* goodsItem.getPurchasePrice()));
			goodsItem.setTotal(new Double(0.00));

			goodsItems.put(new Integer(productId.trim()), goodsItem);
		}

	}

	public void editGoodsItemsBy(GoodsItemVO goodsItemVO, Map<Integer, GoodsItem> goodsItems) {
		GoodsItem goodsItem = goodsItems.get(goodsItemVO.getProductId());
		if (goodsItem == null)
			return;
		goodsItem.setQuantity(goodsItemVO.getQuantity());
		goodsItem.setPurchasePrice(goodsItemVO.getPurchasePrice());
		goodsItem.setTotal(Math.rint(goodsItemVO.getTotal()));	
	}
	
	public void deleteGoodsItemBy(String ids, Map<Integer, GoodsItem> goodsItems) {
		if (ids == null)
			return;
		for (String productId : ids.split(",")) {
			goodsItems.remove(new Integer(productId.trim()));		
		}
	}

	public DataGrid getDataGrid(GoodsItemVO goodsItemVO, Integer rows,
			Integer page, String sort, String order) {
		
		return goodsItemDao.getDataGrid(goodsItemVO, rows, page, sort, order);
	}

	public void deleteByIntoGoodsId(Integer id) {
		goodsItemDao.deleteByIntoGoodsId(id);
	}

}
