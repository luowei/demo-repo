package com.book.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.book.bean.Customer;
import com.book.bean.BrwBook;
import com.book.bean.BrwList;
import com.book.bean.BrwCart;
import com.book.common.util.ServiceFactory;
import com.book.service.IBrwService;

public class ConfirmBrwServlet extends HttpServlet {

    IBrwService brwService=ServiceFactory.getBrwService();
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        HttpSession session=request.getSession();	//取得session
        BrwCart cart=(BrwCart) session.getAttribute("brwCart");
        Collection<BrwList> brwlist=cart.getBrwList();

        Customer customer=(Customer) session.getAttribute("customer");
        Date date=new Date();
        BrwBook brwBook=new BrwBook(customer,date,brwlist);

        try {
            brwService.save(brwBook);
            request.getRequestDispatcher("confirm.jsp").forward(request, response);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
