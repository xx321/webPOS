package com.maxwell.pos.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.maxwell.pos.dao.ProductDao;
import com.maxwell.pos.model.Product;
import com.maxwell.pos.model.ProductBox;
import com.maxwell.pos.vo.ProductVO;
import com.maxwell.pos.vo.base.DataGrid;

@Component("productDao")
public class ProductDaoImpl extends SuperDaoImpl<Product, Integer> implements
		ProductDao {

	@SuppressWarnings("unchecked")
	public List<Product> getProductsByCategoryId(Integer categoryId) {
		String hql = "from Product as p where p.status=1 and p.category.id=? order by p.displayOrder";
		return getHibernateTemplate().find(hql, new Object[] { categoryId });
	}

	@SuppressWarnings("unchecked")
	public List<Product> findForDeleteBy(Integer categoryId) {
		String hql = "from Product as p where p.category.id=?";
		return getHibernateTemplate().find(hql, new Object[] { categoryId });
	}
	
	public DataGrid getDataGrid(ProductVO productVO, Integer rows,
			Integer page, String sort, String order) {
		DataGrid datagrid = new DataGrid();
		
		StringBuffer sb = new StringBuffer();
		List<Object> values = new ArrayList<Object>();
		
		sb.append("from Product p where 1=1 ");
		addWhere(productVO, sb, values);
		
		Long total = (Long) queryObject("select count(*) " + sb.toString(), values);
		datagrid.setTotal(total);
		
		
		if (sort != null)
			if ("categoryName".equals(sort))
				sort = "category";
		
		addOrderBy(sb, sort, order);

		@SuppressWarnings("unchecked")
		List<Product> products = (List<Product>) find(sb.toString(), values, rows, page);
		
		List<ProductVO> ProductVOs = new ArrayList<ProductVO>();
		
		for (Product product : products) {
			productVO = new ProductVO();
			BeanUtils.copyProperties(product, productVO);	
			productVO.setCategoryName(product.getCategory().getName());
			
			ProductBox pBox = product.getProductBox();
			if (pBox !=null) {
				productVO.setBoxId(pBox.getBox().getId());
				productVO.setBoxName(pBox.getBox().getName());
				productVO.setBoxUnit(pBox.getBox().getUnit());
			}
			
			ProductVOs.add(productVO);
		}
		datagrid.setRows(ProductVOs);
		
		return datagrid;
	}
	//增加查詢功能 : 添加 where 條件  方法。(ProductVO 專用) 註: VO = value Object
	private void addWhere(ProductVO productVO, StringBuffer sb,
			List<Object> values) {
			
		if (productVO.getName() != null && !productVO.getName().trim().equals("")) {
			sb.append(" and p.name like ? ");
			values.add("%%" + productVO.getName().trim() + "%%");
		}	
		
		if (productVO.getCategoryId() != null) {
			sb.append(" and p.category.id=? ");
			values.add(productVO.getCategoryId());
		}
		if (productVO.getType() != null) {
			sb.append(" and p.type=? ");
			values.add(productVO.getType());
		}
		
	}

	public Product findByBarcode(String barcodeNumber) {
		String hql = "from Product as p where p.barcodeNumber=? ";
		List<?> products = getHibernateTemplate().find(hql, new Object[] { barcodeNumber });
		if (products.size() >0)
			return (Product) products.get(0);
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Product> findListByBarcodeNumber(String barcodeNumber) {
		String hql = "from Product as p where p.barcodeNumber=? ";
		return getHibernateTemplate().find(hql, new Object[] { barcodeNumber });
	}

//	@SuppressWarnings("unchecked")
//	public List<Product> findByType(Integer type) {
//		String hql = "from Product as p where p.type=? order by p.displayOrder";
//		return getHibernateTemplate().find(hql, new Object[] { type });
//	}
	
}
