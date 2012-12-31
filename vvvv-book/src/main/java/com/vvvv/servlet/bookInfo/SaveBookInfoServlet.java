package com.vvvv.servlet.bookInfo;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vvvv.dao.BookInfoDAO;
import com.vvvv.dao.Tool;
import com.vvvv.entity.BookInfo;

public class SaveBookInfoServlet extends HttpServlet
{
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		this.doPost(request,response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		//int bookId=Integer.parseInt(request.getParameter(""));
		String bookName=request.getParameter("bookName");
		int booktypeId=Integer.parseInt(request.getParameter("bookTypeId"));
		String pbName=request.getParameter("pbName");
		String author=request.getParameter("author");
		String context=request.getParameter("context");
		String smallImg=request.getParameter("smallImg");
		String bigImg=request.getParameter("bigImg");
		float price=Float.parseFloat(request.getParameter("price"));
		
		String strPbdate=request.getParameter("pbDate");
		System.out.println(strPbdate);
		Date pbdate=null;
		try
		{
			pbdate = Tool.conventStringToDate(strPbdate);
			
		} catch (ParseException e)
		{
			e.printStackTrace();
		}
		
	    java.sql.Date sqlDate=new java.sql.Date(pbdate.getTime());        
	    //java.sql.Time sTime=new java.sql.Time(pbdate.getTime());   
	    //java.sql.Timestamp stp=new java.sql.Timestamp(pbdate.getTime());
		
		float vvvvprice=Float.parseFloat(request.getParameter("vvvvPrice"));
		int bookStatus=Integer.parseInt(request.getParameter("bookStates"));
		
		BookInfoDAO bookInfoDAO=new BookInfoDAO();
		BookInfo bookInfo=new BookInfo(bookName, booktypeId, pbName, author, 
				context.trim(), smallImg, bigImg, price, sqlDate, vvvvprice, bookStatus);
		
		String msg="失败";
		String returnPath="/background/saveBookInfo.jsp";
		if(bookInfoDAO.save(bookInfo))
		{
			msg="成功";
			returnPath="/FindAllBookInfoServlet";
		}
		//提示消息
		request.setAttribute("msg", "新增图书"+msg);
		//返回路径
		//String servletPath=request.getContextPath(); 

		request.setAttribute("returnPath", returnPath);
		request.getRequestDispatcher("/background/message.jsp").forward(request, response);
	}

}
