/*
 * 功能：加深对事件处理机制的理解
 * 1.通过上下左右键，来控制一个小球的位置
 */

package com.swing.test4;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.awt.event.KeyListener;

public class Demo9_4 extends JFrame{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Demo9_4 demo9_4=new Demo9_4();

	}
	
	//构造函数
	public Demo9_4()
	{
		MyPanel mp=new MyPanel();
		
		//mp加入到JFrame
		this.add(mp);
		
		//实现监听
		this.addKeyListener(mp);
		
		this.setSize(400,300);
		this.setLocation(300,200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}

//定义自己的面板
class MyPanel extends JPanel implements KeyListener
{
	int x=10;
	int y=10;
	public void paint(Graphics g)
	{
		super.paint(g);
		g.fillOval(x, y, 20, 20);
	}

	@Override
	public void keyPressed(KeyEvent e) //某一个键被压下
	{
		//System.out.println("键被按下！"+(char)e.getKeyCode());
		if(e.getKeyCode()==KeyEvent.VK_DOWN)
		{
			if(y<248)
				y+=2;
			else
				return;
		}
		else if(e.getKeyCode()==KeyEvent.VK_UP)
		{
			if(y>0)
				y-=2;
			else
				return;
		}
		else if(e.getKeyCode()==KeyEvent.VK_LEFT)
		{
			if(x>0)
				x-=2;
			else
				return;
		}
		else if(e.getKeyCode()==KeyEvent.VK_RIGHT)
		{
			if(x<368)
				x+=2;
			else
				return ;
		}
		else
			return;
		//调用repaint(),重绘窗口
		this.repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) //某一个键被释放
	{
		System.out.println("键被释放！");
		
	}

	@Override
	public void keyTyped(KeyEvent e) //键的值被输出
	{
		System.out.println("被按下的键是："+(char)e.getKeyChar());
		
	}
}
