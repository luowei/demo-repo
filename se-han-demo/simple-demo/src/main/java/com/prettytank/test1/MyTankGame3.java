/*
 * 功能：坦克大战v4.0
 * 1.画出坦克
 * 2.我的坦克可以上下左右移动
 * 3.可以发射子弹，子弹连发(最多5颗)
 * 4.当子弹击中敌人坦克时，敌人坦克消失(爆炸效果)
 * 5.我被击中时，显示爆炸效果
 * 6.防止敌人坦克重叠运动
 * 		6.1决定把判断是否碰撞的函数写到EnemyTank类中去
 * 7.可以分关
 * 		7.1做一个开始的Panel，它是一个空的
 * 		7.2字体闪烁效果
 * 8.可以在玩游戏的时候暂停和继续
 * 		8.1当用户点暂停时，子弹的速度和坦克 速度设为0，并让坦克的方向不变化 
 * 9.可以记录玩家的成绩
 * 		9.1用文件流
 * 		9.2单写一个记录类，完成对玩家的记录
 * 		9.3先完成保存共击毁了多少敌人坦克的功能
 * 		9.4存盘退出游戏，可以记录当时敌人坦克的坐标，并可以恢复 
 * 10.java如何操作声音文件
 * 		10.1对声音文件的操作
 * 11.网络对决....
 */

package com.prettytank.test1;
import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.event.*;
import java.util.*;
import java.io.*;

public class MyTankGame3 extends JFrame implements ActionListener
{

	MyPanel mp=null;		//游戏面板
	MyStartPanel msp=null;	//开始面板
	//需要的菜单
	JMenuBar jmb=null;	
	//开始游戏
	JMenu jm1=null;	//菜单
	JMenuItem jmi1=null;//菜单项
	JMenuItem jmi2=null;//退出
	JMenuItem jmi3=null;//存盘退出
	JMenuItem jmi4=null;//续上局
	
	//构造函数
	public MyTankGame3()
	{
		//创建菜单及菜单项
		jmb=new JMenuBar();
		jm1=new JMenu("游戏(G)");
		jm1.setMnemonic('G');//设置快捷方式
		
		jmi1=new JMenuItem("开始新游戏(N)");
		jmi1.setMnemonic('N');//设置快捷方式
		jmi2=new JMenuItem("退出游戏(E)");
		jmi2.setMnemonic('E');//设置快捷方式
		jmi3=new JMenuItem("存盘退出游戏(E)");
		jmi3.setMnemonic('C');//设置快捷方式
		jmi4=new JMenuItem("继续上局游戏(R)");
		jmi4.setMnemonic('R');//设置快捷方式
		
		jm1.add(jmi1);//将菜单项(jmi1)加入菜单(jm1)
		jm1.add(jmi2);
		jm1.add(jmi3);
		jm1.add(jmi4);
		jmb.add(jm1);//将菜单(jm1)加入菜单栏
		
		//对jmi1i添加事件响应
		jmi1.addActionListener(this);
		jmi1.setActionCommand("newGame");
		jmi2.addActionListener(this);
		jmi2.setActionCommand("exit");
		jmi3.addActionListener(this);
		jmi3.setActionCommand("saveExit");
		jmi4.addActionListener(this);
		jmi4.setActionCommand("conGame");
		
		//加入提示面板
		msp=new MyStartPanel();
		this.add(msp);
		Thread t=new Thread(msp);
		t.start();
		
		this.setJMenuBar(jmb);//加入菜单栏
		this.setSize(600,500);
		this.setLocation(200,200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		MyTankGame3 mtg=new MyTankGame3();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//开始新的游戏
		if(e.getActionCommand().equals("newGame"))
		{
			//创建游戏战场面板
			mp=new MyPanel("newGame");
			
			//启动mp线程
			Thread t=new Thread(mp);
			t.start();
			this.remove(msp);//先删除旧的开始面板
			this.add(mp);	//然后加入新的游戏面板
			
			//注册监听
			this.addKeyListener(mp);
			//显示、刷新JFrame
			this.setVisible(true);
		}
		else if(e.getActionCommand().equals("exit"))
		{
			//用户点击了退出系统的菜单
			//保存击毁敌人数量
			Recorder.keepRecording();
			
			System.exit(0);//退出,0代表正常退出,-1代表异常退出
		}
		//对存盘退出处理
		else if(e.getActionCommand().equals("saveExit"))
		{
			//工作
			Recorder rd=new Recorder();
			rd.setEts(mp.ets);
			//保存击毁敌人的数量和坐标
			rd.keeyRecAndEnemyTank();
			System.exit(0);//退出
		}
		else if(e.getActionCommand().equals("conGame"))//续上局
		{
			//创建游戏战场面板
			mp=new MyPanel("conGame");
			//mp.flag="con";
			
			//启动mp线程
			Thread t=new Thread(mp);
			t.start();
			this.remove(msp);//先删除旧的开始面板
			this.add(mp);	//然后加入新的游戏面板
			
			//注册监听
			this.addKeyListener(mp);
			//显示、刷新JFrame
			this.setVisible(true);
		}
	}
}
//开始提示面板
class MyStartPanel extends JPanel implements Runnable
{
	int times=0;
	public void paint(Graphics g)
	{
		super.paint(g);
		g.fillRect(0, 0, 400, 300);
		//提示信息
		if(times%2==0)
		{
			//开关信息的字体
			g.setColor(Color.yellow);//设置画笔颜色
			Font myFont=new Font("华文彩云",Font.BOLD,30);
			g.setFont(myFont);
			g.drawString("stage:1", 130, 150);
		}
	}

	@Override
	public void run() {
		// 
		while(true)
		{
			//休眠
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				e.printStackTrace();
			}
			times++;
			//重画
			this.repaint();
		}
	}
}

//我的面板
class MyPanel extends JPanel implements KeyListener,Runnable
{
	//定义一个我的坦克
	Hero hero=null;
	
	//判断是续上局，还是新游戏
	//String flag="newGame";
	
	//定义敌人的坦克组
	Vector<EnemyTank>ets=new Vector<EnemyTank>();
	Vector<Node>nodes=new Vector<Node>();
	
	//定义炸弹集合
	Vector<Bomb>bombs=new Vector<Bomb>();
	
	//初始化敌人坦克数量
	int enSize=6;
	
	//定义三张图片，组成成一颗炸弹
	Image image1=null;
	Image image2=null;
	Image image3=null;
	
	//构造函数
	public MyPanel(String flag)
	//构造函数中的参数，用于判断是续上局，还是新游戏
	{
		Recorder.getRecording();//恢复记录
		
		hero=new Hero(100,200);
		if(flag.equals("newGame"))//如果是新游戏
		{
			//初始化敌人的坦克
			for(int i=0;i<enSize;i++)
			{
				//创建一个辆敌人的坦克对象
				EnemyTank et=new EnemyTank((i+1)*50,0);
				et.setColor(0);//设置颜色
				et.setDirect(2);//设置方向
				//将MyPanel的敌人坦克向量交给敌人坦克
				et.setEts(ets);
				//启动敌人的坦克
				Thread t=new Thread(et);
				t.start();
				//给敌人坦克添加一颗子弹
				Shot s=new Shot(et.x+10,et.y+30,2);
				//把子弹加入给敌人
				et.ss.add(s);
				//启动子弹敌人的子弹线程
				Thread t2=new Thread(s);
				t2.start();
				//加入
				ets.add(et);
			}
		}
		else //如果不新游戏
		{
			nodes=new Recorder().getNodesAndEnNums();//恢复敌人坦克
			//初始化敌人的坦克
			for(int i=0;i<nodes.size();i++)
			{
				Node node=nodes.get(i);
				//创建一个辆敌人的坦克对象
				EnemyTank et=new EnemyTank(node.x,node.y);
				et.setColor(0);//设置颜色
				et.setDirect(node.direct);//设置方向
				//将MyPanel的敌人坦克向量交给敌人坦克
				et.setEts(ets);
				//启动敌人的坦克
				Thread t=new Thread(et);
				t.start();
				//给敌人坦克添加一颗子弹
				Shot s=new Shot(et.x+10,et.y+30,2);
				//把子弹加入给敌人
				et.ss.add(s);
				//启动子弹敌人的子弹线程
				Thread t2=new Thread(s);
				t2.start();
				//加入
				ets.add(et);
			}
		}
		try{
			image1=ImageIO.read(new File("./src/bomb_1.gif"));
			image2=ImageIO.read(new File("./src/bomb_2.gif"));
			image3=ImageIO.read(new File("./src/bomb_3.gif"));
		}catch (Exception e)
		{
			e.printStackTrace();
		}
		
		//播放开战声音
		AePlayWave apw=new AePlayWave("./src/声音.wav");
		apw.start();
		
		//初始化图片
		//image1=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_1.gif"));
		//image2=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_2.gif"));
		//image3=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_3.gif"));
	}
	
	//画出提示信息坦克(该坦克不参与战斗)
	public void showInfo(Graphics g)
	{
		//敌人的坦克提示信息
		this.drawTank(60, 330, g, 3, 0);
		g.setColor(Color.black);
		g.drawString(Recorder.getEnNum()+"", 85, 350);
		//我的坦克提示信息
		this.drawTank(130, 330, g, 3, 1);
		g.setColor(Color.black);
		g.drawString(Recorder.getMyLife()+"", 155, 350);
		
		//画出玩家的总成绩
		g.setColor(Color.black);
		Font f=new Font("宋体",Font.BOLD,20);
		g.setFont(f);
		g.drawString("您的总成绩：", 420, 30);
		
		this.drawTank(430, 60, g, 3, 0);//敌人坦克
		g.setColor(Color.black);
		g.drawString(Recorder.getAllEnNum()+"", 460, 80);
	}
	
	
	//重写paint函数
	public void paint(Graphics g)
	{
		super.paint(g); //这句不能少
		g.fillRect(0,0,400,300);//设置游戏面板背景
		
		//画出提示信息
		this.showInfo(g);
		
		//画出自己的坦克
		if(hero.isLive)
		{
			this.drawTank(hero.getX(), hero.getY(), g, this.hero.direct, 1);
		}
		//从ss中取出每一颗子弹，并画出
		for(int i=0;i<this.hero.ss.size();i++) //设置子弹连发
		{
			//取出一颗子弹
			Shot myShot=hero.ss.get(i);
			
			//画出一颗子弹
			if(hero.s!=null&&hero.s.isLive==true)
			{
				g.setColor(Color.WHITE);//设置子弹颜色
				g.fill3DRect(myShot.x, myShot.y, 3, 3, true);
			}
			if(myShot.isLive==false)//如果子弹已经死亡
			{
				//从ss中删除掉该子弹
				hero.ss.remove(myShot);
			}
		}
		
		//画出炸弹
		for(int i=0;i<bombs.size();i++)
		{
			//System.out.println("bombs.size()="+bombs.size());
			//取出炸弹
			Bomb b=bombs.get(i);
			if(b.life>6)
			{
				g.drawImage(image1, b.x, b.y, 30, 30, this);
			}
			else if(b.life>4)
			{
				g.drawImage(image2, b.x, b.y, 30, 30, this);
			}
			else
			{
				g.drawImage(image3, b.x, b.y, 30, 30, this);
			}
			//让b的生命值减小
			b.lifeDown();
			if(b.life==0)//如果炸弹生命值为0时，就把炸弹从集合中去掉
			{
				bombs.remove(b);
			}
		}
		
		//画出敌人的坦克
		for(int i=0;i<ets.size();i++)
		{
			EnemyTank et=ets.get(i);
			if(et.isLive)	//画出还是活的坦克
			{
				this.drawTank(et.getX(), et.getY(), g, et.getDirect(), 0);
				//再画出敌人的子弹
				for(int j=0;j<et.ss.size();j++)
				{
					//取出子弹
					Shot enemyShot=et.ss.get(j);
					if(enemyShot.isLive)
					{
						g.setColor(Color.PINK);//设置子弹颜色
						g.fill3DRect(enemyShot.x, enemyShot.y, 3, 3, true);//画子弹
					}
					else //如果敌人的坦克死亡了就从Vector去掉
					{
						et.ss.remove(enemyShot);
					}
				}
			}
		}
	}
	
	//判断我的子弹是否击中敌人的坦克
	public void hitEnemyTank()
	{
		//在这run函数里判断子弹是否击中敌人坦克
		for(int i=0;i<hero.ss.size();i++)
		{
			//取出子弹
			Shot myShot=hero.ss.get(i);
			if(myShot.isLive)//判断子弹是否还是活的或有效
			{
				//取出每个坦克，与它判断
				for(int j=0;j<ets.size();j++)
				{
					//取出坦克
					EnemyTank et=ets.get(j);
					if(et.isLive)
					{
						if(this.hitTank(myShot,et))
						{
							//减少一个敌人坦克
							Recorder.redurceEnNum();
							//增加我的记录(战绩)
							Recorder.addEnNumRec();
						}
					}
				}
			}
		}
	}
	
	//敌人的子弹是否击中我
	public void hitMe()
	{
		//取出每一个敌人的坦克
		for(int i=0;i<this.ets.size();i++)
		{
			//取出坦克
			EnemyTank et=ets.get(i);
			
			//取出每一颗子弹
			for(int j=0;j<et.ss.size();j++)
			{
				//取出子弹
				Shot enemyShot=et.ss.get(j);
				if(hero.isLive)
				{
					this.hitTank(enemyShot, hero);
				}
			}
		}
	}
	
	//1.写一个专门判断子弹是否击中敌人坦克的函数
	public boolean hitTank(Shot s,Tank et)
	{
		boolean bool=false; //判断坦克是否被击中
		//判断该坦克的方向
		switch(et.direct)
		{
		//如果敌人坦克的方向是右或者是左
		case 0:
		case 1:
			if(s.x>et.x&&s.x<et.x+30&&s.y>et.y&&s.y<et.y+20)
			{
				//击中
				s.isLive=false;	//子弹死亡
				et.isLive=false;//敌人坦克死亡
				bool=true;
				//创建一颗炸弹
				Bomb b=new Bomb(et.x,et.y);
				//把炸弹放入到Vector
				bombs.add(b);
			}
			break;
		//如果敌人坦克的方向是上或者是下
		case 2:
		case 3:
			if(s.x>et.x&&s.x<et.x+20&&s.y>et.y&&s.y<et.y+30)
			{
				//击中
				s.isLive=false;	//子弹死亡
				et.isLive=false;//敌人坦克死亡
				bool=false;
				//创建一颗炸弹
				Bomb b=new Bomb(et.x,et.y);
				//把炸弹放入到Vector
				bombs.add(b);
			}
			break;
		}
		return bool;
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
			if(this.hero.ss.size()<=4)//连发子弹小于5
			{
				this.hero.shotEnemy();
			}
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
			
			//2.什么地方调用-判断子弹是否击中敌人坦克的函数
			
			this.hitEnemyTank();//子弹是否击中敌人坦克
			
			this.hitMe();//敌人的坦克是否击中我了
			
			//重绘
			this.repaint();
		}
	}
}




