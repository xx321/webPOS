package com.maxwell.pos.service;

import java.util.Map;

import com.maxwell.pos.model.Product;
import com.maxwell.pos.model.ProductItem;
import com.maxwell.pos.vo.ProductItemVO;

public interface ProductItemManager {

	void setProductItemsBy(String ids, Map<Integer, ProductItem> productItems);

	void editProductItemsBy(ProductItemVO productItemVO,
			Map<Integer, ProductItem> productItems);

	void deleteProductItemBy(String ids, Map<Integer, ProductItem> productItems);

	String setProductItemsBy(Map<Integer, ProductItem> productItems,
			Product oldProduct);

}
