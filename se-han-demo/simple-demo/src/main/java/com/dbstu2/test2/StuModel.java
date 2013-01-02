/*
 * 这是一个stu表的模型
 * 可以把对stu表的各种操作封装到该模型中
 */

package com.dbstu2.test2;
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
	
	
	//初始化连接数据库,查询,查询的本质就是初始化
	public void queryStu(String sql,String []paras)
	{
		SqlHelper sqlHelper=null;
		//中间
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
			sqlHelper=new SqlHelper();
			ResultSet rs=sqlHelper.queryExectue(sql, paras);
			
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
			sqlHelper.close();
		}
	}
	
	//更新学生,添加学生（增、删、改）
	@SuppressWarnings("finally")
	public boolean updStu(String sql,String []paras)//通过函数传入参数解决
	{
		//根据用户输入的sql语句，完成添加...
		//创建SqlHelper(如果程序并发性不考虑，可以把SqlHelper做成static)
		SqlHelper sqlHelper=new SqlHelper();
		return sqlHelper.updExecute(sql, paras);
	}
	
	//做一个构造函数，用于初始化我们的数据模型
	public StuModel()
	{
		
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
