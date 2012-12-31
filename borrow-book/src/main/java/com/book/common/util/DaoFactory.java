package com.book.common.util;

import com.book.dao.IBrwDao;
import com.book.dao.ICustomerDao;
import com.book.dao.impl.BrwDaoImpl;
import com.book.dao.impl.CustomerDaoImpl;

public class DaoFactory {
	private static ICustomerDao customerDao=new CustomerDaoImpl();
	private static IBrwDao brwDao=new BrwDaoImpl();
	public static ICustomerDao getCustomerDao() {
		return customerDao;
	}
	public static IBrwDao getBrwDao() {
		return brwDao;
	}
	
}
