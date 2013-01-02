/**
 * 这个控制器，将处理用户的分页显示，用户的删除，修改，添加
 */

package user.controller;

import user.model.UserBeanCl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class UserClServlet extends HttpServlet {

    /**
     * The doGet method of the servlet. <br>
     *
     * This method is called when a form has its tag value method equals to get.
     *
     * @param request the request send by the client to the server
     * @param response the response send by the server to the client
     * @throws ServletException if an error occurred
     * @throws java.io.IOException if an error occurred
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //获得标志
        String flag=request.getParameter("flag");

        if(flag.equals("fenye")) //如果是分页
        {
            try {
                //得到用户希望显示的信息
                int pageNow=Integer.parseInt(request.getParameter("pageNow"));
                String u=request.getParameter("username");
                //System.out.println("UserClServlet得到的username="+u);

                //调用UserBeanCl
                UserBeanCl ubc=new UserBeanCl();

                //在跳转到wel.jsp页面时，就把要显示的数据给wel.jsp准备好
                ArrayList al=ubc.getUserByPage(pageNow); //经UserBeanCl处理后，获得当前页的数据
                int pageCount=ubc.getPageCount();
                int pageSize=ubc.getPageSize();
                //将al,pageCount,pageNow,pageSize放入request当中
                request.setAttribute("result", al);
                request.setAttribute("pageCount", pageCount+"");
                //因为第二个参数是对象，用int直接传递，后边使用时不好处理，所以把它转成String
                request.setAttribute("pageSize", pageSize+"");
                request.setAttribute("username", u);
                request.setAttribute("pageNow", pageNow+"");

                //重新跳转回wel.jsp
                request.getRequestDispatcher("wel.jsp").forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if(flag.equals("delUser")) //如果是删除
        {
            //完成删除
            //1.得到要删除的用户的id
            String userId=request.getParameter("userid");

            //创建UserBeanCl
            UserBeanCl ubc=new UserBeanCl();

            if(ubc.delUserById(userId))
            {
                //删除成功
                request.getRequestDispatcher("suc.jsp").forward(request, response);
            }else
            {
                //删除失败
                request.getRequestDispatcher("err.jsp").forward(request, response);
            }
        }else if(flag.equals("addUser")) //如果是添加
        {
            //完成添加用户
            //1.得到用户输入的信息
            String name=request.getParameter("userName");
            String passwd=request.getParameter("password");
            String email=request.getParameter("email");
            String grade=request.getParameter("grade");

            //创建UserBeanCl
            UserBeanCl ubc=new UserBeanCl();

            if(ubc.addUser(name, passwd, email, grade))
            {
                //添加成功
                request.getRequestDispatcher("suc.jsp").forward(request, response);
            }else
            {
                //添加失败
                request.getRequestDispatcher("err.jsp").forward(request, response);
            }
        }else if(flag.equals("updateUser")) //如果是修改
        {
            //System.out.println("进入了updateUser处理");
            //完成添加用户
            //1.得到用户输入的信息
            String id=request.getParameter("userId");
            String name=request.getParameter("userName");
            String passwd=request.getParameter("passWd");
            String email=request.getParameter("email");
            String grade=request.getParameter("grade");
            System.out.println("email="+email);
            //创建UserBeanCl
            UserBeanCl ubc=new UserBeanCl();

            if(ubc.updateUser(id, name, passwd, email, grade))
            {
                //修改成功
                System.out.println("进来了！");
                request.getRequestDispatcher("suc.jsp").forward(request, response);
            }else
            {
                //修改失败
                request.getRequestDispatcher("err.jsp").forward(request, response);
            }
        }
    }

    /**
     * The doPost method of the servlet. <br>
     *
     * This method is called when a form has its tag value method equals to post.
     *
     * @param request the request send by the client to the server
     * @param response the response send by the server to the client
     * @throws ServletException if an error occurred
     * @throws java.io.IOException if an error occurred
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        this.doGet(request, response);
    }

}
