/*
 * 演示如果通过继续Thread来开发线程
 */
/*
 * 在java中一个类要当作线程来使用有两种方法：
 *	1.继承Thread类，并重写run函数
 *	2.实现Runable接口，并重写run函数
 */
package com.thread.test1;

public class Demo10_1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//创建一个Cat对象
		Cat cat=new Cat();
		//启动线程，会导致run函数的运行
		cat.start();
	}
}

class Cat extends Thread
{
	int times=0;
	//重写run函数
	public void run()
	{
		while(true)
		{
			try {
				//休眠一秒
				Thread.sleep(1000);
				//1000表示1000毫秒，sleep就会让该线程进入Blocked状态，并释放资源
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
			times++;
			System.out.println("Hello World "+times);
			if(times==10)
			{
				//退出
				break;
			}
		}
	}
}
