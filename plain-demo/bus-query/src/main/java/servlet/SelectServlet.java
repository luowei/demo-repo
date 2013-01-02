package servlet;

import service.CarDb;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class SelectServlet extends HttpServlet{
    CarDb cardb=new CarDb();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String type=req.getParameter("type");
        //type为1的时候表示公交线路查询

        if("1".equals(type))
        {
            String name=req.getParameter("name");
            List list=cardb.getList(name);
            req.setAttribute("list", list);
            req.getRequestDispatcher("/CarSelect.jsp").forward(req, resp);
        }
        else if("2".equals(type)){
            String name=req.getParameter("name");
            List list=cardb.getList2(name);
            req.setAttribute("list", list);
            req.getRequestDispatcher("/CarSelect2.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String type=req.getParameter("type");
        //type为1的时候表示公交线路查询
        //type为2的时候表示公交站点查询
        //type为3的时候表示公交线路换乘查询
        if("1".equals(type))
        {
            String name=req.getParameter("name");
            List list=cardb.getList(name);
            req.setAttribute("list", list);
            req.getRequestDispatcher("/CarSelect.jsp").forward(req, resp);
        }else if("2".equals(type)){
            String name=req.getParameter("name");
            List list=cardb.getList2(name);
            req.setAttribute("list", list);
            req.getRequestDispatcher("/CarSelect2.jsp").forward(req, resp);
        }else if("3".equals(type)){

        }

    }

}
