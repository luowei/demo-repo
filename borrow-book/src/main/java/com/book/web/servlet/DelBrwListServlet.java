package com.book.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.book.bean.BrwList;
import com.book.bean.BrwCart;

public class DelBrwListServlet extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request,response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        Long bookid=Long.parseLong(request.getParameter("bookId"));

        HttpSession session=request.getSession();	//取得session
        BrwCart cart=(BrwCart) session.getAttribute("brwCart");
        //System.out.println("-----------");
        cart.remove(bookid);


        RequestDispatcher rd=request.getRequestDispatcher("listCart.jsp");
        rd.forward(request, response);
    }

}
