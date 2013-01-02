package com.swing.test3;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Demo8_1 extends JFrame //继承JFrame
{
	/**
	 * @param args
	 */
	
	public static void main(String[] args) 
	{
		Demo8_1 demo8_1=new Demo8_1();
		
	}
	
	//把需要的组件定义在这里
	JButton jb1=null; //定义一个按钮
	
	//构造函数
	public Demo8_1()
	{	
		////JFrame是一个顶层容器类(可以添加其它swing组件的类)
		//JFrame jf=new JFrame();

		//给窗体设置标题
		this.setTitle("Hello world");
		
		//设置大小，按像素
		this.setSize(200, 200);
		
		//设置初始位置
		this.setLocation(100,200);
		
		//创建一个按钮
		jb1=new JButton("我是一个按钮！");
		
		//添加JButton组件
		this.add(jb1);
		
		//设置当关闭窗口时，保证JVM也退出
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//显示
		this.setVisible(true);
	}
}
