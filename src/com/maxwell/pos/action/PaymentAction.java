package com.maxwell.pos.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jiangxd.excelTools.ExcelUtils;
import jiangxd.excelTools.JsGridReportBase;
import jiangxd.excelTools.TableColumn;
import jiangxd.excelTools.TableData;
import jiangxd.excelTools.TableHeaderMetaData;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.maxwell.pos.action.base.BaseAction;
import com.maxwell.pos.model.IntoGoods;
import com.maxwell.pos.model.Payment;
import com.maxwell.pos.model.User;
import com.maxwell.pos.service.IntoGoodsManager;
import com.maxwell.pos.service.PaymentManager;
import com.maxwell.pos.vo.IntoGoodsVO;
import com.maxwell.pos.vo.PaymentVO;
import com.maxwell.pos.vo.base.Json;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

@Component("paymentAction")
@Scope("prototype")
public class PaymentAction extends BaseAction implements ModelDriven<PaymentVO> {

	private static final long serialVersionUID = 5056737215609985271L;
	private Map<String, Object> session = ActionContext.getContext().getSession();
	
	private PaymentManager paymentManager;
	private IntoGoodsManager intoGoodsManager;
	
	@Resource
	public void setPaymentManager(PaymentManager paymentManager) {
		this.paymentManager = paymentManager;
	}
	@Resource
	public void setIntoGoodsManager(IntoGoodsManager intoGoodsManager) {
		this.intoGoodsManager = intoGoodsManager;
	}

	private PaymentVO paymentVO = new PaymentVO();
	
	public PaymentVO getModel() {
		return paymentVO;
	}

	public void add() {
		Json j = new Json();
		IntoGoods intoGoods = null;
		if (paymentVO.getIntoGoodsIds() == null || "".equals(paymentVO.getIntoGoodsIds().trim())) {
			j.setSuccess(false);
			j.setMsg("付款失敗 ! <br /> 請選擇付款項目");
			super.writeJson(j);
			return;
		}
		
		List<IntoGoods> intoGoodsList = new ArrayList<IntoGoods>();
		
		for(String intoGoodsId : paymentVO.getIntoGoodsIds().split(",")) { //根據傳入的id 字串取得所有的進貨單資訊
			intoGoods = intoGoodsManager.get(new Integer(intoGoodsId.trim()));
			intoGoodsList.add(intoGoods);
		}
		int length = intoGoodsList.size();
		for(int i=0; i<length; i++) { //判斷選擇的供應商是否相同
			intoGoods = intoGoodsList.get(i);
			if(!intoGoodsList.get(0).getSupplier().getId().equals(intoGoods.getSupplier().getId())) {
				j.setSuccess(false);
				j.setMsg("付款失敗 ! <br /> 請選擇相同供應商");
				super.writeJson(j);
				return;
			}
			if(intoGoods.getStatus() ==1) {
				j.setSuccess(false);
				j.setMsg("付款失敗 ! <br /> 項目不可重覆付款");
				super.writeJson(j);
				return;
			}
		}
		
		Payment payment = paymentManager.get(paymentVO,intoGoodsList,session); //根據參數，取得 Payment。
		
		List<IntoGoodsVO> intoGoodsVOs = new ArrayList<IntoGoodsVO>();
		intoGoodsManager.copyProperties(payment.getIntoGoodses(), intoGoodsVOs);
		//儲存付款單
		paymentManager.add(payment);
		j.setSuccess(true);
		j.setMsg("付款成功 !");
		j.setObj(intoGoodsVOs);
		super.writeJson(j);
	}
	
	public void exportLastPaymentExcel() throws Exception {
		Payment payment = paymentManager.getLastPayment();
		if (payment != null)
			exportExcel(payment);
	}
	
	public void exportExcel(Payment payment) throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		
		//獲取數據
		List<IntoGoodsVO> list = new ArrayList<IntoGoodsVO>();
		intoGoodsManager.copyProperties(payment.getIntoGoodses(), list);
		
		String title = "付款單據";
        User user = (User) session.get("user");
        String creator = user.getUsername();
		
		String[] hearders = new String[] {"供應商名稱", "進貨單編號", "發票編號", "付款方式", "單據日期", "進貨金額", "付款人員", "付款時間"}; //表頭陣列
		String[] fields = new String[] {"supplierName", "id", "invoiceNumber", "paymentModeStr", "createTimeStr", "totalAmount", "paymentPersonnelName", "paymentTimeStr"}; //intoGoods對象屬性陣列
		TableData td = ExcelUtils.createTableData(list, getTableHeader(hearders),fields);
		
        td.compute();//執行小計計算
        JsGridReportBase report = new JsGridReportBase(request, response);
        report.exportToExcel(title, creator, td);
	}
	
	private TableHeaderMetaData getTableHeader(String[] hearders){
	    int spanCount1 = 1;//需要合并的列数。从第1列开始到指定列。如不需要合并，则将该行和153、154行去掉即可
	    int spanCount2 = 7;
	    int spanCount3 = 8;
	    
	    //创建表头对象
		TableHeaderMetaData headMeta = new TableHeaderMetaData();
		for(int i=0;i<hearders.length;i++){
			TableColumn tc = new TableColumn();
			tc.setDisplay(hearders[i]);
			
			//设置按指定列统计
			if(i==0)//按第1列供應商進行小計
				tc.setDisplaySummary(true);
			
			//設置統計目標列的統計方式，目前支持 : max、min、avg、sum
//			if(i==3)//求最大值
//				tc.setAggregateRule("max");
//			if(i==4)//求平均值
//				tc.setAggregateRule("avg");
			if(i==5)//求總合
				tc.setAggregateRule("sum");
			
			if(i==(spanCount1-1) || i==(spanCount2-1) || i==(spanCount3-1))//第 1'7'8列行合并
				tc.setGrouped(true);
			headMeta.addColumn(tc);
		}
		return headMeta;
	}
}
