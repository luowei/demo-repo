package com.book.web.servlet;

import com.book.bean.Book;
import com.book.common.exception.BrwException;
import com.book.common.util.ServiceFactory;
import com.book.service.IBrwService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BookDetailServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		IBrwService brwService=ServiceFactory.getBrwService();
		long bookid=Long.parseLong(request.getParameter("bookid"));
		try {
			Book book=brwService.findBookById(bookid);
			request.setAttribute("book", book);
			request.getRequestDispatcher("bookdetails.jsp").forward(request, response);
		} catch (BrwException e) {
			e.printStackTrace();
		}
	}

}
