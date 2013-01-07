package com.prettytank.test1;

import java.util.*;
import java.io.*;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

//播放声音的类
class AePlayWave extends Thread {

	private String filename;
	public AePlayWave(String wavfile) {
		filename = wavfile;

	}

	public void run() {

		File soundFile = new File(filename);

		AudioInputStream audioInputStream = null;
		try {
			audioInputStream = AudioSystem.getAudioInputStream(soundFile);
		} catch (Exception e1) {
			e1.printStackTrace();
			return;
		}

		AudioFormat format = audioInputStream.getFormat();
		SourceDataLine auline = null;
		DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);

		try {
			auline = (SourceDataLine) AudioSystem.getLine(info);
			auline.open(format);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}

		auline.start();
		int nBytesRead = 0;
		//这是缓冲
		byte[] abData = new byte[512];

		try {
			while (nBytesRead != -1) {
				nBytesRead = audioInputStream.read(abData, 0, abData.length);
				if (nBytesRead >= 0)
					auline.write(abData, 0, nBytesRead);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return;
		} finally {
			auline.drain();
			auline.close();
		}
	}
}


//坦克恢复点
class Node
{
	int x,y,direct;
	public Node(int x,int y,int z)
	{
		this.x=x;
		this.y=y;
		this.direct=direct;
	}
}

//记录类，同时也可以保存玩家的设置
class Recorder
{
	//记录每关有多少敌人
	private static int enNum=20;
	//设置我有多少可以用的坦克
	private static int myLife=3;
	//记录总共消灭了多少敌人
	private static int allEnNum=0;
	
	private static FileWriter fw=null;//文件流
	private static BufferedWriter bw=null;//buf流
	private static FileReader fr=null;
	private static BufferedReader br=null;
	//定义一个集合用于保存敌人的坦克
	private Vector<EnemyTank>ets=new Vector<EnemyTank>();
	//从文件中恢复记录点
	static Vector<Node> nodes=new Vector<Node>();
	
	public Vector<EnemyTank> getEts() {
		return ets;
	}
	public void setEts(Vector<EnemyTank> ets) {
		this.ets = ets;
	}
	public static int getAllEnNum() {
		return allEnNum;
	}
	public static void setAllEnNum(int allEnNum) {
		Recorder.allEnNum = allEnNum;
	}
	public static int getEnNum() {
		return enNum;
	}
	public static void setEnNum(int enNum) {
		Recorder.enNum = enNum;
	}
	public static int getMyLife() {
		return myLife;
	}
	public static void setMyLife(int myLife) {
		Recorder.myLife = myLife;
	}
	//减少敌人数
	public static void redurceEnNum()
	{
		enNum--;
	}
	//消灭敌人
	public static void addEnNumRec()
	{
		allEnNum++;
	}
	
	//保存击毁敌人坦克的数量、坐标和方向
	public void keeyRecAndEnemyTank()
	{
		try {
			//创建
			fw=new FileWriter("./myRecording.txt");
			bw=new BufferedWriter(fw);
			
			bw.write(allEnNum+"\r\n");
			//System.out.println("size="+ets.size());
			//保存当前活的敌人坦克的坐标和方向
			for(int i=0;i<ets.size();i++)
			{
				//取出第一个坦克
				EnemyTank et=ets.get(i);
				if(et.isLive)
				{
					//活的就保存,坐标与方向
					String recorde=et.x+" "+et.y+" "+et.direct;
					//写入
					bw.write(recorde+"\r\n");
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
		
			//关闭流
			try {
				//后开先关闭
				bw.close();
				fw.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	//把玩家击毁敌人坦克数量保存到文件中
	public static void keepRecording()
	{
		try {
			//创建
			fw=new FileWriter("./myRecording.txt");
			bw=new BufferedWriter(fw);
			
			bw.write(allEnNum+"\r\n");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				bw.close();
				fw.close();//关闭流
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	//完成恢复退出时的记录
	public Vector<Node> getNodesAndEnNums()
	{
		try {
			fr=new FileReader("./myRecording.txt");
			br=new BufferedReader(fr); //读取记录
			String n="";
			n=br.readLine();//先读取一行
			allEnNum=Integer.parseInt(n);
			while((n=br.readLine())!=null)
			{
				String []xyz=n.split(" ");//读取以空格分隔的字符 
				Node node=new Node(Integer.parseInt(xyz[0]),
						Integer.parseInt(xyz[1]),Integer.parseInt(xyz[2]));
				nodes.add(node);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				br.close();
				fr.close();//关闭流
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return nodes;
	}
	
	//从文件中读取记录
	public static void getRecording()
	{
		try {
			fr=new FileReader("./myRecording.txt");
			br=new BufferedReader(fr); //读取记录
			String n=br.readLine();	//将读取的记录存到string中
			allEnNum=Integer.parseInt(n);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				br.close();
				fr.close();//关闭流
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}


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
	int direct=3;//0表示右，1表示左，2表示下，3表示上
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
	
	//定义一个集合向量可以访问到MyPanel上所有敌人的坦克
	Vector<EnemyTank>ets=new Vector<EnemyTank>();

	//定义一个集合向量，可以存放敌人的子弹
	Vector<Shot>ss=new Vector<Shot>();
	
	//敌人添加子弹，应当在刚刚创建坦克和敌人的坦克子弹是死亡之后
	
	public EnemyTank(int x,int y)
	{
		super(x,y);
	}

	//得到MyPanel的敌人坦克向量
	public void setEts(Vector<EnemyTank> ets) {
		this.ets = ets;
	}
	public Vector<EnemyTank> getEts() {
		return ets;
	}

	
	//判断是否碰到了别的敌人坦克
	public boolean isTouchOtherEnemy()
	{
		boolean b=false;
		switch(this.direct)
		{
		case 0:	//坦克向右
			//取出所有的敌人坦克
			for(int i=0;i<ets.size();i++)
			{
				//取出第一个坦克
				EnemyTank et=ets.get(i);
				if(et!=this)//如果不是自己
				{
					//如果敌人的方向是向上或向下
					if(et.direct==3||et.direct==2)
					{
						//如果此坦克的右上角点在其它坦克矩形区域内
						if(this.x+30>=et.x&&this.x+30<=et.x+20
								&&this.y>=et.y&&this.y<=et.y+30)
						{
							return true;
						}
						//如果此坦克的右下角点在其它坦克矩形区域内
						if(this.x+30>=et.x&&this.x+30<=et.x+20
								&&this.y+20>=et.y&&this.y+20<=et.y+30)
						{
							return true;
						}
					}
					//如果敌人的方向是向左或向右
					if(et.direct==1||et.direct==0)
					{
						//如果此坦克的右上角点在其它坦克矩形区域内
						if(this.x+30>=et.x&&this.x+30<=et.x+30
								&&this.y>=et.y&&this.y<=et.y+20)
						{
							return true;
						}
						//如果此坦克的右下角点在其它坦克矩形区域内
						if(this.x+30>=et.x&&this.x+30<=et.x+30
								&&this.y+20>=et.y&&this.y+20<=et.y+20)
						{
							return true;
						}
					}
				}
			}
			break;
		case 1: //坦克向左
			//取出所有的敌人坦克
			for(int i=0;i<ets.size();i++)
			{
				//取出第一个坦克
				EnemyTank et=ets.get(i);
				if(et!=this)//如果不是自己
				{
					//如果敌人的方向是向上或向下
					if(et.direct==3||et.direct==2)
					{
						//如果此坦克的左上角点在其它坦克矩形区域内
						if(this.x>=et.x&&this.x<=et.x+20
								&&this.y>=et.y&&this.y<=et.y+30)
						{
							return true;
						}
						//如果此坦克的左下角点在其它坦克矩形区域内
						if(this.x>=et.x&&this.x<=et.x+20
								&&this.y+20>=et.y&&this.y+20<=et.y+30)
						{
							return true;
						}
					}
					//如果敌人的方向是向左或向右
					if(et.direct==1||et.direct==0)
					{
						//如果此坦克的左上角点在其它坦克矩形区域内
						if(this.x>=et.x&&this.x<=et.x+30
								&&this.y>=et.y&&this.y<=et.y+20)
						{
							return true;
						}
						//如果此坦克的左下角点在其它坦克矩形区域内
						if(this.x>=et.x&&this.x<=et.x+30
								&&this.y+20>=et.y&&this.y+20<=et.y+20)
						{
							return true;
						}
					}
				}
			}
			break;
		case 2:	//我的坦克向下
			//取出所有的敌人坦克
			for(int i=0;i<ets.size();i++)
			{
				//取出第一个坦克
				EnemyTank et=ets.get(i);
				if(et!=this)//如果不是自己
				{
					//如果敌人的方向是向上或向下
					if(et.direct==3||et.direct==2)
					{
						//如果此坦克的左下角点在其它坦克矩形区域内
						if(this.x>=et.x&&this.x<=et.x+20
								&&this.y+30>=et.y&&this.y+30<=et.y+30)
						{
							return true;
						}
						//如果此坦克的右下角点在其它坦克矩形区域内
						if(this.x+20>=et.x&&this.x+20<=et.x+20
								&&this.y+30>=et.y&&this.y+30<=et.y+30)
						{
							return true;
						}
					}
					//如果敌人的方向是向左或向右
					if(et.direct==1||et.direct==0)
					{
						//如果此坦克的左下角点在其它坦克矩形区域内
						if(this.x>=et.x&&this.x<=et.x+30
								&&this.y+30>=et.y&&this.y+30<=et.y+20)
						{
							return true;
						}
						//如果此坦克的右下角点在其它坦克矩形区域内
						if(this.x+20>=et.x&&this.x+20<=et.x+30
								&&this.y+30>=et.y&&this.y+30<=et.y+20)
						{
							return true;
						}
					}
				}
			}
			break;
		case 3:	//坦克向上
			//取出所有的敌人坦克
			for(int i=0;i<ets.size();i++)
			{
				//取出第一个坦克
				EnemyTank et=ets.get(i);
				if(et!=this)//如果不是自己
				{
					//如果敌人的方向是向上或向下
					if(et.direct==3||et.direct==2)
					{
						//如果此坦克的左上角点在其它坦克矩形区域内
						if(this.x>=et.x&&this.x<=et.x+20
								&&this.y>=et.y&&this.y<=et.y+30)
						{
							return true;
						}
						//如果此坦克的右上角点在其它坦克矩形区域内
						if(this.x+20>=et.x&&this.x+20<=et.x+20
								&&this.y>=et.y&&this.y<=et.y+30)
						{
							return true;
						}
					}
					//如果敌人的方向是向左或向右
					if(et.direct==1||et.direct==0)
					{
						//如果此坦克的左上角点在其它坦克矩形区域内
						if(this.x>=et.x&&this.x<=et.x+30
								&&this.y>=et.y&&this.y<=et.y+20)
						{
							return true;
						}
						//如果此坦克的右上角点在其它坦克矩形区域内
						if(this.x+20>=et.x&&this.x+20<=et.x+30
								&&this.y>=et.y&&this.y<=et.y+20)
						{
							return true;
						}
					}
				}
			}
			break;
		}
		
		return b;
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
					if(x<380&&!this.isTouchOtherEnemy())//限制坦克不移出边界
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
					if(x>0&&!this.isTouchOtherEnemy())//限制坦克不移出边界
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
					if(y<280&&!this.isTouchOtherEnemy())//限制坦克不移出边界
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
					if(y>0&&!this.isTouchOtherEnemy())//限制坦克不移出边界
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
