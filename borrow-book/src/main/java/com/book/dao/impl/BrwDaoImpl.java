package com.book.dao.impl;

import com.book.bean.Book;
import com.book.bean.BrwBook;
import com.book.bean.BrwList;
import com.book.common.exception.BrwException;
import com.book.common.jdbc.JdbcConnectionFactory;
import com.book.dao.IBrwDao;

import java.sql.*;
import java.util.*;
import java.util.Date;

public class BrwDaoImpl implements IBrwDao {

	public void deleteBrwBook(long orderId) throws BrwException{
		Connection conn= JdbcConnectionFactory.getConnection();
		try {
			Statement st=conn.createStatement();
			st.execute("delete from brwBook where id="+orderId);
			JdbcConnectionFactory.close(st, conn);
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BrwException(e.getMessage());
		}
		
	}

	public void deleteBrwList(long orderId)throws BrwException {
		Connection conn= JdbcConnectionFactory.getConnection();
		try {
			Statement st=conn.createStatement();
			st.execute("delete from brwlist where brwBook_id="+orderId);
			JdbcConnectionFactory.close(st, conn);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BrwException(e.getMessage());
		}
		
	}

	public Map<Long,Book> listAllBooks()throws BrwException {
		Map<Long,Book> books=new HashMap<Long, Book>();
		Book book=null;
		try {
			Connection conn= JdbcConnectionFactory.getConnection();
			Statement st=conn.createStatement();
			//���漯��
			ResultSet rs=st.executeQuery("select id,name,author,img,detail from Book");
			
			while(rs.next())
			{
				Long id=rs.getLong("id");
				book=new Book(id,rs.getString("name"),rs.getString("author"),rs.getString("img"),rs.getString("detail"));
				books.put(id, book);
			}
			JdbcConnectionFactory.close(st, conn);
		} catch (SQLException e) {
			throw new BrwException(e.getMessage());
		}
		return books;
	}

	public Collection<BrwBook> listAllBrwBook(long customerId) throws BrwException{
		Collection<BrwBook> brwbook=new ArrayList<BrwBook>();
		Connection conn= JdbcConnectionFactory.getConnection();
		Statement st=null;
		ResultSet rs=null;
		try {
			st=conn.createStatement();
			rs=st.executeQuery("select * from brwBook where customer_id="+customerId);
			while(rs.next())
			{
				java.sql.Date orderDate=rs.getDate("brwBookDate");
				BrwBook order=new BrwBook(rs.getLong("id"),orderDate);
				brwbook.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BrwException(e.getMessage());
		}
		JdbcConnectionFactory.close(rs, st, conn);
		return brwbook;
	}

	public Collection<BrwList> listBrwList(long orderId)throws BrwException {
		Connection conn= JdbcConnectionFactory.getConnection();
		Collection<BrwList> brwlist=new ArrayList<BrwList>();
		try {
			Statement st=conn.createStatement();
			ResultSet rs=st.executeQuery("select * from brwlist where brwBook_id="+orderId);
			
			while(rs.next())
			{
				long bookid=rs.getLong("book_id");
				Book book=findBookById(bookid);
				BrwList brwList=new BrwList(rs.getLong("id"),book,orderId,rs.getLong("num"));
				brwlist.add(brwList);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BrwException(e.getMessage());
		}
		return brwlist;
	}

	public Book findBookById(long bookId)throws BrwException {
		Connection conn= JdbcConnectionFactory.getConnection();
		Book book=null;
		try {
			Statement st=conn.createStatement();
			ResultSet rs=st.executeQuery("select * from book where id="+bookId);
			while(rs.next())
			{
				book=new Book(rs.getLong("id"),rs.getString("name"),rs.getString("author"),rs.getString("img"),rs.getString("detail"));
			}
			JdbcConnectionFactory.close(st, conn);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BrwException(e.getMessage());
		}
		return book;
	}
	
	
	public void saveBrwBook(BrwBook orderForm)throws BrwException {
		Connection conn= JdbcConnectionFactory.getConnection();
		try {
			PreparedStatement pst=conn.prepareStatement("insert into brwBook values(?,?,?)");
			long id=orderForm.getId();
			Date date=orderForm.getBrwBookDate();
			long customerId=orderForm.getCustomer().getId();
			pst.setLong(1, id);
			pst.setDate(2, new java.sql.Date(date.getTime()));
			pst.setLong(3, customerId);
			pst.execute();
			JdbcConnectionFactory.close(pst, conn);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BrwException(e.getMessage());
		}
		
	}

	public void saveBrwList(Collection<BrwList> brwlist) throws BrwException{
		Connection conn= JdbcConnectionFactory.getConnection();
		try {
			PreparedStatement pst=conn.prepareStatement("insert into brwlist values(brwlist_seq.nextval,?,?,?)");
			for(BrwList brwbook:brwlist)
			{
				long bookid=brwbook.getId();
				long orderid=brwbook.getBrwBookId();
				long num=brwbook.getNum();
				pst.setLong(1, bookid);
				pst.setLong(2,orderid);
				pst.setLong(3, orderid);
				pst.execute();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BrwException(e.getMessage());
		}
		
	}

	public long getIdNum() throws BrwException {
		Connection conn= JdbcConnectionFactory.getConnection();
		try {
			Statement st=conn.createStatement();
			ResultSet rs=st.executeQuery("select brwBook_seq.nextval from dual");
			long orderid=0;
			while(rs.next())
			{
				orderid=rs.getLong(1);
			}
			JdbcConnectionFactory.close(rs, st, conn);
			return orderid;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BrwException(e.getMessage());
		}
	}

	public BrwBook getBrwBook(long brwBook_id) throws BrwException {
		Connection conn= JdbcConnectionFactory.getConnection();
		Collection<BrwList> orderLines=new ArrayList<BrwList>();
		BrwBook orderForm=null;
		try {
			Statement st=conn.createStatement();
			ResultSet rs=st.executeQuery("select * from brwBook where id="+brwBook_id);
			while(rs.next())
			{
				orderForm=new BrwBook(rs.getLong("id"),rs.getDate("brwBookDate"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BrwException(e.getMessage());
		}
		return orderForm;
		// TODO Auto-generated method stub
		
	}
}
