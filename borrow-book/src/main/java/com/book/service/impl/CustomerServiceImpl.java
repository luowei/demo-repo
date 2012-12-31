package com.book.service.impl;

import com.book.bean.Customer;
import com.book.common.exception.CustomerException;
import com.book.dao.ICustomerDao;
import com.book.dao.impl.CustomerDaoImpl;
import com.book.service.ICustomerService;

public class CustomerServiceImpl implements ICustomerService{
    ICustomerDao customerDao=new CustomerDaoImpl();

    public Customer login(String username, String password)
            throws CustomerException {
        Customer customer=this.findCustomer(username);
        //登录判断
        if(customer==null)
        {
            throw new CustomerException("用户名不存在！");
        }
        if(!customer.getPassword().equals(password))
        {
            throw new CustomerException("密码不正确！");
        }
        return customer;
    }

    public void register(Customer customer) throws CustomerException {
        //注册处理
        if(this.findCustomer(customer.getName())!=null)
        {
            throw new CustomerException("该用户名已存在！");
        }
        try {
            //保存用户提交后，由servlet传过来的数据
            customerDao.save(customer);
        } catch (Exception e) {
            throw new CustomerException(e.getMessage());
        }
    }

    public void update(Customer customer) throws CustomerException {

    }

    /**
     * 根据用户查找用户
     */
    public Customer findCustomer(String username) throws CustomerException {
        Customer cus;
        try {
            //调用数据访问层(dao)的findCustomer方法查找用户
            cus=customerDao.findCustomer(username);
        } catch (Exception e) {
            throw new CustomerException(e.getMessage());
        }
        return cus;
    }

}
