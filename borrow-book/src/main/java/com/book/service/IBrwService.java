package com.book.service;

import com.book.bean.Book;
import com.book.bean.BrwBook;
import com.book.bean.BrwList;
import com.book.common.exception.BrwException;

import java.util.Collection;
import java.util.Map;

public interface IBrwService {
	void save(BrwBook brwBook) throws BrwException;
	void save(BrwList brwList)throws BrwException;
	Collection<BrwBook> listAllBrwBook(long customerId)throws BrwException;
	Collection<BrwList> listBrwList(long orderId)throws BrwException;
	
	void deleteBrwBook(long brwbookid)throws BrwException;
	void deleteBrwList(long brwbookid)throws BrwException;
	Book findBookById(long brwbookid)throws BrwException;
	Map<Long,Book> listAllBooks()throws BrwException;
}
