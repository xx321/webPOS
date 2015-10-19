package com.maxwell.pos.service;

import com.maxwell.pos.model.ProductBox;

public interface ProductBoxManager {

	ProductBox get(Integer productId);

	void delete(ProductBox pBox);

}
