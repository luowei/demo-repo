/*
 * 功能：流式：流式布局案例
 */

package com.swing.test3;
import java.awt.*;
import javax.swing.*;

public class Demo8_3 extends JFrame{

	/**
	 * @param args
	 */
	//定义需要的组件
	JButton jb1,jb2,jb3,jb4,jb5,jb6;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Demo8_3 demo8_3 =new Demo8_3();

	}
	
	//构造函数
	public Demo8_3()
	{
		//创建组件
		jb1=new JButton("关羽");
		jb1.setSize(100, 100);
		jb2=new JButton("张飞");
		jb3=new JButton("赵云");
		jb4=new JButton("马超");
		jb5=new JButton("黄忠");
		jb6=new JButton("魏延");
		
		//添加组件
		this.add(jb1);
		this.add(jb2);
		this.add(jb3);
		this.add(jb4);
		this.add(jb5);
		this.add(jb6);
		
		//设置布局管理器
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		//设置窗体属性
		this.setTitle("流式布局案例");
		this.setSize(350,200);
		this.setLocation(200,200);
		
		//禁止用户改变窗体大小
		this.setResizable(false);
		
		 this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 
		 //显示
		 this.setVisible(true);
	}
}
