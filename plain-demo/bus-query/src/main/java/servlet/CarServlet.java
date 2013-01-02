package servlet;

import pojo.Car;
import pojo.CarType;
import service.CarDb;
import service.CarTypeDb;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CarServlet extends HttpServlet{
    CarDb db=new CarDb();
    CarTypeDb cdb=new CarTypeDb();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String type=request.getParameter("type");
        if("list".equals(type)){
            //request客户端的请求对象
            //response响应客户端的对象
            System.out.println("doGet.....");
            //转发

            //获取集合
            List list=db.getList();
            //将集合放置到请求中
            request.setAttribute("list", list);
            request.getRequestDispatcher("/Car.jsp").forward(request, response);
        }
        else{
            List list=cdb.getList();
            request.setAttribute("list", list);
            request.getRequestDispatcher("/CarSave.jsp").forward(request, response);
        }
        //重定向
        //response.sendRedirect("");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("doPost.....");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");
        String type=request.getParameter("type");
        if("save".equals(type)){
            Car c=new Car();
            CarType t=new CarType();
            String name=request.getParameter("name");
            String remark=request.getParameter("remark");
            c.setName(name);
            c.setRemark(remark);

            String id=request.getParameter("typeid");
            if(!"".equals(id)){
                t.setId(Integer.parseInt(id));
                c.setCartype(t);
            }
            db.save(c);
            response.getWriter().print("操作成功");
        }

    }

}