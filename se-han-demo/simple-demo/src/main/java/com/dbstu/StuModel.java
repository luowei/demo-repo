/*
 * 这是一个stu表的模型
 * 可以把对stu表的各种操作封装到该模型中
 */

package com.dbstu;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.table.*;

public class StuModel extends AbstractTableModel{

	//rowData用来存放行数据
	//columnNames存放列名
	Vector rowData,columnNames;
	
	//定义操作数据库需要的东西
	PreparedStatement ps=null;
	Connection ct=null;
	ResultSet rs=null;
	
	public void init(String sql)
	{
		if(sql.equals(""))
		{
			sql="select * from stu";
		}
		columnNames=new Vector();
		//设置列名
		columnNames.add("学号");
		columnNames.add("名字");
		columnNames.add("性别");
		columnNames.add("年龄");
		columnNames.add("籍贯");
		columnNames.add("系别");
		
		rowData=new Vector();
		try {
			//1.加载驱动
			Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");
			ct=DriverManager.getConnection("jdbc:microsoft:sqlserver://127.0.0.1:1433;" +
					"databaseName=LiangShanHeros","sa","luowei");
			ps=ct.prepareStatement(sql);
			rs=ps.executeQuery();
			
			while(rs.next())
			{
				//rowData可以存放多行
				Vector hang=new Vector();
				hang.add(rs.getString(1));
				hang.add(rs.getString(2));
				hang.add(rs.getString(3));
				hang.add(rs.getInt(4));
				hang.add(rs.getString(5));
				hang.add(rs.getString(6));
				
				//加入到rowData
				rowData.add(hang);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally
		{
			//关闭资源
			try {
				if(rs!=null) rs.close();
				if(ps!=null) ps.close();
				if(ct!=null) ct.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void addStu(String sql)
	{
		//根据用户输入的sql语句，完成添加...
	}
	
	//通过传递的sqL语句来获得数据模型
	public StuModel(String sql)
	{
		this.init(sql);
	}
	
	//做一个构造函数，用于初始化我们的数据模型
	public StuModel()
	{
		this.init("");
	}
	
	@Override //得到共有多少列
	public int getColumnCount() {
		//System.out.println("getColumncount");
		return this.columnNames.size();
	}

	@Override //得到共有多少行
	public int getRowCount() {
		// 
		return this.rowData.size();
	}

	@Override //得到某行某列的数据
	public Object getValueAt(int rowIndex, int columnIndex) {
		//return null; 
		return ((Vector)this.rowData.get(rowIndex)).get(columnIndex);
	}

	@Override
	public String getColumnName(int column) {
		// 重写这个方法，修改默认的列名
		return (String)this.columnNames.get(column);
	}
	
}
