/*
 * 完成一个min版本的学生管理系统
 */

package com.dbstu;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;

import com.test1.StuModel;

import java.sql.*;

public class Test3 extends JFrame implements ActionListener{

	//定义一些控件
	JPanel jp1,jp2;
	JLabel jl1;
	JButton jb1,jb2,jb3,jb4;
	JTable jt;
	JScrollPane jsp;
	JTextField jtf;
	
	
	
	
	public static void main(String[] args) {
		Test3 test3=new Test3();

	}

	//构造函数
	public Test3()
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
		jb4=new JButton("请输入名字");
		
		//把各个按钮加入到jp2
		jp2.add(jb2);
		jp2.add(jb3);
		jp2.add(jb4);
		
		//中间
		
		//创建一个数据模型对象
		StuModel sm=new StuModel();
		
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
			System.out.println("用户想查询！");
			//...
			//查询数据库，更新JTable,这样大量的代码重复，解决办法：
			//把对表操作封装成一个类，可以完成对表的操作
			//把对表的数据装到StuModel中，就可以比较简单的完成查询
			String name=this.jtf.getText().trim();
			//写一个sql语句
			String sql="select * from stu where stuname='"+name+"'";
			//构建新的数据模型类，并更新
			StuModel sm=new StuModel(sql);
			//更新JTable
			jt.setModel(sm);
		}
		//当用户点击添加
		else if(e.getSource()==jb2)
		{
			StuAddDialog sa=new StuAddDialog(this,"添加学生",true);
			
			//重新再获得新的数据模型
		}
	}
}
