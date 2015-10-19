package com.maxwell.pos.util;

import com.maxwell.pos.model.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class SessionInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 5933299603190285324L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext actionContext=invocation.getInvocationContext();

		User user=(User)actionContext.getSession().get("user");
		if(user!=null){
			return invocation.invoke();
		}
		//如果session失效，返回登录页面
		return "redirect_login";
	}

}
