/*
 * 风格布局
 */
package com.swing.test3;
import java.awt.*;
import javax.swing.*;
public class Demo8_4 extends JFrame{
	
	int size=9;
	//定义组件
	JButton jbs[]=new JButton[size];
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//创建实例
		Demo8_4 demo8_4=new Demo8_4();
	}
	
	//构造函数
	public Demo8_4()
	{
		//创建组件
		for(int i=0;i<size;i++)
		{
			jbs[i]=new JButton(String.valueOf(i));
		}
		//设置风格布局
		this.setLayout(new GridLayout(3,3,10,10));
		//第1个参数是风格的行数，第2个参数是风格的列数
		
		//添加组件
		for(int i=0;i<size;i++)
		{
			this.add(jbs[i]);
		}
		
		//设置窗体属性
		this.setTitle("风格布局案例");
		this.setSize(300,200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(200,200);
		
		//显示
		this.setVisible(true);
	}

}
