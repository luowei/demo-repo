/*
 * 功能：坦克大战v1.0
 * 1.画出坦克
 */

package com.swing.test2;
import java.awt.*;
import javax.swing.*;

public class MyTankGame1 extends JFrame{

	MyPanel mp=null;
	public MyTankGame1()
	{
		mp=new MyPanel();
		
		this.add(mp);
		this.setSize(400,300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		MyTankGame1 mtg=new MyTankGame1();
	}

}

//我的面板
class MyPanel extends JPanel
{
	//定义一个我的坦克
	Hero hero=null;
	
	//构造函数
	public MyPanel()
	{
		hero=new Hero(10,10);
	}
	
	//重写paint函数
	public void paint(Graphics g)
	{
		super.paint(g); //这句不能少
		g.fillRect(0,0,400,300);//设置游戏面板背景
		this.drawTank(hero.getX(), hero.getY(), g, 0, 1);
	}
	
	//画出坦克的函数
	public void drawTank(int x,int y,Graphics g,int direct,int type)
	{
		//判断坦克的类型
		switch(type)
		{
		case 0:
			g.setColor(Color.cyan);
			break;
		case 1:
		g.setColor(Color.yellow);
		break;
		}
		
		//判断方向
		switch(direct)
		{
		case 0:	//向上
			//画出我的坦克(到时封装成一个函数)
			//1.画出左边的矩形
			g.fill3DRect(x, y, 5, 30,false);
			//2.画出右边的矩形
			g.fill3DRect(x+15, y, 5, 30,false);
			
			//3.画出中间矩形
			g.fill3DRect(x+5, y+5, 10, 20,false);
			
			//4.画出中间的圆形
			g.fillOval(x+5, y+10, 10, 10);
			
			//5.画出线
			g.drawLine(x+10, y+15, x+10, y);
			
			//画齿轮
			g.setColor(Color.darkGray);
			g.drawLine(x+1, y+2, x+4, y+2);
			g.drawLine(x+1, y+5, x+4, y+5);
			g.drawLine(x+1, y+8, x+4, y+8);
			g.drawLine(x+1, y+11, x+4, y+11);
			g.drawLine(x+1, y+14, x+4, y+14);
			g.drawLine(x+1, y+17, x+4, y+17);
			g.drawLine(x+1, y+20, x+4, y+20);
			g.drawLine(x+1, y+23, x+4, y+23);
			g.drawLine(x+1, y+27, x+4, y+27);
			
			g.drawLine(x+16, y+2, x+19, y+2);
			g.drawLine(x+16, y+5, x+19, y+5);
			g.drawLine(x+16, y+8, x+19, y+8);
			g.drawLine(x+16, y+11, x+19, y+11);
			g.drawLine(x+16, y+14, x+19, y+14);
			g.drawLine(x+16, y+17, x+19, y+17);
			g.drawLine(x+16, y+20, x+19, y+20);
			g.drawLine(x+16, y+23, x+19, y+23);
			g.drawLine(x+16, y+27, x+19, y+27);
			break;
		}
	}
}


//坦克类
class Tank
{
	//表示坦克的横坐标
	int x=0;
	//坦克纵坐标
	int y=0;
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Tank(int x,int y)
	{
		this.x=x;
		this.y=y;
	}
	
}

//我的坦克
class Hero extends Tank
{
	public Hero(int x,int y)
	{
		super(x,y);//用父类的构造函数初始化子类的成员变量
	}
}


