/*
 * 异常
 */

package com.swing.test2;

import java.io.FileReader;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Test3 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//检查异常 
		//1.打开文件
		FileReader fr=null;
		try {
			fr=new FileReader("d:\\1.txt");
			//在出现异常的地方，就终止执行代码,然后进入到catch
			System.out.println("继续！");
			Socket s=new Socket("192.168.111.111",78);
		} catch (Exception e1) {//采用Exception可以捕获所有异常，因为它是父类
			//把异常的信息输出，利于排除bug
			System.out.println("\nException 内部");
			System.out.println("message:="+e1.getMessage());
			//System.exit(-1); //退出JVM
			e1.printStackTrace();
			//处理
		}
		finally //这个语句块，不管有没有异常都会执行
		{
			System.out.println("\n进入finally!");
			//一般说，把需要关闭的资源[文件，连接，内存....]
			if(fr!=null)
			{
				try {
					fr.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		//2.连接一个192.168.12.12 ip的端口号4567
		try {
			Socket s=new Socket("192.168.1.23",78);
		} catch (UnknownHostException e) {
			System.out.println("\nUnknownHostException 内部");
			e.printStackTrace();
		} catch (IOException e) {
			//如果有多个catch语句，则进入匹配异常的那个
			System.out.println("\nIOException 内部");
			e.printStackTrace();
		}
		
		//3.运行异常
		try
		{
			int a=4/0;
		}catch(ArithmeticException e)
		{
			e.printStackTrace();
		}
		
		System.out.println("ok1");
	}

}
