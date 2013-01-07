package com.simplechart;
import java.io.*;
import java.net.*;

import com.common.User;
import com.test1.*;
public class MyClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//
		MyClient mc=new MyClient();
	}

	public MyClient()
	{
		try {
			Socket s=new Socket("127.0.0.1",3456);
			//通过ObjectOutputStream给服务器传送对象
			ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
			User u=new User();
			u.setName("άΨΪΪ");
			u.setPass("123456");
			oos.writeObject(u); //写入对象流
			//如果希望一个对象在网络上传输，则需要把这个对象序列化
			//System.out.println("ok");
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
