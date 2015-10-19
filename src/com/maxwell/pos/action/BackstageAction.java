package com.maxwell.pos.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.maxwell.pos.action.base.BaseAction;
import com.maxwell.pos.model.Category;
import com.maxwell.pos.service.CategoryManager;
import com.maxwell.pos.vo.base.Json;
import com.opensymphony.xwork2.ActionContext;

@Component("backstageAction")
@Scope("prototype")
public class BackstageAction extends BaseAction {

	private static final long serialVersionUID = -8034752436704041157L;
	private Map<String, Object> session = ActionContext.getContext().getSession();
	private CategoryManager categoryManager;
	
	@Resource
	public void setCategoryManager(CategoryManager categoryManager) {
		this.categoryManager = categoryManager;
	}
	
	public void backstagePrepare() {
		List<Category> categorys = categoryManager.list(); //取得所有的類別訊息
		categorys.remove(categoryManager.get(99));  //移除 組合商品類別  *
		
		session.put("categorys", categorys);
		session.remove("goodsItems");
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("");
		super.writeJson(j);
	}
}
