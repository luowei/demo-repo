/**
 * 人事的数据模型类，完成对人事表的各种操作
 */

package com.mhl.model;
import javax.swing.table.*;

import java.sql.*;
import java.util.*;

import com.mhl.db.SqlHelper;
import com.mhl.model.*;

public class EmpModel extends AbstractTableModel{

	Vector<String> colums;
	Vector<Vector> rows;
	
	//完成增，删，改的方法
	//删,依据员工号
	public boolean deEmpById(String empId)
	{
		boolean b=true;
		String sql="delete from clerkInfo where cleId=?";
		String []paras={empId};
		SqlHelper sh=new SqlHelper();
		try {
			b=sh.exeUpdate(sql, paras);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			sh.close();
		}
		return b;
		
	}
	
	//写一个方法，用于查询需要显示的人事信息
	//query方法不通用。。。，对query做一个修改，让它具有更好的通用性
	public void query(String sql,String paras[])
	{
		//初始化列
		this.colums=new Vector<String>();
		
		this.rows=new Vector<Vector>();
		//创建SqlHelper对象
		SqlHelper sh=new SqlHelper();
		ResultSet rs=sh.query(sql, paras);
		
		try {
			//rs对象中可以得到一个ResultSetMetaData
			ResultSetMetaData rsmd=rs.getMetaData();
			//rsmd可以得到结果有多少列，而且可以知道每列的名字
			for(int i=0;i<rsmd.getColumnCount();i++)
			{
				this.colums.add(rsmd.getColumnName(i+1));
				//得到第i+1列的列名，并存到colums中
			}
			
			//把rs的结果放入到rows
			while(rs.next())
			{
				Vector<String> temp=new Vector<String>();
				for(int i=0;i<rsmd.getColumnCount();i++)
				{
					temp.add(rs.getString(i+1));
				}
				rows.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			sh.close();
		}
	}
	
	@Override //返回列数
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return this.colums.size();
	}

	@Override //返回行数
	public int getRowCount() {
		// TODO Auto-generated method stub
		return this.rows.size();
	}

	@Override //返回字段的值
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return ((Vector)rows.get(rowIndex)).get(columnIndex);
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.AbstractTableModel#getColumnName(int)
	 */
	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return this.colums.get(column).toString();
	}
	
}
