/**
 * 这是客户端和服务器端保持通讯的线程
 */

package com.client.tools;
import com.client.common.Message;
import com.client.common.MessageType;
import com.client.view.QqChat;
import com.client.view.QqFriendList;

import java.io.*;
import java.net.*;


public class ClientConServerThread extends Thread{
	
	private Socket s;
	public Socket getS() {
		return s;
	}
	public void setS(Socket s) {
		this.s = s;
	}
	//构造函数
	public ClientConServerThread(Socket s)
	{
		this.s=s;
	}
	public void run()
	{
		while(true)
		{
			//不停的读取从服务器端发来的消息
			try {
				ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
				Message m=(Message)ois.readObject();
//				System.out.println("读取到从服务器发来的消息"+m.getSender()+" 给"+
//						m.getGetter()+" 内容："+m.getCon());
				
				//如果是普通包
				if(m.getMesType().equals(MessageType.message_comm_mes))
				{
					//把从服务器获得的消息，显示到该显示的聊天界面
					QqChat qqChat=ManageQqChat.getQqChat(m.getGetter()+" "+m.getSender());
					//显示
					qqChat.showMessage(m);
				}
				//如果返回的是好友上线的包
				else if(m.getMesType().equals(MessageType.message_ret_onLineFriend))
				{
					//System.out.println("客户端接收到:"+m.getCon());
					String con=m.getCon();
					String friends[]=con.split(" ");
					String getter=m.getGetter();
					//System.out.println("getter="+getter);
					
					//修改相应的好友列表.
					QqFriendList qqFriendList=ManageQqFriendList.getQqFriendList(getter);
					
					if(qqFriendList!=null)
					{
						//更新在线好友
						qqFriendList.updateFriend(m);
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
