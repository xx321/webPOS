package com.maxwell.pos.service.impl;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.maxwell.pos.dao.CustomerDao;
import com.maxwell.pos.dao.ProductDao;
import com.maxwell.pos.dao.SellDao;
import com.maxwell.pos.dao.SellitemDao;
import com.maxwell.pos.model.Product;
import com.maxwell.pos.model.ProductBox;
import com.maxwell.pos.model.Sell;
import com.maxwell.pos.model.Sellitem;
import com.maxwell.pos.model.User;
import com.maxwell.pos.service.SellManager;
import com.maxwell.pos.util.DateUtil;
import com.maxwell.pos.vo.SellVO;
import com.maxwell.pos.vo.base.DataGrid;

@Component("sellManager")
public class SellManagerImpl implements SellManager {
	
	private SellDao sellDao;
	private CustomerDao customerDao;
	private ProductDao productDao;
	private SellitemDao sellitemDao;
	
	@Resource
	public void setSellDao(SellDao sellDao) {
		this.sellDao = sellDao;
	}
	
	@Resource
	public void setCutomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}
	
	@Resource
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}
	
	@Resource
	public void setSellitemDao(SellitemDao sellitemDao) {
		this.sellitemDao = sellitemDao;
	}

	public void add(Sell sell) {
		sellDao.save(sell);
	}
	
	public Sell findBy(Map<String, Object> session, SellVO sellVO) {
		
		Sell sell = new Sell();
		BeanUtils.copyProperties(sellVO, sell);
		sell.setUser((User) session.get("user"));
		if (sellVO.getCustomerId() != null)
			sell.setCustomer(customerDao.findById(sellVO.getCustomerId()));
		sell.setTotal(new Double(0.00));
		sell.setCreateTime(DateUtil.toDate(sellVO.getCreateTimeStr()));
		if (sell.getStatus() == 1) {
			sell.setIncomePersonnel((User) session.get("user"));
			sell.setIncomeTime(new java.util.Date());
		}
		
		return sell;
	}
	
	public String sales(Map<Integer, Sellitem> sellitems, Sell sell) { //sellAction add() 方法專用。
		if (sell.getTax() == null)
			sell.setTax(new Double(0.00));
		
		String message = "";
		Product product = null;
		for (Sellitem item : sellitems.values()) {
			if (item.getQuantity() ==0 || item.getPurchasePrice()==0)
				continue;
			
			product = productDao.findById(item.getProduct().getId());
			if (sell.getClosed() == 1 && product.getType() == 1) {  //銷售單 而且商品類型為"庫存商品"時, 才進行庫存連動。    註:如果 報價跟銷售 都要做連動，方法需要作更改。
				if (!autoUnbox(product,item.getQuantity())) {
					StringBuffer buffer = new StringBuffer();
					buffer.append("商品 : ");
					buffer.append(product.getName());
					buffer.append(" , 庫存數量不足 ! <br />數量僅餘 : ");
					buffer.append(product.getStockNumber());
					buffer.append("   ");
					if (product.getUnit() != null)
						buffer.append(product.getUnit());
				
					message = buffer.toString();
					return message;
				}
				product.setStockNumber(product.getStockNumber() - item.getQuantity()); //因為是銷售 所以 庫存數量 - 銷售的數量。
			}
			
//			product.setPrice(item.getPurchasePrice()); 商品連動 調整售價    (根據銷售單上面所修改的售價，調整商品所設定好的售價)
			

			if (item.getTotal() != null) {
				item.setTotalAmount(item.getTotal() * (1+sell.getTax()));
				sell.setTotal(sell.getTotal() + item.getTotal());
			}
			
			item.setProduct(product);		
			item.setSell(sell);
			sell.getSellitems().add(item);		
			
		}
		
		sell.setTotal(Math.rint(sell.getTotal()));
		sell.setSalesTax(Math.rint(sell.getTotal() * sell.getTax()));
		sell.setTotalAmount(Math.rint(sell.getTotal() * (1+sell.getTax())));
		
		return message;
	}
	//自動拆箱功能(判斷商品數大於交易數，返回true ，拆箱完畢)
	//如果失敗， 返回false (拆箱失敗!)   
	//註 : 拆箱完畢  商品數大於 交易數 返回 true . 
	private boolean autoUnbox(Product product, Integer quantity) {
		if (product.getStockNumber() >= quantity)
			return true;
		
		if (!unbox(product)) { //商品數小於交易數，會自動拆箱 ，成功返回 true 、 失敗返回 false。
			return false;
		} else if (autoUnbox(product,quantity)){ //拆箱成功後 ， 再回頭做 自動拆箱 。 註 : 自動拆箱過程中 ， 一次成功 ， 便返回 true。 拆箱完畢
			return true;
		} else {  //所有的拆箱動作都失敗， 直接返回 false 。 給上層交易 判斷  交易取消。
			return false;
		}

	}
	//拆箱功能，傳入商品， 加入 拆解商品 之後 的 商品數。 註 : 拆箱失敗 ，回傳 false 。
	private boolean unbox(Product product) {
		ProductBox pBox = product.getProductBox();
		if (pBox == null) {
			return false;
		} else if (pBox.getBox().getStockNumber() <=0 && !unbox(pBox.getBox())) {
			return false;
		} else {
			product.setStockNumber(product.getStockNumber()+pBox.getQuantity());
			pBox.getBox().setStockNumber(pBox.getBox().getStockNumber()-1);
			return true;
		}
	}
	public void updateSales(Sell sell) {
		if (sell.getClosed() == 0)
			return;
		List<Sellitem> sellitems = sellitemDao.findBySellId(sell.getId());
		Product product = null;
		for (Sellitem item : sellitems) {
			product = productDao.findById(item.getProduct().getId());
			if (product.getType() == 1) {
				product.setStockNumber(product.getStockNumber() + item.getQuantity()); //還原銷售的庫存數量。
				productDao.update(product);
			}
		}
		sellitems = null;
		product = null;		
	}
	public void deleteSales(Sell sell) {
		if (sell.getClosed() == 0)
			return;
		List<Sellitem> sellitems = sellitemDao.findBySellId(sell.getId());
		Product product = null;
		for (Sellitem item : sellitems) {
			product = productDao.findById(item.getProduct().getId());
			if (product.getType() == 1) {
				product.setStockNumber(product.getStockNumber() - item.getQuantity()); //還原銷售的庫存數量。
				productDao.update(product);
			}
		}
	}
	public DataGrid getDataGrid(SellVO sellVO, Integer rows, Integer page,
			String sort, String order) {
		
		return sellDao.getDataGrid(sellVO, rows, page, sort, order);
	}

	public Sell get(Integer id) {
		return sellDao.findById(id);
	}

	public void update(Sell sell) {
		sellDao.update(sell);
	}

	public String delete(String ids) {
		Sell sell = null;
		String message = "";
		if (ids != null) {
			for (String id : ids.split(",")) {
				sell = sellDao.findById(new Integer(id.trim()));
				if (sell == null) {
					message =  "您所要刪除的訊息不存在 !!";
					continue;
				}
				sellDao.delete(sell);
				message = "成功刪除 !!";
			}
		}
		return message;
	}
	
	private void copyProperties(Sell sell, SellVO sellVO) {
		
		BeanUtils.copyProperties(sell, sellVO);
		sellVO.setUserName(sell.getUser().getUsername());
		sellVO.setCustomerName(sell.getCustomer().getName());
		if (sell.getIncomePersonnel() != null)
			sellVO.setIncomePersonnelName(sell.getIncomePersonnel().getUsername());
		sellVO.setCreateTimeStr(DateUtil.toCharForDataGrid(sell.getCreateTime()));
		sellVO.setIncomeTimeStr(DateUtil.toCharForDataGrid(sell.getIncomeTime()));
		
	}
	
	public void copyProperties(Collection<Sell> sells, Collection<SellVO> sellVOs) {
		
		Iterator<Sell> it = sells.iterator();
		while(it.hasNext()) {
			SellVO sellVO = new SellVO();
			this.copyProperties(it.next(), sellVO);
			sellVOs.add(sellVO);
		}
	}
	
}
