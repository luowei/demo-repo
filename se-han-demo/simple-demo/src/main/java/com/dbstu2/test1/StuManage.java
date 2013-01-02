/*
 * 完成一个min版本的学生管理系统
 * 1.查询任务
 * 2.添加一个学生
 */

package com.dbstu2.test1;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;

import com.test1.StuModel;

import java.sql.*;

public class StuManage extends JFrame implements ActionListener{

	//定义一些控件
	JPanel jp1,jp2;
	JLabel jl1;
	JButton jb1,jb2,jb3,jb4;
	JTable jt;
	JScrollPane jsp;
	JTextField jtf;
	StuModel sm;
	
	//定义操作数据库需要的东西
	PreparedStatement ps=null;
	Connection ct=null;
	ResultSet rs=null;
	
	
	
	public static void main(String[] args) {
		StuManage test3=new StuManage();

	}

	//构造函数
	public StuManage()
	{
		jp1=new JPanel();
		jtf=new JTextField(10);
		jb1=new JButton("查询");
		jb1.addActionListener(this);//添加监听
		
		jl1=new JLabel("请输入名字：");
		
		//把各个控件加入jp1
		jp1.add(jl1);
		jp1.add(jtf);
		jp1.add(jb1);
		
		jp2=new JPanel();
		
		jb2=new JButton("添加");
		jb2.addActionListener(this);
		jb3=new JButton("修改");
		jb3.addActionListener(this);
		jb4=new JButton("删除");
		jb4.addActionListener(this);
		
		//把各个按钮加入到jp2
		jp2.add(jb2);
		jp2.add(jb3);
		jp2.add(jb4);
		
		//中间
		
		//创建一个数据模型对象
		sm=new StuModel();
		
		//初始化JTable
		jt=new JTable(sm);
		
		//初始化jsp JScrollPane
		jsp=new JScrollPane(jt);
		
		//把jsp放入到jframe
		this.add(jsp);
		this.add(jp1,"North");
		this.add(jp2,"South");
		
		this.setSize(400,300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//判断是哪个按钮被点击
		if(e.getSource()==jb1)
		{
			//对用户点击添加按钮的响应动作
			System.out.println("用户想查询！");
			//...
			//查询数据库，更新JTable,这样大量的代码重复，解决办法：
			//把对表操作封装成一个类，可以完成对表的操作
			//把对表的数据装到StuModel中，就可以比较简单的完成查询
			String name=this.jtf.getText().trim();
			//写一个sql语句
			String sql="select * from stu where stuName='"+name+"'";
			//构建新的数据模型类，并更新
			sm=new StuModel(sql);
			//更新JTable
			jt.setModel(sm);
		}
		//当用户点击添加
		else if(e.getSource()==jb2)
		{
			StuAddDialog sa=new StuAddDialog(this,"添加学生",true);
			
			//重新再获得新的数据模型
			sm=new StuModel();
			//更新JTable
			jt.setModel(sm);
		}
		else if(e.getSource()==jb3)
		{
			//用户希望修改
			int rowNum=this.jt.getSelectedRow();
			if(rowNum==-1)
			{
				//提示
				JOptionPane.showMessageDialog(this, "请选择一行");
				return ;
			}
			//显示修改对话框
			new StuUpdateDialog(this,"修改学生",true,sm,rowNum);
			
			//重新再获得新的数据模型
			sm=new StuModel();
			//更新JTable
			jt.setModel(sm);
		}
		
		else if(e.getSource()==jb4)
		{
			//说明用户希望删除记录
			//1.得到该学生的id
			int rowNum=this.jt.getSelectedRow();//返回用户点中的行，如果没有选返返-1
			if(rowNum==-1)
			{
				//提示
				JOptionPane.showMessageDialog(this,"请选择一行");
				return ;
			}
			//得到学生编号
			String stuId=(String)sm.getValueAt(rowNum, 0);
			//System.out.println("id="+stuId);
			//连接数据库，完成删除任务
			try {
				Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");
				ct=DriverManager.getConnection("jdbc:microsoft:sqlserver://127.0.0.1:1433;" +
						"databaseName=LiangshanHeros","sa","luowei");
				ps=ct.prepareStatement("delete from stu where stuid=?");
				ps.setString(1, stuId);
				ps.executeUpdate();
			} catch (Exception e2) {
				e2.printStackTrace();
			}finally{
				//关闭资源
				try {
					if(rs!=null) rs.close();
					if(ps!=null) ps.close();
					if(ct!=null) ct.close();
				} catch (Exception e3) 
				{
					e3.printStackTrace();
				}
			}
			//更新数据模型
			sm=new StuModel();
			//更新JTable
			jt.setModel(sm);
		}
	}
}
