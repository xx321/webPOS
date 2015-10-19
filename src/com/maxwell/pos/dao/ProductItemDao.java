package com.maxwell.pos.dao;

import com.maxwell.pos.model.ProductItem;

public interface ProductItemDao extends SuperDao<ProductItem, Integer> {

	void deleteByproductId(Integer id);
	

}
