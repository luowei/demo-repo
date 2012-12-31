package com.book.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.book.bean.Book;
import com.book.bean.BrwList;
import com.book.bean.BrwCart;

public class AddBrwBookServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        Long bookid=Long.parseLong(request.getParameter("bookid"));
        Long num=Long.parseLong(request.getParameter("num"));
        //Date orderdate=new Date();

        ServletContext sc=super.getServletContext();
        Map<Long,Book> books=(Map<Long,Book>)sc.getAttribute("books");
        Book book=books.get(bookid);
        //BrwList orderLine=new BrwList(book,1,num);
        //System.out.println(book.getId());
        BrwList orderLine=new BrwList();
        orderLine.setId(bookid);
        orderLine.setBook(book);
        //orderLine.setOrderdate(orderdate.getTime());
        orderLine.setNum(num);
        //System.out.println(orderLine.getBook().getName());

        HttpSession session=request.getSession();	//取得session
        System.out.println("sessionListener监听之前。。。。");
        BrwCart cart=(BrwCart) session.getAttribute("brwCart");

        //System.out.println(orderLine.getBook().getId());
        if(cart==null){
            cart=new BrwCart();
            session.setAttribute("brwCart", cart);
        }
        cart.add(orderLine);

        RequestDispatcher rd=request.getRequestDispatcher("listBookStore.jsp");
        rd.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
