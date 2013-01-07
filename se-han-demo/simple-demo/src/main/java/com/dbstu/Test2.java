/*
 * 从数据库中取出学生信息
 */

package com.dbstu;

import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.sql.*;

public class Test2 extends JFrame{

	//rowData用来存放行数据
	//columnNames存放列名
	Vector rowData,columnNames;
	JTable jt=null;
	JScrollPane jsp=null;
	
	//定义操作数据库需要的东西
	PreparedStatement ps=null;
	Connection ct=null;
	ResultSet rs=null;
	
	
	public static void main(String[] args) {
		Test2 test2=new Test2();

	}

	public Test2()
	{
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
			ps=ct.prepareStatement("select * from stu");
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
		
		
		//初始化JTable
		jt=new JTable(rowData,columnNames);
		
		//初始化jsp JScrollPane
		jsp=new JScrollPane(jt);
		
		//把jsp放入到jframe
		this.add(jsp);
		this.setSize(400,300);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}
