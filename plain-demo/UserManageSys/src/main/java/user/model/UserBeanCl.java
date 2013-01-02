/**
 * 这是一个处理类，有些人把它叫做BO,主要是封装对user表的各种操作，(主要
 * 是增，删，修，查...)
 */

package user.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UserBeanCl {

    private Statement sm=null;
    private ResultSet rs=null;
    private Connection ct=null;

    private int pageSize=5;
    public int getPageSize() {
        return pageSize;
    }

    private int rowCount=0; //行数
    private int pageCount=0;//页数

    /**
     * 验证用户是否存在
     * @param u
     * @param p
     * @return
     */
    public boolean checkUser(String u,String p)
    {
        boolean b=false;

        try {
            //到数据库去验证
            ct=new ConnDB().getConn();

            //3.创建Statement
            Statement sm=ct.createStatement();

            //4.查询
            ResultSet rs=sm.executeQuery("select passwd from users where username='"+u+"'");

            //验证
            if(rs.next())
            {
                //到此说明输入的用户名存在
                if(rs.getString(1).equals(p))
                {
                    b=true;
                    //合法，跳转wel.jsp
                    //如何将loginCl.jsp得到的数据传到下一个页面
                    //1.cookie 2.session 3.response.sendRedirect
                    //response.sendRedirect("wel.jsp?user="+u);//将用户发送过去
                }else
                {
                    //密码错误
                }

            }else
            {
                //用户名错误
                //不合法，跳转到login.jsp
                //response.sendRedirect("login.jsp?errNo=1");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            //关闭打开的各种资源
            this.close();
        }

        return b;
    }

    /**添加用户
     * @author luowei
     * @param name:用户名
     * @param  passwd:密码
     * @param  email:电子邮件
     * @param  grade:级别
     * @return boolean
     */
    public boolean addUser(String name,String passwd,String email,String grade)
    {
        boolean b=false;
        try {
            //得到连接
            ct=new ConnDB().getConn();
            sm=ct.createStatement();
            //执行
            int a=sm.executeUpdate("insert into users(username,passwd,email,grade) "
                    +"values('"+name+"','"+passwd+"','"+email+"','"+grade+"') ");
            if(a==1) //1表示是否是成功的添加了1条记录
            {
                //添加成功
                b=true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return b;
    }

    /**
     * 修改用户
     * @author luowei
     *
     */
    public boolean updateUser(String id,String name,String passwd,String email,String grade)
    {
        System.out.println("进入了updateUser函数中");
        boolean b=false;
        try {
            //得到连接
            ct=new ConnDB().getConn();
            sm=ct.createStatement();
            //执行
            int a=sm.executeUpdate("update users set username='"+name+"',passwd='"+passwd+"',email='"
                    +email+"',grade='"+grade+"' where useId='"+id+"'");
            if(a==1) //1表示是否是成功的修改了1条记录
            {
                //修改成功
                b=true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return b;
    }

    /**删除用户
     * @author luowei
     */
    public boolean delUserById(String id)
    {
        boolean b=false;
        //int Id=Integer.parseInt(id);
        try {
            //得到连接
            ct=new ConnDB().getConn();
            sm=ct.createStatement();
            //执行
            int a=sm.executeUpdate("delete from users where useId='"+id+"'");

            if(a==1)
            {
                //删除成功
                b=true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            this.close();
        }
        return b;
    }

    /**
     * @author luowei
     * @return 返回分页的总页数
     */
    public int getPageCount()
    {
        try {
            //得到连接
            ct=new ConnDB().getConn();

            //3.创建Statement
            Statement sm=ct.createStatement();

            //4.查询
            ResultSet rs=sm.executeQuery("select count(*) from users");

            if(rs.next())//将游标从第0条记录移到第1条记录，并判断是否为空
            {
                rowCount=rs.getInt(1);
            }

            //计算
            if(rowCount%pageSize==0)
            {
                pageCount=rowCount/pageSize;
            }else
            {
                pageCount=rowCount/pageSize+1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally
        {
            this.close();
        }
        return pageCount;
    }

    /**得到用户需要显示的用户信息(分页)
     * @author luowei
     * @param 当前页
     * @return
     */
    public ArrayList getUserByPage(int pageNow)
    {
        ArrayList al=new ArrayList();

        try {
            //得到连接
            ct=new ConnDB().getConn();

            //3.创建Statement
            Statement sm=ct.createStatement();

            //查询出需要显示的记录
            rs=sm.executeQuery("select * from users order by useId limit "+(pageSize*(pageNow-1))+","+pageSize);

            //开始将rs封装到ArrayList
            while(rs.next())
            {
                UserBean ub=new UserBean();
                ub.setUserId(rs.getInt(1));
                ub.setUsername(rs.getString(2));
                ub.setPasswd(rs.getString(3));
                ub.setEmail(rs.getString(4));
                ub.setGrade(rs.getInt(5));
                al.add(ub);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            this.close();
        }
        return al;
    }

    /**@author luowei
     * @see关闭资源
     * @return null
     *
     */
    public void close()
    {
        try {
            if(rs!=null)
            {
                rs.close();
                rs=null;
            }
            if(sm!=null)
            {
                sm.close();
                sm=null;
            }
            if(ct!=null)
            {
                ct.close();
                ct=null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
