package servlet;

import pojo.CarType;
import service.CarTypeDb;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 此类针对公车线路类型维护
 * @author Administrator
 *
 */
public class IndexServlet extends HttpServlet{
    CarTypeDb db=new CarTypeDb();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //request客户端的请求对象
        //response响应客户端的对象
        System.out.println("doGet.....");
        //转发

        //获取集合
        List list=db.getList();
        //将集合放置到请求中
        request.setAttribute("list", list);
        request.getRequestDispatcher("/Type.jsp").forward(request, response);
        //重定向
        //response.sendRedirect("");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("doPost.....");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");
        //获取提交的数据
        //getParameterValues是获取相同参数名的一组数据 也可以获取单个参数名的数据
        //getParameter是获取单个参数名的数据
        Object b=request.getParameter("type");
        if(b!=null){
            String type=(String)b;
            //判断是否为删除界面
            if(type.equals("delete")){
                Object obj=request.getParameterValues("ids");
                if(obj!=null){
                    String ids[]=request.getParameterValues("ids");//参数名 是提交页面的html控件的名称
                    db.remo(ids);
                    List list=db.getList();
                    //将集合放置到请求中
                    request.setAttribute("list", list);
                    request.getRequestDispatcher("/Type.jsp").forward(request, response);
                }
            }
            //else为添加界面
            else{

                CarType c=new CarType();
                c.setName(request.getParameter("name"));
                //主键一般自动增长型 模拟自动增长 获取最后一个元素得到ID 并且在此ID上加1
                //CarType t=(CarType)db.getList().get(db.getList().size()-1);
                //c.setId(t.getId()+1);
                db.save(c);
                response.getWriter().print("操作成功");
            }
        }
    }

    @Override
    public void service(ServletRequest arg0, ServletResponse arg1)
            throws ServletException, IOException {

        System.out.println("service.....");
        super.service(arg0, arg1);
    }

    @Override
    public void destroy() {
        System.out.println("destroy.....");
        super.destroy();
    }

    @Override
    public void init() throws ServletException {
        System.out.println("init.....");
        super.init();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("init(ServletConfig config).....");
        String text=config.getInitParameter("zhangsan");
        System.out.println("text:"+text);
        super.init(config);
    }

}
