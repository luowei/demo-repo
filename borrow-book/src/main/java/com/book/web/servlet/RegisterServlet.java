package com.book.web.servlet;

import com.book.bean.Customer;
import com.book.common.exception.CustomerException;
import com.book.common.util.ServiceFactory;
import com.book.service.ICustomerService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		ICustomerService customerService= ServiceFactory.getCustomerService();
		Customer customer=new Customer(
			request.getParameter("name"),
			request.getParameter("password"),
			request.getParameter("zip"),
			request.getParameter("address"),
			request.getParameter("telephone"),
			request.getParameter("email")
			);
		try {
			customerService.register(customer);
			RequestDispatcher rd=request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
		} catch (CustomerException e) {
			e.printStackTrace();
			request.setAttribute("message",e.getMessage());
			RequestDispatcher rd=request.getRequestDispatcher("register.jsp");
			rd.forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}
}
