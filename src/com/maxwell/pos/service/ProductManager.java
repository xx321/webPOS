package com.maxwell.pos.service;

import java.util.List;

import com.maxwell.pos.model.Product;
import com.maxwell.pos.vo.ProductVO;
import com.maxwell.pos.vo.base.DataGrid;

public interface ProductManager {

	DataGrid getDataGrid(ProductVO productVO, Integer rows, Integer page,
			String sort, String order);

	void add(Product product);

	void update(Product oldProduct);

	String delete(String ids);

	Product get(Integer id);

//	List<Product> stockList();

	List<Product> getListByCategoryId(int i);

	Product findByBarcode(String barcodeNumber);

	List<Product> findListByBarcodeNumber(String barcodeNumber);

}
