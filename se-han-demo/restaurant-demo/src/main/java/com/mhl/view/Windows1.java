/*
 * 系统管理界面
 * 这是系统管理员,经理,主管可以进入的操作界面
 * 完成界面的顺序，从上到下，从左到右
 * 写代码要注意就近原则
 */

package com.mhl.view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import javax.imageio.*;
import java.util.*;
import java.util.Timer;

import com.mhl.tools.*;//引入这个自己添加的包

import java.io.File;
import java.io.IOException;


public class Windows1 extends JFrame implements ActionListener,MouseListener{

	//定义需要的组件
	//ImageIcon titleIcon;
	Image titleIcon,timebg;
	JMenuBar jmb;//菜单栏
	//一级菜单
	JMenu jm1,jm2,jm3,jm4,jm5,jm6;
	//二级菜单
	JMenuItem jmm1,jmm2,jmm3,jmm4,jmm5;
	//菜单项图标
	ImageIcon jmm1_icon1,jmm2_icon2,jmm3_icon3,jmm4_icon4,jmm5_icon5;
	
	//工具栏
	JToolBar jtb;
	JButton jb1,jb2,jb3,jb4,jb5,jb6,jb7,jb8,jb9,jb10;
	
	//定义需要的五个JPanel
	JPanel p1,p2,p3,p4,p5;
	JLabel timeNow;	//用于显示当前时间的标签
	//8个图标
	JLabel p1_lab1,p1_lab2,p1_lab3,p1_lab4,p1_lab5,p1_lab6,p1_lab7,p1_lab8;
	
	//给p2面板定义需要的JLabel
	JLabel p2_lab1,p2_lab2;
	
	javax.swing.Timer t; //这个Timer类可以定时触发Action
	
	Cursor myCursor=null;
	
	JSplitPane jsp1; //拆分窗口
	
	CardLayout cardP3;
	CardLayout cardP2;
	
	ImagePanel p1_imgPanel;
	Image p1_bg=null;
	Image zhu_image=null;
	
	public static void main(String[] args) {
		//
		Windows1 w1=new Windows1();
	}
	//初始化菜单
	public void initMenu()
	{
		//创建图标
		jmm1_icon1=new ImageIcon("image/menuBar_image/jm1_icon1.jpg");
		jmm2_icon2=new ImageIcon("image/menuBar_image/jm1_icon2.jpg");
		jmm3_icon3=new ImageIcon("image/menuBar_image/jm1_icon3.jpg");
		jmm4_icon4=new ImageIcon("image/menuBar_image/jm1_icon4.jpg");
		jmm5_icon5=new ImageIcon("image/menuBar_image/jm1_icon5.jpg");
		
		//创建一级菜单
		jm1=new JMenu("系统管理");
		jm1.setFont(MyTools.f1);
		//创建它的二级菜单
		jmm1=new JMenuItem("切换用户",jmm1_icon1);
		jmm1.setFont(MyTools.f2);
		jmm2=new JMenuItem("切换到收款界面",jmm2_icon2);
		jmm2.setFont(MyTools.f2);
		jmm3=new JMenuItem("登陆管理",jmm3_icon3);
		jmm3.setFont(MyTools.f2);
		jmm4=new JMenuItem("万年历",jmm4_icon4);
		jmm4.setFont(MyTools.f2);
		jmm5=new JMenuItem("退出",jmm5_icon5);
		jmm5.setFont(MyTools.f2);
		//加入
		jm1.add(jmm1);
		jm1.add(jmm2);
		jm1.add(jmm3);
		jm1.add(jmm4);
		jm1.add(jmm5);
		
		jm2=new JMenu("人事管理");
		jm2.setFont(MyTools.f1);
		jm3=new JMenu("菜单管理");
		jm3.setFont(MyTools.f1);
		jm4=new JMenu("报表统计");
		jm4.setFont(MyTools.f1);
		jm5=new JMenu("成本及库房");
		jm5.setFont(MyTools.f1);
		jm6=new JMenu("帮助");
		jm6.setFont(MyTools.f1);
		
		//把一级菜单加入大JMenuBar
		jmb=new JMenuBar();
		jmb.add(jm1);
		jmb.add(jm2);
		jmb.add(jm3);
		jmb.add(jm4);
		jmb.add(jm5);
		jmb.add(jm6);
		
		//把JMenuBar添加到JFrame
		this.setJMenuBar(jmb);
	}
	
	//初始化工具栏
	public void initToolBar()
	{
		//处理工具栏的组件
		jtb=new JToolBar();
		//设置工具栏不可以浮动
		jtb.setFloatable(false);
		jb1=new JButton(new ImageIcon("image/toolBar_image/jb1.jpg"));
		jb2=new JButton(new ImageIcon("image/toolBar_image/jb2.jpg"));
		jb3=new JButton(new ImageIcon("image/toolBar_image/jb3.jpg"));
		jb4=new JButton(new ImageIcon("image/toolBar_image/jb4.jpg"));
		jb5=new JButton(new ImageIcon("image/toolBar_image/jb5.jpg"));
		jb6=new JButton(new ImageIcon("image/toolBar_image/jb6.jpg"));
		jb7=new JButton(new ImageIcon("image/toolBar_image/jb7.jpg"));
		jb8=new JButton(new ImageIcon("image/toolBar_image/jb8.jpg"));
		jb9=new JButton(new ImageIcon("image/toolBar_image/jb9.jpg"));
		jb10=new JButton(new ImageIcon("image/toolBar_image/jb10.jpg"));
		//把按钮加入到jtb
		jtb.add(jb1);
		jtb.add(jb2);
		jtb.add(jb3);
		jtb.add(jb4);
		jtb.add(jb5);
		jtb.add(jb6);
		jtb.add(jb7);
		jtb.add(jb8);
		jtb.add(jb9);
		jtb.add(jb10);
	}
	
	//初始化中间的panel
	public void allPanels()
	{
		//处理p1面板
		p1=new JPanel(new BorderLayout());
		
		try {
			p1_bg = ImageIO.read(new File("image/center_image/jp1_bg.jpg"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		myCursor=new Cursor(Cursor.HAND_CURSOR);//设置手形光标
		//p1的背景图
		this.p1_imgPanel=new ImagePanel(p1_bg);
		this.p1_imgPanel.setLayout(new GridLayout(8,1));
		p1_lab1=new JLabel(new ImageIcon("image/center_image/label_1.gif"));
		p1_lab1.setFont(MyTools.f3);
		p1_lab1.setCursor(myCursor);
		p1_lab1.addMouseListener(this);
		this.p1_imgPanel.add(p1_lab1); //加入第1个label
		p1_lab2=new JLabel("人 事 管 理",new ImageIcon("image/center_image/label_2.jpg"),0);
		p1_lab2.setFont(MyTools.f3);
		p1_lab2.setEnabled(false);//让该label不可用
		p1_lab2.addMouseListener(this);
		p1_lab2.setCursor(myCursor);
		this.p1_imgPanel.add(p1_lab2);
		p1_lab3=new JLabel("登 录 管 理",new ImageIcon("image/center_image/label_3.jpg"),0);
		p1_lab3.setFont(MyTools.f3);
		p1_lab3.setEnabled(false);
		p1_lab3.addMouseListener(this);
		p1_lab3.setCursor(myCursor);
		this.p1_imgPanel.add(p1_lab3);
		p1_lab4=new JLabel("菜 谱 价 格",new ImageIcon("image/center_image/label_4.jpg"),0);
		p1_lab4.setFont(MyTools.f3);
		p1_lab4.setEnabled(false);
		p1_lab4.addMouseListener(this);
		p1_lab4.setCursor(myCursor);
		this.p1_imgPanel.add(p1_lab4);
		p1_lab5=new JLabel("报 表 统 计",new ImageIcon("image/center_image/label_5.jpg"),0);
		p1_lab5.setFont(MyTools.f3);
		p1_lab5.setEnabled(false);
		p1_lab5.addMouseListener(this);
		p1_lab5.setCursor(myCursor);
		this.p1_imgPanel.add(p1_lab5);
		p1_lab6=new JLabel("成本及库房",new ImageIcon("image/center_image/label_6.jpg"),0);
		p1_lab6.setFont(MyTools.f3);
		p1_lab6.setEnabled(false);
		p1_lab6.addMouseListener(this);
		p1_lab6.setCursor(myCursor);
		this.p1_imgPanel.add(p1_lab6);
		p1_lab7=new JLabel("系 统 设 置",new ImageIcon("image/center_image/label_7.jpg"),0);
		p1_lab7.setFont(MyTools.f3);
		p1_lab7.setEnabled(false);
		p1_lab7.addMouseListener(this);
		p1_lab7.setCursor(myCursor);
		this.p1_imgPanel.add(p1_lab7);
		p1_lab8=new JLabel("动 画 帮 助",new ImageIcon("image/center_image/label_8.jpg"),0);
		p1_lab8.setFont(MyTools.f3);
		p1_lab8.setEnabled(false);
		p1_lab8.addMouseListener(this);
		p1_lab8.setCursor(myCursor);
		this.p1_imgPanel.add(p1_lab8);
		
		p1.add(this.p1_imgPanel);
		
		//处理p2,p3,p4
		p4=new JPanel(new BorderLayout());
		cardP2=new CardLayout();
		p2=new JPanel(this.cardP2);//卡片布局
		p2_lab1=new JLabel(new ImageIcon("image/center_image/jp2_left.jpg"));
		p2_lab1.addMouseListener(this); //监听
		p2_lab2=new JLabel(new ImageIcon("image/center_image/jp2_right.jpg"));
		p2_lab2.addMouseListener(this); //监听
		p2.add(p2_lab1,"0");//作为p2的0号卡片
		p2.add(p2_lab2,"1");//作为p2的1号卡片
		
		this.cardP3=new CardLayout();
		p3=new JPanel(this.cardP3);
		//先给p3加入一个主界面的卡片(ImagePanel)
		try {
			zhu_image = ImageIO.read(new File("image/center_image/jp3_bg.jpg"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		ImagePanel ip=new ImagePanel(zhu_image);
		p3.add(ip,"0");//作为p3的0号卡片
		//对p3做添加几个JLabel
		//创建EmpInfo对象(JPanel)
		Empinfo eInfo=new Empinfo();
		p3.add(eInfo,"1");	//人事管理
		JLabel dl=new JLabel(new ImageIcon("image/center_image/denglu_img.jpg"));
		
		p3.add(dl,"2");	//登录管理
		//把p2,p3加入p4
		p4.add(p2,"West");
		p4.add(p3,"Center");
		
		//做一个拆分窗口,分别存放p1和p4
		jsp1=new  JSplitPane(JSplitPane.HORIZONTAL_SPLIT,p1,p4);//横向分拆
		//指定左边的面板占多大
		jsp1.setDividerLocation(160);
		//设置分隔线的宽度
		jsp1.setDividerSize(0);
	}
	
	//初始化状态栏
	public void initStatusBar()
	{
		//处理p5面板
		p5=new JPanel(new BorderLayout());
		//创建Timer
		t=new javax.swing.Timer(1000,this); //每隔1秒取触发ActionListener
		t.start();//启动定时器
		//获取当前时间
		timeNow=new JLabel("当前时间："+Calendar.getInstance().getTime().toString());
		//this.timeNow.setText(Calendar.getInstance().getTime().toLocaleString());
		//timeNow.setFont(MyTools.f1); //设置字体
		//设置状态栏背景
		try {
			timebg=ImageIO.read(new File("image/time_bg.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		ImagePanel ip1=new ImagePanel(timebg);//建一个存放图片的panel
		ip1.setLayout(new BorderLayout());
		ip1.add(timeNow,"East");
		p5.add(ip1);
	}
	
	public Windows1()
	{
		//创建组件
		//titleIcon=new ImageIcon("image/title.ico");
		try {
			titleIcon=ImageIO.read(new File("image/title.gif"));
		} catch (Exception e) {
			//
			e.printStackTrace();
		}
		
		//调用初始化菜单函数
		this.initMenu();
		
		//调用初始化工具栏函数
		this.initToolBar();
		
		//调用初始化中间面板的函数
		this.allPanels();
		
		//调用初始化状态栏的函数
		this.initStatusBar();
		
		
		//从JFrame中取得Container
		Container ct=this.getContentPane();
		
		ct.add(jtb,"North");
		ct.add(jsp1,"Center");
		ct.add(p5,"South");
		
		
		//设置大小
		int w=Toolkit.getDefaultToolkit().getScreenSize().width	;
		int h=Toolkit.getDefaultToolkit().getScreenSize().height;
		
		this.setIconImage(titleIcon);//设置窗口图标
		this.setTitle("满汉楼餐饮管理系统");
		
		//关闭窗口时候，退出系统
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(w,h-30);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//
		this.timeNow.setText("当前时间："+Calendar.getInstance().getTime().toString());
		//this.timeNow.setText(Calendar.getInstance().getTime().toLocaleString());
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		//判断用户点击哪个操作JLabel
		if(e.getSource()==this.p1_lab1)
		{
			this.cardP3.show(this.p3, "0");
		}
		else if(e.getSource()==this.p1_lab2)
		{
			this.cardP3.show(this.p3, "1");
		}
		else if(e.getSource()==this.p1_lab3)
		{
			this.cardP3.show(this.p3, "2");
		}
		else if(e.getSource()==this.p2_lab1)//当点击了向左的箭头
		{
			//把显示各种操作的界面p1，展开
			this.cardP2.show(p2, "1"); //显示向右的箭头
			this.jsp1.setDividerLocation(0);//隐藏
		}
		else if(e.getSource()==this.p2_lab2) //当点击了向右的箭头
		{
			this.cardP2.show(p2, "0"); //显示向左的箭头
			this.jsp1.setDividerLocation(160);//展开
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		//如果用户选择了某个操作JLabel，则高亮
		if(e.getSource()==this.p1_lab2)
		{
			//System.out.println("选中了 人事管理");
			this.p1_lab2.setEnabled(true);
		}
		else if(e.getSource()==this.p1_lab3)
		{
			this.p1_lab3.setEnabled(true);
		}
		else if(e.getSource()==this.p1_lab4)
		{
			this.p1_lab4.setEnabled(true);
		}
		else if(e.getSource()==this.p1_lab5)
		{
			this.p1_lab5.setEnabled(true);
		}
		else if(e.getSource()==this.p1_lab6)
		{
			this.p1_lab6.setEnabled(true);
		}
		else if(e.getSource()==this.p1_lab7)
		{
			this.p1_lab7.setEnabled(true);
		}
		else if(e.getSource()==this.p1_lab8)
		{
			this.p1_lab8.setEnabled(true);
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		//如果用户选择了某个操作JLabel，则禁用
		if(e.getSource()==this.p1_lab2)
		{
			this.p1_lab2.setEnabled(false);
		}
		else if(e.getSource()==this.p1_lab3)
		{
			this.p1_lab3.setEnabled(false);
		}
		else if(e.getSource()==this.p1_lab4)
		{
			this.p1_lab4.setEnabled(false);
		}
		else if(e.getSource()==this.p1_lab5)
		{
			this.p1_lab5.setEnabled(false);
		}
		else if(e.getSource()==this.p1_lab6)
		{
			this.p1_lab6.setEnabled(false);
		}
		else if(e.getSource()==this.p1_lab7)
		{
			this.p1_lab7.setEnabled(false);
		}
		else if(e.getSource()==this.p1_lab8)
		{
			this.p1_lab8.setEnabled(false);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
