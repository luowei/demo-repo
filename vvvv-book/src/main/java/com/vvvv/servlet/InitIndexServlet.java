package com.vvvv.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vvvv.dao.BookInfoDAO;
import com.vvvv.dao.BookTypeDAO;
import com.vvvv.entity.BookInfo;
import com.vvvv.entity.BookType;

public class InitIndexServlet extends HttpServlet
{

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        List<BookInfo> ztBooks=null;
        List<BookInfo> hjBooks=null;
        List<BookInfo> zxBooks=null;
        List<BookInfo> phBooks=null;

        BookInfoDAO bookInfoDAO=new BookInfoDAO();

        //String strBookStatus=request.getParameter("bookStatus");
        //int bookStatus=Integer.parseInt(strBookStatus);
        BookInfo nullBook=bookInfoDAO.findById(1);//bookId为1的书为缺书状态信息
        request.setAttribute("nullBook", nullBook);

        ztBooks=bookInfoDAO.findAll(2,10);	//2表示已上架的书
        //System.out.println(ztBooks.size());
        //BookInfo book=ztBooks.get(0);
        //System.out.println(book.getBookName());
        request.setAttribute("ztBooks", ztBooks);	//专题推荐

        hjBooks=bookInfoDAO.findAll(2);
        request.setAttribute("hjBooks", hjBooks);	//行家推荐

        zxBooks=bookInfoDAO.findAll(2);
        request.setAttribute("zxBooks", zxBooks);	//最新排行

        phBooks=bookInfoDAO.findAll(2);
        request.setAttribute("phBooks", phBooks);	//vvvv排行

        BookTypeDAO bookTypeDAO=new BookTypeDAO();
        List<BookType> bookTypes=bookTypeDAO.findAll();
        request.setAttribute("bookTypes", bookTypes);	//图书类型

        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        this.doGet(request, response);
    }

}
