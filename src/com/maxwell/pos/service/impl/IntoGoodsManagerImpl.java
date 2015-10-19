package com.maxwell.pos.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.maxwell.pos.dao.GoodsItemDao;
import com.maxwell.pos.dao.IntoGoodsDao;
import com.maxwell.pos.dao.ProductDao;
import com.maxwell.pos.dao.SupplierDao;
import com.maxwell.pos.model.GoodsItem;
import com.maxwell.pos.model.IntoGoods;
import com.maxwell.pos.model.Product;
import com.maxwell.pos.model.User;
import com.maxwell.pos.service.IntoGoodsManager;
import com.maxwell.pos.util.DateUtil;
import com.maxwell.pos.vo.IntoGoodsVO;
import com.maxwell.pos.vo.base.DataGrid;

@Component("intoGoodsManager")
public class IntoGoodsManagerImpl implements IntoGoodsManager {
	
	private IntoGoodsDao intoGoodsDao;
	private SupplierDao supplierDao;
	private ProductDao productDao;
	private GoodsItemDao goodsItemDao;
	
	@Resource
	public void setIntoGoodsDao(IntoGoodsDao intoGoodsDao) {
		this.intoGoodsDao = intoGoodsDao;
	}
	@Resource
	public void setSupplierDao(SupplierDao supplierDao) {
		this.supplierDao = supplierDao;
	}
	@Resource
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}
	@Resource
	public void setGoodsItemDao(GoodsItemDao goodsItemDao) {
		this.goodsItemDao = goodsItemDao;
	}
	public void add(IntoGoods intoGoods) {
		intoGoodsDao.save(intoGoods);
	}

	public DataGrid getDataGrid(IntoGoodsVO intoGoodsVO, Integer rows,
			Integer page, String sort, String order) {
		
		return intoGoodsDao.getDataGrid(intoGoodsVO, rows, page, sort, order);
	}
	
	public IntoGoods findBy(Map<String, Object> session, IntoGoodsVO intoGoodsVO) {
		
		IntoGoods intoGoods = new IntoGoods();
		BeanUtils.copyProperties(intoGoodsVO, intoGoods);
		intoGoods.setUser((User) session.get("user"));
		if (intoGoodsVO.getSupplierId() != null)
			intoGoods.setSupplier(supplierDao.findById(intoGoodsVO.getSupplierId()));
		intoGoods.setTotal(new Double(0.00));
		intoGoods.setCreateTime(DateUtil.toDate(intoGoodsVO.getCreateTimeStr()));
		if (intoGoods.getStatus() == 1) {
			intoGoods.setPaymentPersonnel((User) session.get("user"));
			intoGoods.setPaymentTime(new java.util.Date());
		}
		
		return intoGoods;
	}
	
	public String purchase(Map<Integer, GoodsItem> goodsItems, IntoGoods intoGoods) {
		if (intoGoods.getTax() == null)
			intoGoods.setTax(new Double(0.00));
		
		String message = "";
		Product product = null;
		for (GoodsItem item : goodsItems.values()) {
			if (item.getQuantity() ==0 || item.getPurchasePrice()==0)
				continue;
			//商品庫存，連動調整(庫存數量，進貨成本)
			product = productDao.findById(item.getProduct().getId());
			if (intoGoods.getClosed() == 1 && product.getType() == 1) {//如果為進貨單 而且商品類型為"庫存商品"時，則庫存數量要連動調整。
				Integer stockNumber = product.getStockNumber() + item.getQuantity();
				if (stockNumber <0) {
					StringBuffer buffer = new StringBuffer();
					buffer.append("商品 : ");
					buffer.append(product.getName());
					buffer.append(" , 採購的數量不足 ! <br />無法滿足庫存數 !");
	
					message = buffer.toString();
					return message;
				}
				product.setStockNumber(stockNumber); 
			}
			
			product.setPurchasePrice(item.getPurchasePrice());
			
			//進貨單設定，(進貨總金額)
			if (item.getTotal() != null) {
				item.setTotalAmount(item.getTotal() * (1+intoGoods.getTax()));
				intoGoods.setTotal(intoGoods.getTotal() + item.getTotal());
			}
			
			item.setProduct(product);
			item.setIntoGoods(intoGoods);//很重要，少了這一個步驟，沒辦法作儲存動作。
			intoGoods.getGoodsItems().add(item);
		
		}
		
		intoGoods.setTotal(Math.rint(intoGoods.getTotal()));
		intoGoods.setSalesTax(Math.rint(intoGoods.getTotal() * intoGoods.getTax()));
		intoGoods.setTotalAmount(Math.rint(intoGoods.getTotal() * (1+intoGoods.getTax())));
		
		return message;
	}
	
	public void updatePurchase(IntoGoods intoGoods) {
		if (intoGoods.getClosed() == 0)
			return;
		List<GoodsItem> goodsItems = goodsItemDao.findByIntoGoodsId(intoGoods.getId());
		Product product = null;
		for (GoodsItem item : goodsItems) {
			product = productDao.findById(item.getProduct().getId());
			if (product.getType() == 1) {
				product.setStockNumber(product.getStockNumber() - item.getQuantity());
				productDao.update(product);
			}
		}
		goodsItems = null;
		product = null;
	}
	public void deletePurchase(IntoGoods intoGoods) {
		if (intoGoods.getClosed() == 0)
			return;
		List<GoodsItem> goodsItems = goodsItemDao.findByIntoGoodsId(intoGoods.getId());
		Product product = null;
		for (GoodsItem item : goodsItems) {
			product = productDao.findById(item.getProduct().getId());
			if (product.getType() == 1) {
				product.setStockNumber(product.getStockNumber() + item.getQuantity());
				productDao.update(product);
			}
		}
	}
	
	public IntoGoods get(Integer id) {
		return intoGoodsDao.findById(id);
	}
	public void update(IntoGoods intoGoods) {
		intoGoodsDao.update(intoGoods);
	}
	public String delete(String ids) {
		IntoGoods intoGoods = null;
		String message = "";
		if (ids != null) {
			for (String id : ids.split(",")) {
				intoGoods = intoGoodsDao.findById(new Integer(id.trim()));
				if (intoGoods == null) {
					message =  "您所要刪除的訊息不存在 !!";
					continue;
				}
//				if (intoGoodsDao.exists(intoGoods.getId())) {  這裡之後要改為 intoGoods.getClosed == 1  則不能刪除 已經付款之類的。 .0...
//					message = "您所要刪除的訊息已經在使用 !!"。";
//					continue;
//				}
				intoGoodsDao.delete(intoGoods);
				message = "成功刪除 !!";
			}
		}
		return message;
	}
	
	public List<IntoGoods> getListByIds(String idsStr) {
		List<Integer> ids = new ArrayList<Integer>();
		if (idsStr != null) {
			for (String id : idsStr.split(",")) {
				ids.add(new Integer(id.trim()));
			}
		}
		return intoGoodsDao.findListByIds(ids);
	}
	
	public void copyProperties(IntoGoods intoGoods, IntoGoodsVO intoGoodsVO) {
		
		BeanUtils.copyProperties(intoGoods, intoGoodsVO);
		intoGoodsVO.setUserName(intoGoods.getUser().getUsername());
		intoGoodsVO.setSupplierName(intoGoods.getSupplier().getName());
		if (intoGoods.getPaymentPersonnel() != null)
			intoGoodsVO.setPaymentPersonnelName(intoGoods.getPaymentPersonnel().getUsername());
		intoGoodsVO.setCreateTimeStr(DateUtil.toCharForDataGrid(intoGoods.getCreateTime()));
		intoGoodsVO.setPaymentTimeStr(DateUtil.toCharForDataGrid(intoGoods.getPaymentTime()));
		
	}
	public void copyProperties(Collection<IntoGoods> intoGoodses,
			Collection<IntoGoodsVO> intoGoodsVOs) {
		
		Iterator<IntoGoods> it = intoGoodses.iterator();
		while(it.hasNext()) {
			IntoGoodsVO intoGoodsVO = new IntoGoodsVO();
			this.copyProperties(it.next(), intoGoodsVO);
			intoGoodsVOs.add(intoGoodsVO);
		}
	}
}
