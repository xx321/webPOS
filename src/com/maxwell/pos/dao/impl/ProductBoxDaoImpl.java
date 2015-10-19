package com.maxwell.pos.dao.impl;

import org.springframework.stereotype.Component;

import com.maxwell.pos.dao.ProductBoxDao;
import com.maxwell.pos.model.ProductBox;


@Component("productBoxDao")
public class ProductBoxDaoImpl extends SuperDaoImpl<ProductBox, Integer> implements
		ProductBoxDao {

	
}
