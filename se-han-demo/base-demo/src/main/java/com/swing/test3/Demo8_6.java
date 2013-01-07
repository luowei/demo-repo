package com.swing.test3;
import java.awt.*;
import javax.swing.*;

public class Demo8_6 extends JFrame{

	/**
	 * @param args
	 */
	//定义组件
	JPanel jp1,jp2,jp3; 	//三个子面板
	JLabel jlb1,jlb2; 		//标签
	JButton jb1,jb2; 		//两个按钮
	JTextField jtf1,jpf1; //两个文本输入框
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Demo8_6 demo8_6=new Demo8_6();
	}

	//构造函数
	public Demo8_6()
	{
		jp1=new JPanel();
		jp2=new JPanel();
		jp3=new JPanel();
		
		jlb1=new JLabel("用户名：");
		jlb2=new JLabel("密码：");
		jb1=new JButton("登录");
		jb2=new JButton("取消");
		
		jtf1=new JTextField(10); 		//用户名文本框
		jpf1=new JPasswordField(10); 	//密码文本框
		
		//设置布局管理
		this.setLayout(new GridLayout(3,1));
		
		//加入各个组件
		jp1.add(jlb1);
		jp1.add(jtf1);
		
		jp2.add(jlb2);
		jp2.add(jpf1);
		
		jp3.add(jb1);
		jp3.add(jb2);
		
		//加入到JFrame
		this.add(jp1);
		this.add(jp2);
		this.add(jp3);
		
		this.setTitle("会员管理系统");
		this.setSize(250,130);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}
