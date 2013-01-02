/*
 * 功能：用Runable接口实现多线程
 */

package com.thread.test2;

public class Demo10_2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//注意启动-------------------------------------------
		Dog dog=new Dog();
		//创建一个Thread对象
		Thread t=new Thread(dog);
		t.start();
		//---------------------------------------------------
	}

}

class Dog implements Runnable
{
	int times=0;
	public void run()
	{
		while(true)
		{
			try {
				//休眠一秒
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			times++;
			System.out.println("Hello "+times);
			if(times==10)
			{
				//退出
				break;
			}
		}
	}
}
