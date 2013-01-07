/*
 * JTable使用
 */

package com.dbstu;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.*;
import java.awt.event.*;

public class Test1 extends JFrame{

	//rowData用来存放行数据
	//columnNames存放列名
	Vector rowData,columnNames;
	JTable jt=null;
	JScrollPane jsp=null;
	
	public static void main(String[] args) {
		Test1 test1=new Test1();

	}

	//构造函数
	public Test1()
	{
		Vector columnNames=new Vector();
		//设置列名
		columnNames.add("学号");
		columnNames.add("名字");
		columnNames.add("性别");
		columnNames.add("年龄");
		columnNames.add("籍贯");
		columnNames.add("系别");
		
		rowData=new Vector();
		//rowData可以存放多行
		Vector hang=new Vector();
		hang.add("lw001");
		hang.add("孙悟空");
		hang.add("男");
		hang.add("500");
		hang.add("花果山");
		hang.add("少林派");
		
		//加入到rowData
		rowData.add(hang);
		
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
