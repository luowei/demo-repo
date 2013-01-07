/**
 * 全双工通信
 * 客户
 */

package com.test3;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;

public class MyClient3 extends JFrame implements ActionListener{

	JTextArea jta=null;
	JTextField jtf=null;
	JButton jb=null;
	JScrollPane jsp=null;
	JPanel jp1=null;
	
	//把信息发给客户端的对象
	PrintWriter pw=null;
	
	public static void main(String[] args) {
		MyClient3 ms=new MyClient3();
	}
	
	public MyClient3()
	{
		jta=new JTextArea();
		jsp=new JScrollPane(jta);
		jtf=new JTextField(10);
		jb=new JButton("发送");
		jb.addActionListener(this);
		
		jp1=new JPanel();
		jp1.add(jtf);
		jp1.add(jb);
		this.add(jsp,"Center");
		this.add(jp1,"South");

		this.setSize(300,200);
		this.setTitle("QQ简易聊天-客户端");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		try {
			Socket s=new Socket("127.0.0.1",9988);
			
			InputStreamReader isr=new InputStreamReader(s.getInputStream());
			BufferedReader br=new BufferedReader(isr);
			
			pw=new PrintWriter(s.getOutputStream(),true);
			while(true)
			{
				//不停的读取从服务器发来的信息
				String info=br.readLine();
				jta.append("服务器端对客户端说： "+info+"\r\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//如果用户按下发送信息的按钮
		if(e.getSource()==jb)
		{
			//把服务器在jtf写的内容发送给客户端
			String info=jtf.getText();
			//把客户端显示的信息加到jta
			jta.append("客户端对服务器说： "+info+"\r\n");
			pw.println(info);
			//清空jtf中的内容
			jtf.setText("");
		}
	}

}
