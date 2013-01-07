/**
 * 对数据库进行操作的类
 * 对数据库的操作，就是crud
 * 调用存储过程
 */

package com.mhl.db;
import java.util.*;
import java.sql.*;
public class SqlHelper {
	//定义需要的对象
	PreparedStatement ps=null;
	ResultSet rs=null;
	Connection ct=null;
	String driverName="com.microsoft.jdbc.sqlserver.SQLServerDriver";
	String url="jdbc:microsoft:sqlserver://127.0.0.1:1433;databaseName=MyRestaurantDB";
	String user="sa";
	String passwd="luowei";
	
	
	//构造函数，初始化ct
	public SqlHelper()
	{
		try {
			Class.forName(driverName);
			ct=DriverManager.getConnection(url,user,passwd);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//把对数据库的增，删，改写一个函数
	public boolean exeUpdate(String sql,String [] paras)
	{
		boolean b=true;
		try {
			ps=ct.prepareStatement(sql);
			//对sql的参数赋值
			for(int i=0;i<paras.length;i++)
			{
				ps.setString(i+1,paras[i]);
			}
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}
	
	public ResultSet query(String sql,String []paras) //查询
	{
		try {
			ps=ct.prepareStatement(sql);
			//对sql的参数赋值
			for(int i=0;i<paras.length;i++)
			{
				ps.setString(i+1,paras[i]);
			}
			rs=ps.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	//关闭资源
	public void close()
	{
		try {
			if(rs!=null) rs.close();
			if(ps!=null) ps.close();
			if(!ct.isClosed()) ct.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
