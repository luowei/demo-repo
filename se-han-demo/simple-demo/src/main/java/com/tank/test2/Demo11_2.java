/*
 * 功能：模拟一个机票售票系统：有三个售票点，
 * 在1天卖出2000张票。（注是一共有2000张票）
 */

package com.tank.test2;

public class Demo11_2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//定义三个售票窗口
		TicketWindow tw1=new TicketWindow();
		//TicketWindow tw2=new TicketWindow();
		//TicketWindow tw3=new TicketWindow();
		
		//创建三个线程
		Thread t1=new Thread(tw1);
		Thread t2=new Thread(tw1);
		Thread t3=new Thread(tw1);
		
		//启动三个卖票线程
		t1.start();
		t2.start();
		t3.start();
	}
}

//售票窗口类
class TicketWindow implements Runnable
{
	//一共两千张票
	private static int nums=2000;
	//private Dog myDog=new Dog();
	
	public void run()
	{
		while(true)
		{
			//认为if else 要保证其原子性[同步代码块]
			//synchronized(myDog) //对象锁，保证同步安全
			synchronized(this)
			{
				//先判断是否还有票
				if(nums>0)
				{
					System.out.println(Thread.currentThread().getName()+" 在售出第"+nums+"张票");
					nums--;
					
					//出票速度是1秒出一张
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else
				{
					//售票结束
					break;
				}
			}
		}
	}
}

class Dog
{
	
}
