package com.book.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.book.bean.Customer;
import com.book.common.exception.CustomerException;
import com.book.common.jdbc.JdbcConnectionFactory;
import com.book.dao.ICustomerDao;

public class CustomerDaoImpl implements ICustomerDao{

    public Customer login(String username, String password) throws CustomerException{
        return null;
    }

    public void register(Customer customer) throws CustomerException{

    }

    public void update(Customer customer) throws CustomerException{

    }

    //插入用户信息
    public void save(Customer customer) throws CustomerException, SQLException {
        //从工厂类里直接拿连接对象
        Connection conn=JdbcConnectionFactory.getConnection();
        //利用连接对象创建sql语句的执行环境
        Statement st=conn.createStatement();
        //利用Statement对象运行sql语句
        st.executeQuery("insert into customer values(customer_seq.nextval,'"+customer.getName()
                +"','"+customer.getPassword()+"','"+customer.getZip()+"','"+customer.getAddress()
                +"','"+customer.getTelephone()+"','"+customer.getEmail()+"')");
        JdbcConnectionFactory.close(st, conn);
    }

    //根据用户名查找用户
    public Customer findCustomer(String username) throws CustomerException, SQLException {
        Connection conn=JdbcConnectionFactory.getConnection();
        Statement st=conn.createStatement();
        //保存集果集
        ResultSet rs=st.executeQuery("select * from customer where name='"+username+"'");
        Customer customer=null;
        while(rs.next())
        {
            customer=new Customer(rs.getLong("id"),
                    rs.getString("name"),
                    rs.getString("password"),
                    rs.getString("zip"),
                    rs.getString("address"),
                    rs.getString("telephone"),
                    rs.getString("email")
            );
        }
        JdbcConnectionFactory.close(st, conn);
        return customer;
    }
}
