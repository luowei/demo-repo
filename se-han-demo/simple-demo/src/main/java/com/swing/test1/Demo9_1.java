/*
 * 功能：java绘图原理
 */

package com.swing.test1;
import java.awt.*;
import javax.swing.*;

public class Demo9_1 extends JFrame{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Demo9_1 demo9_1=new Demo9_1();
		
	}
	
	public Demo9_1()
	{
		MyPanel1 mp=new MyPanel1();
		
		this.add(mp);
		this.setSize(400,500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

}

//定义一个MyPanel(自定义的面板，用于绘图和现实绘图的区域)
class MyPanel1 extends JPanel
{
	//覆盖JPanel的paint方法
	public void paint(Graphics g) //Graphics是绘图的重要类，画笔类
	{//paint(Graphic g)绘制组件的外观，repaint()刷新组件的外观 
		//1.调用父类函数完成初始化
		//这句话，不能少
		super.paint(g);
		System.out.println("paint被调用");
		//以下情况paint()会被调用
			//1.窗口最小化，再最大化。
			//2.窗口的大小发生变化
			//3.repaint函数被调用
		
		//先画出一个圆
		g.drawOval(10, 10, 30, 30);
		
		//画出直线
		g.drawLine(80, 10, 40, 40);
		
		//画出矩形边框
		g.drawRect(10, 50, 40, 60);
		
		//设置颜色
		g.setColor(Color.blue);
		//填充矩形
		g.fillRect(10,50,40,60);
		
		g.setColor(Color.red);
		g.fillRect(70,70,40,40);//填充矩形
		g.fillOval(70, 10, 60, 40);//填充椭圆
		
		//在面板上画出图片,记住这种在面板上画图像的方法
		Image im=Toolkit.getDefaultToolkit().getImage
			(Panel.class.getResource("/刘亦菲2.jpg"));	
		//实现
		g.drawImage(im, 140, 10, 200, 300, this);
		
		//画出文字
		g.setColor(Color.red);//设置颜色
		g.setFont(new Font("微软简行楷",Font.BOLD,30));//设置字体
		g.drawString("祖国万岁", 5, 150);//画文字
		
		//画孤形
		g.drawArc(10, 180, 120, 300, 50, 100);
	}
}
