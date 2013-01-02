/**
 * 全双工通信
 * 服务器端
 */

package com.test3;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;

public class MyServer3 extends JFrame implements ActionListener{

	JTextArea jta=null;
	JTextField jtf=null;
	JButton jb=null;
	JScrollPane jsp=null;
	JPanel jp1=null;
	
	//把信息发给客户端的对象
	PrintWriter pw=null;
	
	public static void main(String[] args) {
		MyServer3 ms=new MyServer3();
	}
	
	public MyServer3()
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
		this.setTitle("QQ简易聊天-服务器端");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		try {
			//服务器监听
			ServerSocket ss=new ServerSocket(9988);
			//等待客户端连接
			Socket s=ss.accept();
			//System.out.println("服务器端在9988监听！");
			
			InputStreamReader isr=new InputStreamReader(s.getInputStream());
			BufferedReader br=new BufferedReader(isr);
			
			pw=new PrintWriter(s.getOutputStream(),true);
			
			//读取从客户端发来的信息
			while(true)
			{
				//读取从客户端发来的信息
				String info=br.readLine();
				//把信息显示加到文本域中(以追加的形式)
				jta.append("客户端对服务器说： "+info+"\r\n");
			}
		} catch (IOException e) {
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
			jta.append("服务器端对客户端说： "+info+"\r\n");
			pw.println(info);
			//清空jtf中的内容
			jtf.setText("");
		}
	}
}
