/**
 * 这是一个客户端程序，可以连接服务器端
 */

package com.test1;

import java.io.*;
import java.net.*;

public class MyClient1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MyClient1 mc1=new MyClient1();
	}
	
	public MyClient1()
	{
		try {
			//Socket()就是去连接某个服务器端，127.0.0.1表示服务器的IP
			//9999就是端口号
			Socket s=new Socket("127.0.0.1",9999);
			//如果s连接成功，就可以发送数据给服务器
			//通过pw,向s写数据，true表示即时刷新
			PrintWriter pw=new PrintWriter(s.getOutputStream(),true);
			pw.println("你好吗？我是客户端");
			
			//客户端接收
			InputStreamReader isr=new InputStreamReader(s.getInputStream());
			BufferedReader br=new BufferedReader(isr);
			
			String response=br.readLine();
			
			System.out.println("我是客户端!\n我收到了服务器回复的信息:"+response);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
