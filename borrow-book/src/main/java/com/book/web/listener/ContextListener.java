package com.book.web.listener;

import com.book.bean.Book;
import com.book.common.exception.BrwException;
import com.book.common.util.ServiceFactory;
import com.book.service.IBrwService;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Map;

public class ContextListener implements ServletContextListener {
	
	private IBrwService brwService=ServiceFactory.getBrwService();
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void contextInitialized(ServletContextEvent sce) {
		ServletContext sc=sce.getServletContext();
		Map<Long, Book> books;
		try {
			books = brwService.listAllBooks();
			sc.setAttribute("books", books);
			System.out.println(books.size());
		} catch (BrwException e) {
			e.printStackTrace();
		}
	}
}
