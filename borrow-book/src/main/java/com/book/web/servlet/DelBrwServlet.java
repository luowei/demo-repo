package com.book.web.servlet;

import com.book.common.exception.BrwException;
import com.book.common.util.ServiceFactory;
import com.book.service.IBrwService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DelBrwServlet extends HttpServlet {

	IBrwService service=ServiceFactory.getBrwService();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		long brwBookId=Long.parseLong(request.getParameter("brwBookId"));
		try {
			service.deleteBrwBook(brwBookId);
			request.getRequestDispatcher("ListOrderServlet").forward(request, response);
		} catch (BrwException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
