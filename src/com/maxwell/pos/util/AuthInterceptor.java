package com.maxwell.pos.util;

import com.maxwell.pos.model.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * 权限拦截器
 * 
 * @author 孙宇
 * 
 */
public class AuthInterceptor extends MethodFilterInterceptor {

	private static final long serialVersionUID = -4633239823840098384L;

	protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
		ActionContext actionContext=actionInvocation.getInvocationContext();
		
		User user=(User)actionContext.getSession().get("user");
		if (user !=null && "1".equals(user.getPurview().toString())) {
			return actionInvocation.invoke();
		}
		return "noAuth";
	}

}
