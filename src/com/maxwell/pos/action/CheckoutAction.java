package com.maxwell.pos.action;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.maxwell.pos.model.Checkout;
import com.maxwell.pos.model.User;
import com.maxwell.pos.service.CheckoutManager;
import com.maxwell.pos.util.DateUtil;
import com.maxwell.pos.vo.CheckoutVO;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Component("checkoutAction")
@Scope("prototype")
public class CheckoutAction extends ActionSupport {

	private static final long serialVersionUID = 8233921273376304031L;
	
	private Map<String, Object> session = ActionContext.getContext().getSession();
	
	private CheckoutManager checkoutManager;
	private Checkout checkout;
	private Checkout lastCheckout;
	
	private String createtimeStart;
	private String createtimeEnd;
	
	private CheckoutVO checkoutVO;
	
	private String message;
	@Resource
	public void setCheckoutManager(CheckoutManager checkoutManager) {
		this.checkoutManager = checkoutManager;
	}
	
	public void setCheckout(Checkout checkout) {
		this.checkout = checkout;
	}

	public Checkout getCheckout() {
		return checkout;
	}

	public Checkout getLastCheckout() {
		return lastCheckout;
	}

	public void setCreatetimeStart(String createtimeStart) {
		this.createtimeStart = createtimeStart;
	}

	public void setCreatetimeEnd(String createtimeEnd) {
		this.createtimeEnd = createtimeEnd;
	}

	public CheckoutVO getCheckoutVO() {
		return checkoutVO;
	}

	public void setCheckoutVO(CheckoutVO checkoutVO) {
		this.checkoutVO = checkoutVO;
	}
	
	public String getMessage() {
		return message;
	}

	public String load() {
		checkout = checkoutManager.getByTradeStatus(new Integer(0));
		checkout.setUser((User) session.get("user"));
		checkout.setCreateTime(new java.util.Date());
		session.put("checkout", checkout);
		
		lastCheckout = checkoutManager.getLastCheckout();
		
		
		return SUCCESS;
	}
	
	public String add() {
		checkout = (Checkout) session.get("checkout");
		checkoutManager.add(checkout);
		session.remove("checkout");
		return SUCCESS;
	}
	
	public String find() {
		Date beginDate = DateUtil.toDate(createtimeStart);
		Date endDate = DateUtil.toDate(createtimeEnd);
		
		if(beginDate == null || endDate == null)
		{
			return INPUT;
		}
		
		endDate = DateUtil.addDay(endDate, 1);
		
//		List<Object[]> list = checkoutManager.getCheckoutByTrade(begin, end);
//		
//		checkoutVO = getDefaultVO(); //初始化之前 先將 屬性值 給定 0 ，防止"空指針"。
//
//		for (Object[] o : list) {
//			checkoutVO.setTotalCount(checkoutVO.getTotalCount()+(Long) o[0]);
//			checkoutVO.setTotalItem(checkoutVO.getTotalItem()+(Long) o[1]);
//			checkoutVO.setTotalAmount(checkoutVO.getTotalAmount()+Math.rint((Double) o[2]));
//			if ((Integer) o[3] ==1) {
//				checkoutVO.setInvoiceCount(checkoutVO.getInvoiceCount()+(Long) o[0]);
//				checkoutVO.setInvoiceItem(checkoutVO.getInvoiceItem()+(Long) o[1]);
//				checkoutVO.setInvoiceAmount(checkoutVO.getInvoiceAmount()+Math.rint((Double) o[2]));
//			} else {
//				checkoutVO.setCashCount(checkoutVO.getCashCount()+(Long) o[0]);
//				checkoutVO.setCashItem(checkoutVO.getCashItem()+(Long) o[1]);
//				checkoutVO.setCashAmount(checkoutVO.getCashAmount()+Math.rint((Double) o[2]));
//			}
//		}
		Checkout checkout = checkoutManager.getCheckoutByTrade(beginDate, endDate);
		checkoutVO = new CheckoutVO();
		BeanUtils.copyProperties(checkout, checkoutVO);
		checkoutVO.setUser((User) session.get("user"));
		checkoutVO.setCreateTime(new java.util.Date());
		checkoutVO.setCreatetimeStart(createtimeStart);
		checkoutVO.setCreatetimeEnd(createtimeEnd);
		
		return SUCCESS;
	}

//	private CheckoutVO getDefaultVO() {
//		CheckoutVO checkoutVO = new CheckoutVO();
//		checkoutVO.setCashCount((long) 0);
//		checkoutVO.setInvoiceCount((long) 0);
//		checkoutVO.setTotalCount((long) 0);
//		checkoutVO.setCashItem((long) 0);
//		checkoutVO.setCashAmount(0.0);
//		checkoutVO.setInvoiceItem((long) 0);
//		checkoutVO.setInvoiceAmount(0.0);
//		checkoutVO.setTotalItem((long) 0);
//		checkoutVO.setTotalAmount(0.0);
//		return checkoutVO;
//	}
}
