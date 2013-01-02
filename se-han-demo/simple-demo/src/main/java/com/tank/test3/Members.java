package com.tank.test3;

import java.util.*;

//炸弹类
class Bomb
{
	//定义炸弹的坐标
	int x,y;
	//炸弹的生命
	int life=9;
	//炸弹是否还是活的
	boolean isLive=true;
	public Bomb(int x,int y)
	{
		this.x=x;
		this.y=y;
	}
	//减少炸弹生命值
	public void lifeDown()
	{
		if(life>0)
		{
			life--;
		}
		else
		{
			this.isLive=false;
		}
	}
}

//子弹类
class Shot implements Runnable
{
	int x,y,direct;
	int speed=3;
	//是否还活着
	boolean isLive=true;
	
	public Shot(int x,int y,int direct)
	{
		this.x=x;
		this.y=y;
		this.direct=direct;
	}
	
	@Override
	public void run() {
		//子弹向前跑动
		while(true)
		{
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			switch(direct)
			{
			case 0:	//子弹向右
				x+=speed;
				break;
			case 1:	//子弹向左
				x-=speed;
				break;
			case 2: //子弹向下
				y+=speed;
				break;
			case 3:	//子弹向上
				y-=speed;
				break;
			}
			//System.out.println("子弹坐标x="+x+" y="+y);
			
			//子弹何时死亡
			//判断该子弹是否碰到边缘
			if(x<0||x>400||y<0||y>300)
			{
				this.isLive=false;
				break;
			}
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
	//坦克方向
	int direct=0;//0表示右，1表示左，2表示下，3表示上
	//坦克的速度
	int speed=1;
	//坦克的颜色
	int color;
	
	boolean isLive=true;
	
	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getDirect() {
		return direct;
	}

	public void setDirect(int direct) {
		this.direct = direct;
	}

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

//敌人的坦克,把敌人坦克做成线程类
class EnemyTank extends Tank implements Runnable
{
	//boolean isLive=true;//是否死亡
	int times=0;
	
	//定义一个向量，可以存放敌人的子弹
	Vector<Shot>ss=new Vector<Shot>();
	
	//敌人添加子弹，应当在刚刚创建坦克和敌人的坦克子弹是死亡之后
	
	public EnemyTank(int x,int y)
	{
		super(x,y);
	}

	@Override
	public void run() {
		//让敌人坦克坐标不停的变化
		while(true)
		{
			switch(this.direct)
			{
			case 0: //说明坦克正在向右移动
				for(int i=0;i<30;i++)
				{
					if(x<400)//限制坦克不移出边界
					{
						try {
							Thread.sleep(50);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						x+=speed;
					}
				}
				break;
			case 1: //说明坦克正在向左移动
				for(int i=0;i<30;i++)
				{
					if(x>0)//限制坦克不移出边界
					{
						try {
							Thread.sleep(50);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						x-=speed;
					}
				}
				break;
			case 2: //说明坦克正在向下移动
				for(int i=0;i<30;i++)
				{
					if(y<280)//限制坦克不移出边界
					{
						try {
							Thread.sleep(50);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						y+=speed;
					}
				}
				break;
			case 3: //说明坦克正在向上移动
				for(int i=0;i<30;i++)
				{
					if(y>0)//限制坦克不移出边界
					{
						try {
							Thread.sleep(50);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						y-=speed;
					}
				}
				break;
			}
			
			this.times++;
			
			if(times%2==0)//3秒时间到了
			{
				if(isLive)
				{
					if(ss.size()<5)
					{
						Shot s=null;
						//添加子弹
						switch(direct)
						{
						case 0:	//坦克朝右
							//创建一颗子弹
							s=new Shot(x+30,y+9,0);
							//把子弹加入集合向量
							ss.add(s);
							break;
						case 1: //坦克朝左
							s=new Shot(x,y+9,1);
							//把子弹加入向量
							ss.add(s);
							break;
						case 2:	//坦克朝下
							s=new Shot(x+9,y+30,2);
							//把子弹加入向量
							ss.add(s);
							break;
						case 3: //坦克朝上
							s=new Shot(x+9,y,3);
							//把子弹加入向量
							ss.add(s);
							break;
						}
						
						//启动子弹线程
						Thread t=new Thread(s);
						t.start();
					}
				}
			}
			
			//让坦克随机产生一个新的方向
			this.direct=(int)(Math.random()*4);
			
			//判断敌人坦克是否死亡
			if(this.isLive==false)
			{
				//让坦克死亡后退出线程
				break;
			}
		}
	}
}

//我的坦克
class Hero extends Tank
{
	//子弹
	Shot s=null;
	//定义一个用于存放子弹的集合
	Vector<Shot> ss=new Vector<Shot>(); 
	
	public Hero(int x,int y)
	{
		super(x,y);//用父类的构造函数初始化子类的成员变量
	}
	
	//发射子弹
	public void shotEnemy()
	{
		switch(this.direct)
		{
		case 0:	//坦克朝右
			//创建一颗子弹
			s=new Shot(x+30,y+9,0);
			//把子弹加入集合向量
			ss.add(s);
			break;
		case 1: //坦克朝左
			s=new Shot(x,y+9,1);
			//把子弹加入向量
			ss.add(s);
			break;
		case 2:	//坦克朝下
			s=new Shot(x+9,y+30,2);
			//把子弹加入向量
			ss.add(s);
			break;
		case 3: //坦克朝上
			s=new Shot(x+9,y,3);
			//把子弹加入向量
			ss.add(s);
			break;
		}
		//启动子弹线程
		Thread t=new Thread(s); //因为子弹类Shot实现了Runnable接口
		t.start();
	}
	
	//坦克向上移动
	public void moveUp()
	{
		y-=speed;
	}
	//坦克向右移动
	public void moveRight()
	{
		x+=speed;
	}
	//坦克向下移动
	public void moveDown()
	{
		y+=speed;
	}
	//坦克向左移动
	public void moveLeft()
	{
		x-=speed;
	}
}
