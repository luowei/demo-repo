/*
 * 功能：坦克大战v2.0
 * 1.画出坦克
 * 2.我的坦克可以上下左右移动
 */

package com.thread.test4;
import java.awt.*;
import javax.swing.*;

import java.awt.event.*;
import java.util.*;

public class MyTankGame2 extends JFrame{

	MyPanel mp=null;
	
	//构造函数
	public MyTankGame2()
	{
		mp=new MyPanel();
		
		//启动mp线程
		Thread t=new Thread(mp);
		t.start();
		this.add(mp);
		
		//注册监听
		this.addKeyListener(mp);
		
		this.setSize(400,300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		MyTankGame2 mtg=new MyTankGame2();
	}

}

//我的面板
class MyPanel extends JPanel implements KeyListener,Runnable
{
	//定义一个我的坦克
	Hero hero=null;
	
	//定义敌人的坦克组
	Vector<EnemyTank>ets=new Vector<EnemyTank>();
	
	int enSize=3;
	
	//构造函数
	public MyPanel()
	{
		hero=new Hero(100,200);
		
		//初始化敌人的坦克
		for(int i=0;i<enSize;i++)
		{
			//创建一个辆敌人的坦克对象
			EnemyTank et=new EnemyTank((i+1)*50,0);
			et.setColor(0);//设置颜色
			et.setDirect(2);
			//加入
			ets.add(et);
			
		}
	}
	
	//重写paint函数
	public void paint(Graphics g)
	{
		super.paint(g); //这句不能少
		g.fillRect(0,0,400,300);//设置游戏面板背景
		
		//画出自己的坦克
		this.drawTank(hero.getX(), hero.getY(), g, this.hero.direct, 1);
		
		//画出子弹
		if(hero.s!=null&&hero.s.isLive==true)
		{
			g.setColor(Color.WHITE);//设置子弹颜色
			g.fill3DRect(hero.s.x, hero.s.y, 3, 3, true);
		}
		
		//画出敌人的坦克
		for(int i=0;i<ets.size();i++)
		{
			this.drawTank(ets.get(i).getX(), ets.get(i).getY(), g, ets.get(i).getDirect(), 0);
		}
	}
	
	//画出坦克的函数(扩展)
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
		case 0: //向右
			//画出上面的矩形
			g.fill3DRect(x, y, 30, 5, false);
			//画出下面的矩形
			g.fill3DRect(x, y+15, 30, 5, false);
			//画出中间的矩形
			g.fill3DRect(x+5, y+5, 20, 10, false);
			//画出圆形
			g.fillOval(x+10, y+5, 10, 10);
			//画出线
			g.drawLine(x+15, y+10, x+30, y+10);
			//画齿轮
			g.setColor(Color.darkGray);
			g.drawLine(x+2, y+1, x+2, y+4);
			g.drawLine(x+5, y+1, x+5, y+4);
			g.drawLine(x+8, y+1, x+8, y+4);
			g.drawLine(x+11, y+1, x+11, y+4);
			g.drawLine(x+14, y+1, x+14, y+4);
			g.drawLine(x+17, y+1, x+17, y+4);
			g.drawLine(x+20, y+1, x+20, y+4);
			g.drawLine(x+23, y+1, x+23, y+4);
			g.drawLine(x+26, y+1, x+26, y+4);
			
			g.drawLine(x+2, y+16, x+2, y+19);
			g.drawLine(x+5, y+16, x+5, y+19);
			g.drawLine(x+8, y+16, x+8, y+19);
			g.drawLine(x+11, y+16, x+11, y+19);
			g.drawLine(x+14, y+16, x+14, y+19);
			g.drawLine(x+17, y+16, x+17, y+19);
			g.drawLine(x+20, y+16, x+20, y+19);
			g.drawLine(x+23, y+16, x+23, y+19);
			g.drawLine(x+26, y+16, x+27, y+19);
			break;
		case 1:	//向左
			//画出上面的矩形
			g.fill3DRect(x, y, 30, 5, false);
			//画出下面的矩形
			g.fill3DRect(x, y+15, 30, 5, false);
			//画出中间的矩形
			g.fill3DRect(x+5, y+5, 20, 10, false);
			//画出圆形
			g.fillOval(x+10, y+5, 10, 10);
			//画出线
			g.drawLine(x+15, y+10, x, y+10);
			//画齿轮
			g.setColor(Color.darkGray);
			g.drawLine(x+2, y+1, x+2, y+4);
			g.drawLine(x+5, y+1, x+5, y+4);
			g.drawLine(x+8, y+1, x+8, y+4);
			g.drawLine(x+11, y+1, x+11, y+4);
			g.drawLine(x+14, y+1, x+14, y+4);
			g.drawLine(x+17, y+1, x+17, y+4);
			g.drawLine(x+20, y+1, x+20, y+4);
			g.drawLine(x+23, y+1, x+23, y+4);
			g.drawLine(x+26, y+1, x+26, y+4);
			
			g.drawLine(x+2, y+16, x+2, y+19);
			g.drawLine(x+5, y+16, x+5, y+19);
			g.drawLine(x+8, y+16, x+8, y+19);
			g.drawLine(x+11, y+16, x+11, y+19);
			g.drawLine(x+14, y+16, x+14, y+19);
			g.drawLine(x+17, y+16, x+17, y+19);
			g.drawLine(x+20, y+16, x+20, y+19);
			g.drawLine(x+23, y+16, x+23, y+19);
			g.drawLine(x+26, y+16, x+27, y+19);
			break;
		case 2:	//向下
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
			g.drawLine(x+10, y+15, x+10, y+30);
			
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
		case 3:	//向上
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

	@Override
	public void keyPressed(KeyEvent e) //键按下处理
	{
		//a表示向左，s表示向上，w表示向上，d表示向右
		if(e.getKeyCode()==KeyEvent.VK_D)
		{
			this.hero.setDirect(0);//设置我的坦克的方向,向右
			this.hero.moveRight();
		}
		else if(e.getKeyCode()==KeyEvent.VK_A)
		{
			this.hero.setDirect(1);//向左
			this.hero.moveLeft();
		}
		else if(e.getKeyCode()==KeyEvent.VK_S)
		{
			this.hero.setDirect(2);//向下
			this.hero.moveDown();
		}
		else if(e.getKeyCode()==KeyEvent.VK_W)
		{
			this.hero.setDirect(3);//向上
			this.hero.moveUp();
		}
		
		if(e.getKeyCode()==KeyEvent.VK_J)
		{
			//判断玩家是否按下j
			//发射子弹
			this.hero.shotEnemy();
		}
		//重绘Panel
		this.repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) 
	{
		
	}

	@Override
	public void keyTyped(KeyEvent e) 
	{
		
	}

	@Override
	public void run() {
		//每隔100毫秒去重绘
		while(true)
		{
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//重绘
			this.repaint();
		}
		
	}
}




