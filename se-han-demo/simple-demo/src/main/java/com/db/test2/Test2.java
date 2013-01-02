/*
 * 在java中使用ddl语句(create,drop,backup...)
 */

package com.db.test2;
import java.sql.*;

public class Test2 {
	
	public static void main(String[] args) {
		//定义需要的对象
		PreparedStatement ps=null;
		Connection ct=null;
		ResultSet rs=null;
		
		try {
			//加载驱动
			Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");
			//得到连接
			ct=DriverManager.getConnection("jdbc:microsoft:sqlserver://127.0.0.1:1433;" +
					"databaseName=LiangshanHeros2","sa","luowei");
			//创建ps,创建数据库,创建表，备份数据库
			ps=ct.prepareStatement("create database vvv");
			ps=ct.prepareStatement("create table xxx(aa int)");
			ps=ct.prepareStatement("backup database aaa to disk='f:/123.bak'");
			//如果执行的是ddl语句
			boolean b=ps.execute();
			System.out.println("OK "+b);
			
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
