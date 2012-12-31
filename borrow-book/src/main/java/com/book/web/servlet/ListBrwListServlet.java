package com.book.web.servlet;

import com.book.bean.BrwList;
import com.book.common.util.ServiceFactory;
import com.book.service.IBrwService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

public class ListBrwListServlet extends HttpServlet {

	IBrwService service= ServiceFactory.getBrwService();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		long orderid=Long.parseLong(request.getParameter("orderid"));
		Double cost=Double.parseDouble(request.getParameter("cost"));
		try{
			Collection<BrwList> brwlist=service.listBrwList(orderid);
			request.setAttribute("brwlist", brwlist);
			request.setAttribute("cost", cost);
			request.getRequestDispatcher("orderinfo.jsp").forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
