/**
 * 这是一个控制器，主要完成对用户身份的验证
 * 控制器本身是不会去完成业务逻辑，它主要是去调用模型完成对数据的处理
 */

package user.controller;

import user.model.UserBeanCl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class LoginClServlet extends HttpServlet {

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

        //得到用户名和密码
        String u=request.getParameter("username");
        //u=new String(u.getBytes("iso-8859-1"),"gb2312");//将用户名转码，解决中文乱码而导致的验证错误
        String p=request.getParameter("passwd");
        System.out.println("username="+u+" password="+p);

        //使用模型(UserBeanCl)，完成对用户的验证
        //1.创建一个UserBeanCl对象
        UserBeanCl ubc=new UserBeanCl();
        //调用方法
        if(ubc.checkUser(u, p))
        {
            //System.out.println("使用了servlet控制器完成验证！");

            //在跳转到wel.jsp页面时，就把要显示的数据给wel.jsp准备好
            ArrayList al=ubc.getUserByPage(1);
            int pageCount=ubc.getPageCount();
            int pageSize=ubc.getPageSize();
            //将al,pageCount,pageNow,pageSize放入request当中
            request.setAttribute("result", al);
            request.setAttribute("pageCount", pageCount+"");
            //因为第二个参数是对象，用int直接传递，后边使用时不好处理，所以把它转成String
            request.setAttribute("pageSize", pageSize+"");
            request.setAttribute("pageNow", "1");

            //合法
            //response.sendRedirect("wel.jsp?username="+u);
            //因为sendRedirect方法效率不高，所以一般多是用转发的方法

            //将用户名放入session,以备后用
            request.getSession().setAttribute("myName", u);

            //这种方法的效率高，同时保证了request中的原有对象还可以在下一面页使用
            //所以就不需要加(?username="+u)了
            request.getRequestDispatcher("Main.jsp").forward(request, response);
        }else
        {
            //不合法
            request.getRequestDispatcher("login.jsp").forward(request, response);
            System.out.println("LoginClServlet验证不合法");
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

        //合二为一
        this.doGet(request, response);
    }

}
