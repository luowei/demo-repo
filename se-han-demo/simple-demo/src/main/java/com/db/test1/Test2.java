/*
 * PreparedStatement的使用
 * 1.PreparedStatement可以提高执行效率(因为它有预编译的功能)
 * 2.PreparedStatement可以防止sql注入，但是要求用?赋值的方式才可以
 */

package com.db.test1;
import java.sql.*;

public class Test2 {
	
	public static void main(String[] args) {
		Connection ct=null;	//定义一个Connection
		PreparedStatement ps=null;	//定义一个Statement
		ResultSet rs=null;
		try {
			//1.加载驱动(把需要的驱动程序加入内存)
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			
			//2.得到连接[指定连接到哪个数据源，用户名和密码]
			//如果配置数据源时，选择的是windows nt验证，则不需要加"sa","luowei"
			//即：Connection ct=DriverMannager.getConnection("jdbc:odbc:mytest");
			ct=DriverManager.getConnection("jdbc:odbc:mytest","sa","luowei");
			
			//3.创建PreparedStatement - ps
			//Statement用处是：主要用于发送sql语句到数据库
			ps=ct.prepareStatement("select * from dept where deptno=? and loc=?");
			//给？赋值,用？赋值可以防止注入漏洞
			ps.setInt(1, 20);
			ps.setString(2, "dallas");
			rs=ps.executeQuery();
			
			//4.查询
			//ResultSet结果集
			//循环取出
			while(rs.next())
			{
				int deptno=rs.getInt(1);
				String dname=rs.getString(2);
				String loc=rs.getString(3);
				System.out.println(deptno+" "+dname+" "+loc);
			}
			
			//使用PrepareStatement添加一条记录
			ps=ct.prepareStatement("insert into dept values(?,?,?)");
			ps.setInt(1, 50);
			ps.setString(2,"安全部");
			ps.setString(3, "北京");
			int i=ps.executeUpdate();
			if(i==1)
			{
				System.out.println("添加ok");
			}
			else
			{
				System.out.println("添加error");
			}
			
			//5.关闭资源，关闭顺序是，谁后创建，则谁先关闭
			try {
				if(rs!=null)
				{
					rs.close();
				}
				if(ps!=null)
				{
					ps.close();
				}
				if(ps!=null)
				{
					ct.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
