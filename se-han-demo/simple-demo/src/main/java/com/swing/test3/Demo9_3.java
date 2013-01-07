/*
 * 功能：java事件处理机制
 * 
 */

/*
 * 1.一个类要实现监听的步骤
 * 	a.实现相应的接口[KeyListener,MouseListener,ActionListener,WindowListener,]
 * 	b.把接口的处理方法根据需要重新编写（override）
 * 	c.在事件源注册监听
 * 	d.事件传递是靠事件对象
 */
package com.swing.test3;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Demo9_3 extends JFrame implements ActionListener  //让窗体对象实现对事件的监听
{

	//定义一个panel
	JPanel mp=null;
	JButton jb1=null;
	JButton jb2=null;
	
	public static void main(String[] args) {
		Demo9_3 demo9_3=new Demo9_3();
	}
	
	public Demo9_3()
	{
		//初始化一个面板和两个按钮
		mp=new JPanel();
		jb1=new JButton("黑色");
		jb2=new JButton("红色");
		
		Cat myCat1=new Cat();
		
		//注册监听
		jb1.addActionListener(this);//让窗体对象监听
		jb1.addActionListener(myCat1);//让猫对象监听
		//指定action命令
		jb1.setActionCommand("blackbtn");
		
		jb2.addActionListener(this);//添加事件监听，让窗体对象监听
		jb2.addActionListener(myCat1);//让猫对象监听
		jb2.setActionCommand("redbtn");
		
		this.add(jb1,BorderLayout.NORTH);//将按钮jb1添加到窗体北边
		mp.setBackground(Color.black);//设置面板颜色
		this.add(mp);
		this.add(jb2,BorderLayout.SOUTH);//将按钮jb2添加到窗体南边
		
		this.setSize(200,150); //设置窗体大小
		this.setLocation(400,300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}

	//对事件处理的方法
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("OK");
		
		//判断是哪个按钮被点击
		if(e.getActionCommand().equals("blackbtn"))
		{
			System.out.println("你点击黑色按钮了！");
			mp.setBackground(Color.BLACK);
		}
		else if(e.getActionCommand().equals("redbtn"))
		{
			System.out.println("你点击红色按钮了！");
			mp.setBackground(Color.RED);
		}
		else
		{
			System.out.println("不知道！");
		}
	}
}

//任何一个类，只要他实现了相应的接口，就可以去临界听某个事件源
class Cat implements ActionListener //让猫也现实对事件的监听
{
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("blackbtn"))
		{
			System.out.println("猫监听到你按下了黑色按钮！");
		}
		else if(e.getActionCommand().equals("redbtn"))
		{
			System.out.println("猫监听到你按下了红色按钮！");
		}
		else
		{
			System.out.println("猫没有监听到按钮消息！");
		}
	}
}
