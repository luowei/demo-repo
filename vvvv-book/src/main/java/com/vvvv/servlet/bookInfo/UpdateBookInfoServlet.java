package com.vvvv.servlet.bookInfo;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vvvv.dao.BookInfoDAO;
import com.vvvv.dao.BookTypeDAO;
import com.vvvv.dao.Tool;
import com.vvvv.entity.BookInfo;
import com.vvvv.entity.BookType;

public class UpdateBookInfoServlet extends HttpServlet
{
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        this.doGet(request,response);
    }


    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        int bookId=Integer.parseInt(request.getParameter("bookId"));
        String bookName=request.getParameter("bookName");
        int booktypeId=Integer.parseInt(request.getParameter("bookTypeId"));
        String pbName=request.getParameter("pbName");
        String author=request.getParameter("author");
        String context=request.getParameter("context");
        String smallImg=request.getParameter("smallImg");
        String bigImg=request.getParameter("bigImg");
        float price=Float.parseFloat(request.getParameter("price"));

        String strPbdate=request.getParameter("pbDate");
        //System.out.println(strPbdate);
        Date pbdate=null;
        try
        {
            pbdate = Tool.conventStringToDate(strPbdate);

        } catch (ParseException e)
        {
            e.printStackTrace();
        }
        java.sql.Date sqlDate=new java.sql.Date(pbdate.getTime());
        //System.out.println(sqlDate);
        float vvvvprice=Float.parseFloat(request.getParameter("vvvvPrice"));
        int bookStatus=Integer.parseInt(request.getParameter("bookStates"));

        BookInfoDAO bookInfoDAO=new BookInfoDAO();
        BookInfo bookInfo=new BookInfo(bookId,bookName, booktypeId, pbName, author,
                context.trim(), smallImg, bigImg, price, sqlDate, vvvvprice, bookStatus);

        String msg="失败";
        String returnPath="/background/updateBookInfo.jsp";
        if(bookInfoDAO.update(bookInfo))
        {
            msg="成功";
            returnPath="/FindAllBookInfoServlet";
        }
        //提示消息
        request.setAttribute("msg", "修改图书"+msg);
        //返回路径
        //String servletPath=request.getContextPath();

        request.setAttribute("returnPath", returnPath);
        request.getRequestDispatcher("/background/message.jsp").forward(request, response);

    }

}
