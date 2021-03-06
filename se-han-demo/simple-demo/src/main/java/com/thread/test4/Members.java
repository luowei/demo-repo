package com.thread.test4;

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
			System.out.println("子弹坐标x="+x+" y="+y);
			
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

//敌人的坦克
class EnemyTank extends Tank
{
	public EnemyTank(int x,int y)
	{
		super(x,y);
	}
}

//我的坦克
class Hero extends Tank
{
	//子弹
	Shot s=null;
	
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
			s=new Shot(x+30,y+9,0);
			break;
		case 1: //坦克朝左
			s=new Shot(x,y+9,1);
			break;
		case 2:	//坦克朝下
			s=new Shot(x+9,y+30,2);
			break;
		case 3: //坦克朝上
			s=new Shot(x+9,y,3);
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
