package com.book.web.servlet;

import com.book.bean.BrwCart;
import com.book.bean.BrwList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class EditBrwListServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		Long bookid=Long.parseLong(request.getParameter("brwBookId"));
		Long num=Long.parseLong(request.getParameter("num"));
		
		HttpSession session=request.getSession();	//取得session
		BrwCart cart=(BrwCart) session.getAttribute("brwCart");
		BrwList orderLine=cart.findBrwList(bookid);
		orderLine.setNum(num);
		
		RequestDispatcher rd=request.getRequestDispatcher("listCart.jsp");
		rd.forward(request, response);
	}

}
