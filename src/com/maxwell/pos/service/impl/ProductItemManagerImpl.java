package com.maxwell.pos.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.maxwell.pos.dao.ProductDao;
import com.maxwell.pos.dao.ProductItemDao;
import com.maxwell.pos.model.Product;
import com.maxwell.pos.model.ProductItem;
import com.maxwell.pos.service.ProductItemManager;
import com.maxwell.pos.vo.ProductItemVO;

@Component("productItemManager")
public class ProductItemManagerImpl implements ProductItemManager {
	
	private ProductItemDao productItemDao;
	private ProductDao productDao;
	
	@Resource
	public void setProductItemDao(ProductItemDao productItemDao) {
		this.productItemDao = productItemDao;
	}
	@Resource
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	public void setProductItemsBy(String ids, Map<Integer, ProductItem> productItems) {
		ProductItem productItem = null;
		if (ids == null || "".equals(ids.trim()))
			return;
		for (String productId : ids.split(",")) {
			productItem = productItems.get(new Integer(productId.trim()));
			if (productItem != null)
				continue;
			
			Product product = productDao.findById(new Integer(productId.trim()));
			if (product == null)
				continue;
			
			productItem = new ProductItem();
			productItem.setItem(product);
			productItem.setQuantity(new Integer(0));
			
			productItems.put(new Integer(productId.trim()), productItem);
		}
		
	}
	public void editProductItemsBy(ProductItemVO productItemVO, Map<Integer, ProductItem> productItems) {
		ProductItem productItem = productItems.get(productItemVO.getProductId());
		if (productItem == null)
			return;
		productItem.setQuantity(productItemVO.getQuantity());
		
	}
	public void deleteProductItemBy(String ids, Map<Integer, ProductItem> productItems) {
		if (ids == null)
			return;
		for (String productId : ids.split(",")) {
			productItems.remove(new Integer(productId.trim()));
		}
	}
	public String setProductItemsBy(Map<Integer, ProductItem> productItems, Product product) {
		String message = "";
		for (ProductItem item : productItems.values()) {
			if (item.getQuantity() <=0)
				continue;
			item.setProduct(product);
			product.getProductItems().add(item);
		}
		
		if (product.getProductItems().size() <=0) {
			message = "組合商品設定失敗 !";
			return message;
		}
		productItemDao.deleteByproductId(product.getId());
		return message;
		
	}

}
