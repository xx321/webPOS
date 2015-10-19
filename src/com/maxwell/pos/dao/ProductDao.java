package com.maxwell.pos.dao;

import java.util.List;

import com.maxwell.pos.model.Product;
import com.maxwell.pos.vo.ProductVO;
import com.maxwell.pos.vo.base.DataGrid;

public interface ProductDao extends SuperDao<Product, Integer> {
	
	public List<Product> getProductsByCategoryId(Integer categoryId);

	public List<Product> findForDeleteBy(Integer categoryId);
	
	public DataGrid getDataGrid(ProductVO productVO, Integer rows,
			Integer page, String sort, String order);

	public Product findByBarcode(String barcodeNumber);

	public List<Product> findListByBarcodeNumber(String barcodeNumber);

//	public List<Product> findByType(Integer type);

}
