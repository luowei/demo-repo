package com.vvvv.servlet.bookInfo;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vvvv.dao.BookInfoDAO;
import com.vvvv.entity.BookInfo;

public class FindAllBookInfoServlet extends HttpServlet
{
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        //共pageCount页
        int pageCount=0;
        //第nowPage页
        int nowPage=1;
        //pageSize 每页显示多少条
        int pageSize=5;
        //具体数据
        List<BookInfo> books=null;

        String strBookStatus=request.getParameter("bookStatus");
        String strpageSize=request.getParameter("pageSize");
        //System.out.println(strpageSize);
        String strbookTypeId=request.getParameter("bookTypeId");
        //System.out.println(strbookTypeId);
        //-----------------判断pageSize-----------------------
        if(strpageSize==null)
        {
            strpageSize="5";	//显示所有的
        }
        pageSize=Integer.parseInt(strpageSize);
        //-----------------判断图书的状态-----------------------
        if(strBookStatus==null)
        {
            strBookStatus="4";	//显示所有的
        }
        int bookStatus=Integer.parseInt(strBookStatus);

        BookInfoDAO bookInfoDAO=new BookInfoDAO();
        //books=bookInfoDAO.findAll(bookStatus);
        String strnowPage=request.getParameter("nowPage");
        if(strnowPage==null)
        {
            nowPage=1;
        }
        else
        {
            nowPage=Integer.parseInt(strnowPage);
        }
        //---------判断是否需要添加查询某一种图书类型的条件--------
        if(strbookTypeId!=null)
        {
            int bookTypeId=Integer.parseInt(strbookTypeId);
            books=bookInfoDAO.getNowPageData(nowPage, pageSize, bookStatus, bookTypeId);
        }
        else{
            books=bookInfoDAO.getNowPageData(nowPage, pageSize, bookStatus);
        }
        pageCount=bookInfoDAO.getPageCount(pageSize, bookStatus);

        request.setAttribute("pageSize", pageSize);
        request.setAttribute("bookStatus", bookStatus);
        request.setAttribute("pageCount", pageCount);
        request.setAttribute("nowPage", nowPage);
        request.setAttribute("pageSize", pageSize);
        request.setAttribute("books", books);

        request.getRequestDispatcher("/background/bookInfoList.jsp").forward(request, response);

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        this.doGet(request,response);
    }

}
