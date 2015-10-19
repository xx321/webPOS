package com.maxwell.pos.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.maxwell.pos.dao.ProductDao;
import com.maxwell.pos.dao.ProductItemDao;
import com.maxwell.pos.model.Product;
import com.maxwell.pos.service.ProductManager;
import com.maxwell.pos.vo.ProductVO;
import com.maxwell.pos.vo.base.DataGrid;

@Component("productManager")
public class ProductManagerImpl implements ProductManager {
	
	private ProductDao productDao;
	private ProductItemDao productItemDao;
	
	@Resource
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}
	@Resource
	public void setProductItemDao(ProductItemDao productItemDao) {
		this.productItemDao = productItemDao;
	}

	public List<Product> list() {
		return productDao.findAll();
	}

	public Product get(Integer id) {
		return productDao.findById(id);
	}

	public void update(Product product) {
		productDao.update(product);
	}

	public String delete(Integer id) {
		Product product = productDao.findById(id);
		if (product == null)
			return "您所要刪除的商品不存在 !!";
		if (product.getTradeitems().size() >0 )
			return "您所要刪除的商品已經在使用 !! 建議您將商品狀態設定為\"關閉 \"。";
		if (product != null)
			productDao.delete(product);
		return "商品已經成功刪除 !! ";
	}

	public void add(Product product) {
		productDao.save(product);
	}

	public List<Product> getListByCategoryId(int categoryId) {
		return productDao.getProductsByCategoryId(categoryId);
	}

	public DataGrid getDataGrid(ProductVO productVO, Integer rows,
			Integer page, String sort, String order) {
		return productDao.getDataGrid(productVO, rows, page, sort, order);
	}

	public String delete(String ids) {
		Product product;
		String message = "";
		if (ids != null) {
			for (String id : ids.split(",")) {
				
				product = productDao.findById(new Integer(id.trim()));
				if (product == null) {
					message = "您所要刪除的商品不存在 !!";
					continue;
				}
				if (product.getTradeitems().size() > 0 || product.getGoodsItems().size() > 0 
						|| product.getSellitems().size() > 0 || product.getProductItems().size() > 0
						|| product.getProductBox() != null) {
					message = "您所要刪除的商品已經在使用 !! 建議您將商品狀態設定為\"關閉 \"。";
					continue;
				}
				if (product.getType() == 2) {
					productItemDao.deleteByproductId(product.getId());
				}
				productDao.delete(product);
				message = "商品已經成功刪除 !!";
			}
		}
		return message;
	}
//	public List<Product> stockList() {
//		
//		return productDao.findByType(new Integer(1));
//	}
	public Product findByBarcode(String barcodeNumber) {
		return productDao.findByBarcode(barcodeNumber);
	}
	public List<Product> findListByBarcodeNumber(String barcodeNumber) {
		return productDao.findListByBarcodeNumber(barcodeNumber);
	}

}
