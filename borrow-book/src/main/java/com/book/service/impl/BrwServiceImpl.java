package com.book.service.impl;

import com.book.bean.Book;
import com.book.bean.BrwBook;
import com.book.bean.BrwList;
import com.book.common.exception.BrwException;
import com.book.common.util.DaoFactory;
import com.book.dao.IBrwDao;
import com.book.service.IBrwService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class BrwServiceImpl implements IBrwService {

	IBrwDao brwDao= DaoFactory.getBrwDao();
	public void deleteBrwBook(long brwBookId) throws BrwException {
		brwDao.deleteBrwList(brwBookId);
		brwDao.deleteBrwBook(brwBookId);
	}

	public void deleteBrwList(long brwBookId) throws BrwException {
		brwDao.deleteBrwList(brwBookId);
	}

	public Map<Long,Book> listAllBooks() throws BrwException {
		Map<Long,Book> books=brwDao.listAllBooks();
		return books;
	}

	public Collection<BrwBook> listAllBrwBook(long customerId)
			throws BrwException {
		Collection<BrwBook> brwList=brwDao.listAllBrwBook(customerId);
		return brwList;
	}

	public Collection<BrwList> listBrwList(long brwBookId)
			throws BrwException {
		Collection<BrwList> orderLines=new ArrayList<BrwList>();
		try {
			orderLines=brwDao.listBrwList(brwBookId);
		} catch (RuntimeException e) {
			e.printStackTrace();
			throw new BrwException(e.getMessage());
		}
		return orderLines;
	}

	public void save(BrwBook brwBook)throws BrwException{
		try {
			long brwBookId=brwDao.getIdNum();
			brwBook.setId(brwBookId);
			Collection<BrwList> brwList=brwBook.getBrwList();
			for(BrwList orderLine:brwList)
			{
				orderLine.setBrwBookId(brwBookId);
			}
			brwDao.saveBrwBook(brwBook);
			brwDao.saveBrwList(brwList);
		} catch (BrwException e) {
			e.printStackTrace();
			throw new BrwException(e.getMessage());
		}
		
	}

	public void save(BrwList orderLine) {
		// TODO Auto-generated method stub
		
	}

	public Book findBookById(long bookId) throws BrwException {
		Book book=brwDao.findBookById(bookId);
		return book;
	}

}
