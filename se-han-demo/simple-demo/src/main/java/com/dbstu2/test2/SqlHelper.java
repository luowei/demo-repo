/*
 * 这是一个对数据库进行操作的类(SqlHelper)
 */

package com.dbstu2.test2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SqlHelper {
	
	//定义操作数据库需要的东西
	PreparedStatement ps=null;
	Connection ct=null;
	ResultSet rs=null;
	String url="jdbc:microsoft:sqlserver://127.0.0.1:1433;databaseName=LiangshanHeros";
	String user="sa";
	String passwd="luowei";
	String driver="com.microsoft.jdbc.sqlserver.SQLServerDriver";
	
	//关闭数据库资源
	public void close()
	{
		//关闭资源
		try {
			if(rs!=null) rs.close();
			if(ps!=null) ps.close();
			if(ct!=null) ct.close();
			
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}
	//写一个不需要注入的方法
	public ResultSet queryExectue(String sql)
	{
		try {
			//1.加载驱动
			Class.forName(driver);
			//2.得到连接
			ct=DriverManager.getConnection(url,user,passwd);
			//3.创建ps
			ps=ct.prepareStatement(sql);
			
			rs=ps.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}finally
		{
			//关闭资源,???
		}
		return rs;
	}
	
	//查询数据库操作
	public ResultSet queryExectue(String sql,String []paras)
	{
		try {
			//1.加载驱动
			Class.forName(driver);
			//2.得到连接
			ct=DriverManager.getConnection(url,user,passwd);
			//3.创建ps
			ps=ct.prepareStatement(sql);
			//给ps的问号赋值
			for(int i=0;i<paras.length;i++)
			{
				ps.setString(i+1, paras[i]);
			}
			rs=ps.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}finally
		{
			//关闭资源,???
		}
		return rs;
	}
	
	//把增、删、改合到一起
	public boolean updExecute(String sql,String []paras)
	{
		boolean b=true;
		try {
			//1.加载驱动
			Class.forName(driver);
			//2.得到连接
			ct=DriverManager.getConnection(url,user,passwd);
			//3.创建ps
			ps=ct.prepareStatement(sql);
			//给ps的问号赋值
			for(int i=0;i<paras.length;i++)
			{
				ps.setString(i+1, paras[i]);
			}
			//4.执行操作
			if(ps.executeUpdate()!=1)
			{
				b=false;
			}
		} catch (Exception e) {
			b=false;
			e.printStackTrace();
		}finally
		{
			this.close();
		}
		return b;
	}
}
