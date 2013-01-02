/*
 * jdbc方式操作数据库
 * 1.把java.sql.*;引入
 * 2.需要引入三个jar包
 * 3.如果取值按编号，则需要一一对应，如果是按名字取的，则顺序可以倒乱
 */

package com.db.test2;
import java.sql.*;

public class Test1 {

	public static void main(String[] args) {
		//PreparedStatement[火箭车]
		//定义需要的对象
		PreparedStatement ps=null;
		Connection ct=null;
		ResultSet rs=null;
		
		try {
			//初始化我们的对象
			//1.加载驱动
			Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");
			//2.得到连接
			ct=DriverManager.getConnection("jdbc:microsoft:sqlserver://" +
					"127.0.0.1:1433;databaseName=LiangshanHeros2","sa","luowei");
			//3.创建火箭车
			ps=ct.prepareStatement("select ename,sal,dname from emp,dept " +
					"where emp.deptno=dept.deptno");
			//4.执行[如果是增加，删除，修改 使用executeUpdate(),如果是查询使用executeQuery()]
			rs=ps.executeQuery();
			//循环取出,雇员的名字，雇员的薪水，部门名称
			while(rs.next())
			{
				//String name=rs.getString(1);
				//float sal=rs.getFloat(2);
				//int deptno=rs.getInt(3);
				String name=rs.getString("ename");
				float sal=rs.getFloat("sal");
				String deptno=rs.getString("dname");
				System.out.println(name+" "+sal+" "+deptno);
			}
			//添加，删除，修改
			ps=ct.prepareStatement("insert into dept values(?,?,?)");
			ps.setInt(1, 100);
			ps.setString(2, "财务部");
			ps.setString(3, "大学城");
			
			//执行[如果是增加，删除，修改 使用executeUpdate(),如果是查询使用executeQuery()]
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
