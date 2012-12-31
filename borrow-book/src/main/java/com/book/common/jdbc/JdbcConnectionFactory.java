package com.book.common.jdbc;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class JdbcConnectionFactory {
	private static String username=null;
	private static String password=null;
	private static String url=null;
	private static String driver=null;
	private static Properties properties=new Properties();
	static{
		try {
			properties.load(JdbcConnectionFactory.class.getResourceAsStream("jdbc.properties"));
			username=properties.getProperty("username");
			password=properties.getProperty("password");
			url=properties.getProperty("url");
			driver=properties.getProperty("driver");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection()
	{
		Connection conn=null;
		try {
			Class.forName(driver);
			conn=DriverManager.getConnection(url,username,password);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void close(ResultSet rs,Statement st,Connection conn)
	{
		try {
			if(rs!=null)
				rs.close();
			if(st!=null)
				st.close();
			if(conn!=null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(ResultSet rs,PreparedStatement pst,Connection conn)
	{
		try {
			if(rs!=null)
				rs.close();
			if(pst!=null)
				pst.close();
			if(conn!=null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(Statement st,Connection conn)
	{
		try {
			if(st!=null)
				st.close();
			if(conn!=null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(PreparedStatement pst,Connection conn)
	{
		try {
			if(pst!=null)
				pst.close();
			if(conn!=null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String []args) {
		System.out.println(JdbcConnectionFactory.getConnection());
	}
}
