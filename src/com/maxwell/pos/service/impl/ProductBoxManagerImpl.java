package com.maxwell.pos.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.maxwell.pos.dao.ProductBoxDao;
import com.maxwell.pos.model.ProductBox;
import com.maxwell.pos.service.ProductBoxManager;

@Component("productBoxManager")
public class ProductBoxManagerImpl implements ProductBoxManager {
	
	private ProductBoxDao productBoxDao;
	
	@Resource
	public void setProductBoxDao(ProductBoxDao productBoxDao) {
		this.productBoxDao = productBoxDao;
	}

	public ProductBox get(Integer productId) {
		return productBoxDao.findById(productId);
	}

	public void delete(ProductBox pBox) {
		productBoxDao.delete(pBox);
	}
	
}
