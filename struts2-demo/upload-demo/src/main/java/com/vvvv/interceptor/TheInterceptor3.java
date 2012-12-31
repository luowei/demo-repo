package com.vvvv.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.vvvv.listener.TheListener;

public class TheInterceptor3 extends MethodFilterInterceptor
{
	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception
	{
		invocation.addPreResultListener(new TheListener());
		
		System.out.println("before interceptor3");
		
		String result = invocation.invoke();
		
		System.out.println("after interceptor3");
		
		return result;
	}

}
