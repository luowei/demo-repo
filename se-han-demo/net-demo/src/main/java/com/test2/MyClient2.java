/**
 * 半双工通信
 * 功能：客户端
 */

package com.test2;
import java.net.*;
import java.io.*;

public class MyClient2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MyClient2 mc2=new MyClient2();
	}

	public MyClient2()
	{
		try {
			//连接服务器端
			Socket s=new Socket("127.0.0.1",9999);
			//向s写入数据
			PrintWriter pw=new PrintWriter(s.getOutputStream(),true);
			
			//先接收从控制台输入的信息
			InputStreamReader isr=new InputStreamReader(System.in);
			BufferedReader br=new BufferedReader(isr);
			
			//接收从服务器端发送过来的信息
			InputStreamReader isr2=new InputStreamReader(s.getInputStream());
			BufferedReader br2=new BufferedReader(isr2);
			
			while(true)
			{
				System.out.println("请输入你想对服务器说的话：");
				//客户端先从控制台接收
				String info=br.readLine();
				
				//然后把从控制台输入的信息发送给服务器
				pw.println(info);
				if(info.equals("bye"))
				{
					System.out.println("对话结束");
					s.close();
					break;
				}
				//接收从服务器发来的话
				String res=br2.readLine();
				System.out.println("服务器说："+res);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
