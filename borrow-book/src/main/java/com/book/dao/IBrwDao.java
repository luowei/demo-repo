package com.book.dao;

import com.book.bean.Book;
import com.book.bean.BrwBook;
import com.book.bean.BrwList;
import com.book.common.exception.BrwException;

import java.util.Collection;
import java.util.Map;

public interface IBrwDao {
	void saveBrwBook(BrwBook brwbook)throws BrwException;
	void saveBrwList(Collection<BrwList> brwlist)throws BrwException;
	Collection<BrwBook> listAllBrwBook(long customerId)throws BrwException;
	Collection<BrwList> listBrwList(long orderId)throws BrwException;
	
	void deleteBrwBook(long brwbookid)throws BrwException;
	void deleteBrwList(long brwboookid)throws BrwException;
	
	Map<Long,Book> listAllBooks()throws BrwException;
	long getIdNum() throws BrwException;
	BrwBook getBrwBook(long brwbookid) throws BrwException;
	Book findBookById(long bookId)throws BrwException;
}
