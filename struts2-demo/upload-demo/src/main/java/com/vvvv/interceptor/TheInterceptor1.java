package com.vvvv.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class TheInterceptor1 implements Interceptor
{
	private String test;
	
	public String getTest()
	{
		return test;
	}

	public void setTest(String test)
	{
		System.out.println("setTest invoked!");
		
		this.test = test;
	}

	public void destroy()
	{
		
	}

	public void init()
	{
		System.out.println("init invoked!");
		
		System.out.println("test: " + this.test);
	}

	public String intercept(ActionInvocation invocation) throws Exception
	{
		System.out.println("before");
		
		System.out.println("interceptor1: " + invocation.getAction().getClass());
		
		String result = invocation.invoke();
		
		System.out.println("after");
		
		return result;
	}

}
