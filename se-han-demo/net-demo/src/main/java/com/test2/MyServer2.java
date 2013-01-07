/**
 * 半双工通信
 * 功能：一个服务器端，在9999端口上监听
 */

package com.test2;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.io.*;

public class MyServer2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MyServer2 ms2=new MyServer2();
	}
	
	public MyServer2()
	{
		try {
			//在9999端口监听
			ServerSocket ss=new ServerSocket(9999);
			System.out.println("服务器在9999监听...");
			//等待连接
			Socket s=ss.accept();
			
			//向s中写信息
			PrintWriter pw=new PrintWriter(s.getOutputStream(),true);
			
			//先接收客户端发来的信息(Socket上的)
			InputStreamReader isr=new InputStreamReader(s.getInputStream());
			BufferedReader br=new BufferedReader(isr);
			
			//接收从控制台输入的信息
			InputStreamReader isr2=new InputStreamReader(System.in);
			BufferedReader br2=new BufferedReader(isr2);
			
			while(true)
			{
				String infoFromClient=br.readLine();
				System.out.println("客户端发来："+infoFromClient);
				if(infoFromClient.equals("bye"))
				{
					System.out.println("对话结束！");
					s.close();
					break;
				}
				
				//接收从控制台输入的信息
				System.out.println("输入你希望对客户端说的话：");
				String response=br2.readLine();
				//把从控制台接受的信息送给客户端
				pw.println(response);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
