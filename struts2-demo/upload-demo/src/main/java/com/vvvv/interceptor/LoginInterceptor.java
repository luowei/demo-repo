package com.vvvv.interceptor;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.vvvv.struts2.LoginAction;

import java.util.Map;

public class LoginInterceptor extends AbstractInterceptor
{
	@Override
	@SuppressWarnings("unchecked")
	public String intercept(ActionInvocation invocation) throws Exception
	{
		if(LoginAction.class == invocation.getAction().getClass())
		{
			return invocation.invoke();
		}
		
		Map map = invocation.getInvocationContext().getSession();
		
		if(null == map.get("userInfo"))
		{
			return Action.LOGIN;
		}
		
		return invocation.invoke();
	}

}
