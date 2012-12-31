package com.book.web.servlet;

import com.book.bean.BrwBook;
import com.book.bean.Customer;
import com.book.common.exception.BrwException;
import com.book.common.util.ServiceFactory;
import com.book.service.IBrwService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;

public class ListBrwBookServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		IBrwService service=ServiceFactory.getBrwService();
		HttpSession session=request.getSession();
		Customer customer=(Customer) session.getAttribute("customer");
		long customerId=customer.getId();
		
		try {
			Collection<BrwBook> brwlist=service.listAllBrwBook(customerId);
			request.setAttribute("brwlist", brwlist);
			request.getRequestDispatcher("listOrder.jsp").forward(request, response);
		} catch (BrwException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
