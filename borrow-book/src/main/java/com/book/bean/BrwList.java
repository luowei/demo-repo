package com.book.bean;

import com.book.common.exception.BrwException;
import com.book.common.util.DaoFactory;
import com.book.dao.IBrwDao;

public class BrwList {
	private long id;
	private Book book;
	private long brwBookId;
	private long num;
	//private BrwBook orderForm;
	public BrwList() {
	}
	
	public BrwList(Book book, long orderForm_id, long num) {
		super();
		this.book = book;
		brwBookId = orderForm_id;
		this.num = num;
	}
	
	public BrwList(long id, Book book, long orderForm_id, long num) {
		super();
		this.id = id;
		this.book = book;
		brwBookId = orderForm_id;
		this.num = num;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public long getBrwBookId() {
		return brwBookId;
	}
	public void setBrwBookId(long orderForm_id) {
		brwBookId = orderForm_id;
	}
	
	public long getNum() {
		return num;
	}
	public void setNum(long num) {
		this.num = num;
	}
	
	public BrwBook getBrwBook() throws BrwException
	{
		IBrwDao brwDao=DaoFactory.getBrwDao();
		//System.out.println("OrderForm_id « :"+brwBookId);
		BrwBook orderForm=brwDao.getBrwBook(brwBookId);
		//System.out.println(orderForm.toString());
		return orderForm;
	}
}
