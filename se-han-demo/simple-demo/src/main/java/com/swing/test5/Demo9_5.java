/*
 * 功能：让一个监听器监听多个不同的事件
 */

package com.swing.test5;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Demo9_5 extends JFrame{

	MyPanel mp=null;
	public static void main(String[] args) {
		Demo9_5 demo9_5=new Demo9_5();

	}
	//构造函数
	public Demo9_5()
	{
		mp=new MyPanel();
		this.add(mp);
		
		//注册监听
		this.addMouseListener(mp);
		this.addKeyListener(mp);
		this.addMouseMotionListener(mp);
		this.addWindowListener(mp);
		
		this.setSize(400,300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}

//1.让MyPanel知道鼠标按下的消息，并且知道点击的位置(x,y)
//2.让MyPanel知道哪个键按下
//3.让MyPanel知道鼠标移动、拖拽
//4.让MyPanel知道窗口的变化(关闭,最小化,最大化)

class MyPanel extends JPanel implements 
	MouseListener,KeyListener,MouseMotionListener,WindowListener
{
	public void paint(Graphics g)
	{
		super.paint(g);
	}

	@Override
	public void mouseClicked(MouseEvent e) //鼠标点击
	{
		System.out.println("鼠标点击了 x="+e.getX()+" y="+e.getY());
		
	}

	@Override
	public void mouseEntered(MouseEvent e) //鼠标移入
	{
		System.out.println("鼠标来了");
		
	}

	@Override
	public void mouseExited(MouseEvent e) //鼠标离开MyPanel
	{
		System.out.println("鼠标走了");
		
	}

	@Override
	public void mousePressed(MouseEvent e) //鼠标按下
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) //鼠标释放
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) //键按下
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) //键松开
	{
		System.out.println("按下的键是："+e.getKeyChar());
		
	}

	@Override
	public void keyTyped(KeyEvent e) //打印键的值
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) //鼠标拖拽
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) //鼠标移动
	{
		System.out.println("鼠标当前坐标是：x="+e.getX()+"y="+e.getY());
		
	}

	@Override
	public void windowActivated(WindowEvent e) //窗口变为活动窗口
	{
		System.out.println("windowActivated");
		
	}

	@Override
	public void windowClosed(WindowEvent e) //窗口关闭了
	{
		System.out.println("windowClosed");
		
	}

	@Override
	public void windowClosing(WindowEvent e) //窗口正在关闭
	{
		System.out.println("windowClosing");
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) //窗口变为非活动窗口
	{
		System.out.println("windowDeactivated");
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) //窗口变到最大图标区域
	{
		System.out.println("windowDeiconified");
		
	}

	@Override
	public void windowIconified(WindowEvent e) //窗口变到最小图标区域
	{
		System.out.println("windowIconified");
		
	}

	@Override
	public void windowOpened(WindowEvent e) //窗口打开了
	{
		System.out.println("windowOpened");
		
	}
	
}
