package com.book.bean;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class BrwCart {
	private Map<Long,BrwList> cart=new HashMap<Long,BrwList>();
	
	public void add(BrwList orderLine)
	{
		Long bookid=orderLine.getBook().getId();
		if(cart.containsKey(bookid))
		{
			Long old_num=cart.get(bookid).getNum();
			Long new_num=orderLine.getNum();
			cart.get(bookid).setNum(old_num+new_num);
		}
		else{
			cart.put(bookid, orderLine);
		}
		
	}
	
	public void remove(Long bookId) 
	{
		cart.remove(bookId);
	}
	
	
	
	public void clear()
	{
		cart.clear();
	}
	
	public Collection<BrwList> getBrwList()
	{
		return cart.values();
	}
	
	
	public Book findBook(long bookid){
		
		return cart.get(bookid).getBook();
	}

	public BrwList findBrwList(long bookid){
		
		return cart.get(bookid);
	}
	
	public String getWord(){
		return "hello";
	}
}
