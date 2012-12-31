package com.vvvv.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class TheInterceptor2 extends AbstractInterceptor
{
	@Override
	public String intercept(ActionInvocation invocation) throws Exception
	{
		System.out.println("interceptor before...");
		
		System.out.println("interceptor2: " + invocation.getAction().getClass());
		
		String result = invocation.invoke();
		
		System.out.println("interceptor after...");
		
		return result;
	}

}
