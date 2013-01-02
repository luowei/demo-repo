/**
 * 与好友聊天的界面
 * 因为客户端，要处于读取的状态，因此我们把它做成一个线程
 */

package com.client.view;
import com.client.common.Message;
import com.client.common.MessageType;
import com.client.tools.ManageClientConServerThread;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;


public class QqChat extends JFrame implements ActionListener{

	JTextArea jta;
	JTextField jtf;
	JButton jb;
	JPanel jp;
	String ownerId;
	String friendId;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//QqChat qqChat=new QqChat("1");
	}

	public QqChat(String ownerId,String friend)
	{
		this.ownerId=ownerId;
		this.friendId=friend;
		jta=new JTextArea();
		jtf=new JTextField(15);
		jb=new JButton("发送");
		jb.addActionListener(this);//监听
		jp=new JPanel();
		jp.add(jtf);
		jp.add(jb);
		
		this.add(jta,"Center");
		this.add(jp,"South");
		
		this.setSize(300,200);
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle(ownerId+" 正在和 "+friend+" 聊天");
		this.setIconImage((new ImageIcon("image/qq.gif")).getImage());
		this.setVisible(true);
		//this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
	}

	//写一个方法，让它显示消息
	public void showMessage(Message m)
	{
		//显示
		String info=m.getSender()+"  对"+m.getGetter()+" 说："+m.getCon()+"\r\n";
		this.jta.append(info);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//判断
		if(e.getSource()==jb)
		{
			//如果用户点击了 发送按钮
			Message m=new Message();
			m.setMesType(MessageType.message_comm_mes);
			m.setSender(this.ownerId);
			m.setGetter(this.friendId);
			m.setCon(jtf.getText());
			m.setSendTime(new java.util.Date().toString());
			try {
				//发送给服务器
				ObjectOutputStream oos=new ObjectOutputStream
					(ManageClientConServerThread.getClientConServerThread(ownerId).getS().getOutputStream());
				//通过管理线程的类取得线程，然后通过线程取得对应线程中的Socket,再通过这个Socket输出流取得Socket中内容
				oos.writeObject(m);//发送
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		}
	}

//	@Override
//	public void run() {
//		//读取状态
//		while(true)
//		{
//			//读取[如果读不到就等待]
//			try {
//				ObjectInputStream ois=new ObjectInputStream(QqClientConServer.s.getInputStream());
//				Message m=(Message)ois.readObject();
//				
//				//显示
//				String info=m.getSender()+"  对"+m.getGetter()+" 说："+m.getCon()+"\r\n";
//				this.jta.append(info);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			
//		}
//	}
}
