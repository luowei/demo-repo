package com.vvvv.listener;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.PreResultListener;

public class TheListener implements PreResultListener
{
	public void beforeResult(ActionInvocation invocation, String resultCode)
	{
		System.out.println("result code: " + resultCode);
	}
}
