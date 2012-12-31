package com.vvvv.servlet.bookInfo;

import java.io.IOException;
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

public class ShowFrontBookInfoServlet extends HttpServlet
{
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		int bookId=Integer.parseInt(request.getParameter("bookId"));
		
		BookInfoDAO bookInfoDAO=new BookInfoDAO();
		BookInfo book=new BookInfo();
		book=bookInfoDAO.findById(bookId);
		request.setAttribute("book",book);
		
		/*
		request.setAttribute("bookId",bookInfo.getBookId());
		request.setAttribute("bookName", bookInfo.getBookName());
		request.setAttribute("booktypeId", bookInfo.getBooktypeId());
		request.setAttribute("pbName",bookInfo.getPbName());
		request.setAttribute("author",bookInfo.getAuthor());
		request.setAttribute("context", bookInfo.getContext());
		request.setAttribute("smallImg",bookInfo.getSmallImg());
		request.setAttribute("bigImg",bookInfo.getBigImg());
		request.setAttribute("price",bookInfo.getPrice());
		
		String pbDate=Tool.conventDateToString(bookInfo.getPbdate());
		request.setAttribute("pbDate",pbDate);
		
		request.setAttribute("vvvvprice",bookInfo.getVvvvprice());
		request.setAttribute("bookStatus",bookInfo.getBookStatus());
		*/
		
		BookTypeDAO bookTypeDAO=new BookTypeDAO();
		List<BookType> bookTypes=bookTypeDAO.findAll();
		request.setAttribute("bookTypes", bookTypes);
		
		request.getRequestDispatcher("/bookInfomation.jsp").forward(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		this.doGet(request,response);
	}

}
