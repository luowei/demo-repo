/*
 * 功能：演示使用线程的注意事项
 */

package com.tank.test1;

public class Demo11_1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//启动线程
		Cat cat1=new Cat();
		cat1.start();
		//cat1.start();//一个线程类只能启动一次
		
		Dog dog1=new Dog();
		Thread t=new Thread(dog1);
		Thread t2=new Thread(dog1);
		t.start();
		t2.start(); 
		//t2.start();//一个线程类只能启动一次
		
//结论：不管是通过继承Thread,还是通实现Runnable接口创建线程，
//它们的一个对象只能启动一次，否则就会有异常抛出。
	}

}

//猫类
class Cat extends Thread
{
	public void run()
	{
		System.out.println("11");
	}
}

//狗类
class Dog implements Runnable
{

	@Override
	public void run() {
		System.out.println("22");
		
	}
	
}
