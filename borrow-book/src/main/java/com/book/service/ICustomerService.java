package com.book.service;

import com.book.bean.Customer;
import com.book.common.exception.CustomerException;

public interface ICustomerService {
	void register(Customer customer) throws CustomerException;
	Customer login(String username, String password)throws CustomerException;
	void update(Customer customer)throws CustomerException;
	Customer findCustomer(String username)throws CustomerException;
}
