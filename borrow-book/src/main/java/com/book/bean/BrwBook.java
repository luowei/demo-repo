package com.book.bean;

import java.util.Collection;
import java.util.Date;

public class BrwBook {
	private long id;
	private Customer customer;
	private Date brwBookDate;
	private Collection<BrwList> brwList;
	


	public BrwBook() {
		super();
	}
	

	public BrwBook(long id, Date orderdate) {
		super();
		this.id = id;
		this.brwBookDate = orderdate;
	}

	public BrwBook(Customer customer, Date orderdate,
			Collection<BrwList> orderLines) {
		super();
		this.customer = customer;
		this.brwBookDate = orderdate;
		this.brwList = orderLines;
	}


	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Date getBrwBookDate() {
		return brwBookDate;
	}
	public void setBrwBookdate(Date orderdate) {
		this.brwBookDate = orderdate;
	}


	public Collection<BrwList> getBrwList() {
		return brwList;
	}


	public void setBrwList(Collection<BrwList> orderLines) {
		this.brwList = orderLines;
	}
	
}
