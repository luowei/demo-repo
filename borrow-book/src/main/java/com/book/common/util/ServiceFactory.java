package com.book.common.util;

import com.book.service.IBrwService;
import com.book.service.ICustomerService;
import com.book.service.impl.BrwServiceImpl;
import com.book.service.impl.CustomerServiceImpl;

public class ServiceFactory {
	private static ICustomerService customerService=new CustomerServiceImpl();
	private static IBrwService brwService=new BrwServiceImpl();
	public static ICustomerService getCustomerService() {
		return customerService;
	}
	public static IBrwService getBrwService() {
		return brwService;
	}
	
}
