/*
 * 演示使用jdbc-odbc桥连方式操作数据库
 * 1.配置数据源
 * 2.在程序中去连接数据源
 */

package com.db.test1;
import java.sql.*;

public class Test1 {
	
	public static void main(String[] args) {
		Connection ct=null;	//定义一个Connection
		Statement sm=null;	//定义一个Statement
		try {
			//1.加载驱动(把需要的驱动程序加入内存)
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			
			//2.得到连接[指定连接到哪个数据源，用户名和密码]
			//如果配置数据源时，选择的是windows nt验证，则不需要加"sa","luowei"
			//即：Connection ct=DriverMannager.getConnection("jdbc:odbc:mytest");
			ct=DriverManager.getConnection("jdbc:odbc:mytest","sa","luowei");
			
			//3.创建Statement或者PreparedStatement[区别]
			//Statement用处是：主要用于发送sql语句到数据库
			sm=ct.createStatement();
			
			//4.执行(crud,创建数据库，备份数据库，删除数据库....)
				//1.演示添加一条数据到dept表
			//executeUpdate可以执行cud操作(添加，删除，修改)
			int i=sm.executeUpdate("insert into dept values('50','保安部','北京')");
			if(i==1)
			{
				System.out.println("添加ok");
			}
			else
			{
				System.out.println("添加error");
			}
				//2.修改从dept表记录
			int k=sm.executeUpdate("update dept set loc='北京 ' where deptno='40'");
			if(k==1)
			{
				System.out.println("更新成功");
			}
			else
			{
				System.out.println("更新不成功");
			}
				//3.演示从dept表中删除一条记录
			int j=sm.executeUpdate("delete from dept where deptno='50'");
			if(j==1)
			{
				System.out.println("删除ok");
			}
			else
			{
				System.out.println("删除error");
			}
			//查询，显示所有部门
			//ResultSet结果集
			ResultSet rs=sm.executeQuery("select * from dept;");
			//rs指向结果集的第一行的前一行
			//循环取出
			while(rs.next())
			{
				int deptno=rs.getInt(1);
				String dname=rs.getString(2);
				String loc=rs.getString(3);
				System.out.println(deptno+" "+dname+" "+loc);
			}
//			//试图取出1行的第一列
//			rs.next();
//			int a=rs.getInt(1);//取出第1列
//			System.out.println(a);
//			//取出第一行第2列
//			String b=rs.getString(2);
//			System.out.println(b);
			
			//5.关闭资源，关闭顺序是，谁后创建，则谁先关闭
			try {
				if(sm!=null)
				{
					rs.close();
					sm.close();
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
