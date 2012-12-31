package com.book.dao;

import com.book.bean.Customer;

public interface ICustomerDao {
	void save(Customer customer)throws Exception;
	Customer findCustomer(String username)throws Exception;
	void register(Customer customer)throws Exception;
	Customer login(String username, String password)throws Exception;
	void update(Customer customer)throws Exception;
}
