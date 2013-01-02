/**
 * 功能：网络这间利用 对象流
 */

package com.simplechart;

import java.net.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.common.*;

public class MyServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyServer ms=new MyServer();
	}

	public MyServer()
	{
		try {
			ServerSocket ss=new ServerSocket(3456);
			System.out.println("服务器在3456端口监听...");
			Socket s=ss.accept();
			//以对象流方式读取(假设客户端发送的是User的一个对象)
			ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
			User u=(User)ois.readObject();//读取对象
			
			//输出
			System.out.println("从客户端接收到："+u.getName()+" "+u.getPass());
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
