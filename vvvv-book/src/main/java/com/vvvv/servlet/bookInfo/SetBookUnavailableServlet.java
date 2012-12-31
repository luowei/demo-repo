package com.vvvv.servlet.bookInfo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vvvv.dao.BookInfoDAO;

public class SetBookUnavailableServlet extends HttpServlet
{
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {

        int bookId = Integer.parseInt(request.getParameter("bookId"));
        BookInfoDAO bookInfoDAO = new BookInfoDAO();

        String msg = "失败";
        if (bookInfoDAO.updateIsDelete(bookId,3)) {
            msg = "成功";
        }
        //提示消息
        request.setAttribute("msg", "图书信息不可用（删除）" + msg);
        //返回路径
        request.setAttribute("returnPath", "/FindAllBookInfoServlet");
        request.getRequestDispatcher("/background/message.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        this.doGet(request,response);
    }

}
