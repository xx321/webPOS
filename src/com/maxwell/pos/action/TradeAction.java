package com.maxwell.pos.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.maxwell.pos.action.base.BaseAction;
import com.maxwell.pos.model.Checkout;
import com.maxwell.pos.model.InvoiceForFront;
import com.maxwell.pos.model.Product;
import com.maxwell.pos.model.ProductBox;
import com.maxwell.pos.model.ProductItem;
import com.maxwell.pos.model.Trade;
import com.maxwell.pos.model.TradeInvoice;
import com.maxwell.pos.model.Tradeitem;
import com.maxwell.pos.model.User;
import com.maxwell.pos.service.CheckoutManager;
import com.maxwell.pos.service.InvoiceForFrontManager;
import com.maxwell.pos.service.ProductManager;
import com.maxwell.pos.service.TradeManager;
import com.maxwell.pos.util.DateUtil;
import com.maxwell.pos.vo.TradeVO;
import com.maxwell.pos.vo.base.DataGrid;
import com.maxwell.pos.vo.base.Json;
import com.opensymphony.xwork2.ActionContext;

@Component("tradeAction")
@Scope("prototype")
public class TradeAction extends BaseAction {

	private static final long serialVersionUID = -1424383315086199886L;
	
	private Map<String, Object> session = ActionContext.getContext().getSession();
	
	private TradeManager tradeManager;
	private CheckoutManager checkoutManager;
	private ProductManager productManager;
	private InvoiceForFrontManager invoiceForFrontManager;
	
	private Trade trade;
	
	private Integer totalItem;
	private Double totalAmount;
	private Integer type;
	private String companyCode;
	private String invoiceNumber;
	
	private String ids;
	
	private String createtimeStart;
	private String createtimeEnd;
	
	private String message;
	private Checkout checkout;
	private Map<Integer, Tradeitem> tradeItems;

	@Resource
	public void setTradeManager(TradeManager tradeManager) {
		this.tradeManager = tradeManager;
	}
	@Resource
	public void setCheckoutManager(CheckoutManager checkoutManager) {
		this.checkoutManager = checkoutManager;
	}
	@Resource
	public void setProductManager(ProductManager productManager) {
		this.productManager = productManager;
	}
	@Resource
	public void setInvoiceForFrontManager(
			InvoiceForFrontManager invoiceForFrontManager) {
		this.invoiceForFrontManager = invoiceForFrontManager;
	}
	
	public void setTrade(Trade trade) {
		this.trade = trade;
	}
	
	public void setTotalItem(Integer totalItem) {
		this.totalItem = totalItem;
	}
	
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	public void setType(Integer type) {
		this.type = type;
	}
	
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	
	public void setIds(String ids) {
		this.ids = ids;
	}
	
	public String getCreatetimeStart() {
		return createtimeStart;
	}
	
	public void setCreatetimeStart(String createtimeStart) {
		this.createtimeStart = createtimeStart;
	}
	
	public String getCreatetimeEnd() {
		return createtimeEnd;
	}
	
	public void setCreatetimeEnd(String createtimeEnd) {
		this.createtimeEnd = createtimeEnd;
	}
	
	public String getMessage() {
		return message;
	}
	
	public Checkout getCheckout() {
		return checkout;
	}
	
	public Map<Integer, Tradeitem> getTradeItems() {
		return tradeItems;
	}
	
	public void setTradeItems(Map<Integer, Tradeitem> tradeItems) {
		this.tradeItems = tradeItems;
	}
	
//	@SuppressWarnings("unchecked")
	public void add() {  //前台交易 : 現金結帳   type = 0.   發票結帳  type = 1.
		
		Json json = new Json();	
		
		trade = new Trade();
		trade.setUser((User) session.get("user"));
		trade.setQuantity(totalItem);
		trade.setTotal(totalAmount);
		trade.setCreateTime(new java.util.Date());
		trade.setStatus(new Integer(0));
		trade.setType(type);
		
		if (trade.getType() == 1) { //發票交易  :  需要另外儲存一筆   tradeInvoice 的資料。
			InvoiceForFront iForFront = invoiceForFrontManager.get(new Integer(1));
			if (iForFront.getQuantity() >= 250) {
				json.setSuccess(false);
				json.setMsg("發票機 : 發票使用完畢，請更換發票 !<br />交易取消 !");
				super.writeJson(json);
				return;
			}
			
			TradeInvoice tInvoice = new TradeInvoice();
			tInvoice.setCompanyCode(companyCode);
			tInvoice.setInvoiceNumber(invoiceForFrontManager.getInvoiceNumber(iForFront));
			tInvoice.setStatus(new Integer(0));
			tInvoice.setTrade(trade);
			
			trade.setTradeInvoice(tInvoice);
			
			if (iForFront.getQuantity() >= 250) {
				json.setMsg("發票機 : 發票使用完畢，請更換發票 !<br />");
			}
		}
		
		tradeItems = (Map<Integer, Tradeitem>) session.get("tradeItems");
		for (Tradeitem item : tradeItems.values()) {	
			
			//前台系統，交易提交之前，計算商品庫存(product.stocknumber) 和 出售的商品數量(tradeitem.quantity) 做連動。
			Product product = productManager.get(item.getProduct().getId());
			
			//判斷交易是否失敗 ，如果失敗，則直接返回。
			boolean tradeFails = caseType(product,item.getQuantity());
			if (tradeFails)
				return;
				
			
			item.setProduct(product);
			item.setTrade(trade);
			trade.getTradeitems().add(item);
		}
		
		if (trade.getTradeitems().size() == 0)
			return;
		
		try {
			tradeManager.add(trade);
			
			/*        開啟執行緒，列印發票<未實作>                         */
			json.setSuccess(true);
			json.setMsg(json.getMsg() + "交易完成 !");
			super.writeJson(json);
		} catch (Exception e) {
			//交易失敗後， 實作區域 。
			
			json.setSuccess(true);
			json.setMsg("交易失敗 !<br /><br />請重新交易");
			super.writeJson(json);
		}
		
	}

	private boolean caseType(Product product,Integer quantity) {
		
		switch (product.getType()) {
		case 1:  //交易的商品類型 = 1 : 庫存商品 ， 則要判斷庫存數量 與 庫存數連動。 
			if (editStockNumber(product, quantity))
				return true;
			break;
		case 2:  // 這邊 判斷為2 時 ，商品類型 為組合商品  需要把 組合商品  所含的商品 集合  拿出來做迭代  case 1 : 的方法 (把裡面的方法獨立出來 做 Method
			for(ProductItem pItem : product.getProductItems()) {
				boolean fails = caseType(pItem.getItem(),pItem.getQuantity()*quantity);  //遞迴
				if (fails)
					return true;
			}
			break;
		default:  //default 則為 非庫存商品 ， 不做任何調整 直接結束  ，接著完成。
			break;
		}	
		return false;
	}
	//庫存數量連動編輯，如果數量不足 ， 返回true (交易失敗!)   註 : 所有的庫存數量判斷 都為 false 交易才會成功。
	private boolean editStockNumber(Product product, Integer quantity) {
		if (!autoUnbox(product,quantity)) {
			StringBuffer buffer = new StringBuffer();
			buffer.append("商品 : ");
			buffer.append(product.getName());
			buffer.append(" , 庫存數量不足 ! <br />數量僅餘 : ");
			buffer.append(product.getStockNumber());
			buffer.append("   ");
			if (product.getUnit() != null)
				buffer.append(product.getUnit());
		
			Json json = new Json();
			json.setSuccess(false);
			json.setMsg(buffer.toString());
			super.writeJson(json);
			return true;
		}
		product.setStockNumber(product.getStockNumber() - quantity);
		return false;
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
//	public String list() {
//		
//		Date dateBegin = DateUtil.toDate(createtimeStart);
//		Date dateEnd = DateUtil.toDate(createtimeEnd);
//		
//		if(dateBegin == null || dateEnd == null) {
//			message = "請選擇您所要查詢的日期 !! ";
//			return INPUT;
//		}
//		
//		if(dateBegin.after(dateEnd)) {
//			message = "您所選的日期範圍不符合 !! ";
//			return INPUT;
//		}
//		dateEnd = DateUtil.addDay(dateEnd, 1);
//		
//		List<Trade> trades = tradeManager.getListBy(dateBegin,dateEnd);
//		checkout = checkoutManager.get(trades);	
//		
//		checkout.setUser((User) session.get("user"));
//		checkout.setCreateTime(new java.util.Date());
//		
//		return SUCCESS;
//	}
	
	public String find() {	
		return SUCCESS;
	}
	public void datagrid() {
		
		Date begin = DateUtil.toDate(createtimeStart);
		Date end = DateUtil.toDate(createtimeEnd);
		if(begin == null || end == null)
		{
			//這裡要設定message
			DataGrid datagrid = new DataGrid();
			datagrid.setRows(new ArrayList<Object>());
			super.writeJson(datagrid);
			return;
		}
		
		TradeVO tradeVO = new TradeVO();
		BeanUtils.copyProperties(this, tradeVO);
		
		DataGrid datagrid = tradeManager.getDataGrid(tradeVO, getRows(), getPage(), getSort(), getOrder());
		
		super.writeJson(datagrid);
	}
	
	public String get() {
		
		
		
		return SUCCESS;
	}
	
	public void datagridByStatus() {
		
		TradeVO tradeVO = new TradeVO();
		tradeVO.setType(type);
		tradeVO.setInvoiceNumber(invoiceNumber);
		tradeVO.setIds(ids);
		tradeVO.setStatus(0);
		
		DataGrid datagrid = tradeManager.getDataGrid(tradeVO, getRows(), getPage(), getSort(), getOrder());
		
		super.writeJson(datagrid);
	}
	
	public void updateInvalid() {
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg(tradeManager.updateInvalid(ids));
		super.writeJson(j);
	}
}
