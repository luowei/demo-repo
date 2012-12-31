package com.book.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.book.bean.Customer;
import com.book.common.exception.CustomerException;
import com.book.common.util.ServiceFactory;
import com.book.service.ICustomerService;

public class LoginServlet extends HttpServlet {
    private ICustomerService customerService=ServiceFactory.getCustomerService();

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //转码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        //获取参数
        String username=request.getParameter("name");
        String password=request.getParameter("password");
        try {
            //登录处理
            Customer customer=customerService.login(username, password);
            //准备session
            HttpSession session=request.getSession();
            session.setAttribute("customer", customer);
            //转发
            RequestDispatcher rd=request.getRequestDispatcher("listBookStore.jsp");
            rd.forward(request, response);

        } catch (CustomerException e) {
            //异常消息
            request.setAttribute("message", e.getMessage());
            e.printStackTrace();
            RequestDispatcher rd=request.getRequestDispatcher("login.jsp");
            rd.forward(request, response);
        }
    }
}
