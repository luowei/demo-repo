/**
 * 功能：qq客户端登录界面
 */

package com.client.view;
import com.client.common.Message;
import com.client.common.MessageType;
import com.client.common.User;
import com.client.model.QqClientUser;
import com.client.tools.ManageClientConServerThread;
import com.client.tools.ManageQqFriendList;

import javax.swing.*;
import java.io.*;


import java.awt.*;
import java.awt.event.*;

public class QqClientLogin extends JFrame implements ActionListener{

	//定义北部需要的组件
	JLabel jbl1;
	
	//定义中部需要的组件
	//中部有三个JPanel，由一个选项卡窗口管理
	JTabbedPane jtp;
	JPanel jp2,jp3,jp4; //jp2放qq号码输入
	JLabel jp2_jbl1,jp2_jbl2,jp2_jbl3,jp2_jbl4;
	JButton jp2_jb1;
	JTextField jp2_jtf;
	JPasswordField jp2_jpf;
	JCheckBox jp2_jcb1,jp2_jcb2;
	
	//定义南部需要的组件
	JPanel jp1;
	JButton jp1_jb1,jp1_jb2,jp1_jb3;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		QqClientLogin qqClientLogin=new QqClientLogin();
	}

	public QqClientLogin()
	{
		//处理北部
		jbl1=new JLabel(new ImageIcon("image/tou.gif"));
		
		//处理中部
		jp2=new JPanel(new GridLayout(3,3));
		jp2_jbl1=new JLabel("QQ号码",JLabel.CENTER);
		jp2_jbl2=new JLabel("QQ密码",JLabel.CENTER);
		jp2_jbl3=new JLabel("忘记密码",JLabel.CENTER);
		jp2_jbl3.setForeground(Color.blue);
		jp2_jbl4=new JLabel("申请密码保护",JLabel.CENTER);
		jp2_jb1=new JButton(new ImageIcon("image/clear.gif"));
		jp2_jtf=new JTextField();
		jp2_jpf=new JPasswordField();
		jp2_jcb1=new JCheckBox("隐身登录");
		jp2_jcb2=new JCheckBox("记住密码");
		
		//把控件按照顺序加入到jp2
		jp2.add(jp2_jbl1);
		jp2.add(jp2_jtf);
		jp2.add(jp2_jb1);
		jp2.add(jp2_jbl2);
		jp2.add(jp2_jpf);
		jp2.add(jp2_jbl3);;
		jp2.add(jp2_jcb1);
		jp2.add(jp2_jcb2);
		jp2.add(jp2_jbl4);
		
		jtp=new JTabbedPane();//创建选项卡窗口
		jtp.add("QQ号码",jp2);
		jp3=new JPanel();
		jtp.add("手机号码",jp3);
		jp4=new JPanel();
		jtp.add("电子邮件",jp4);
		
		//处理南部
		jp1=new JPanel();
		jp1_jb1=new JButton(new ImageIcon("image/denglu.gif"));
		//响应用户点击登录
		jp1_jb1.addActionListener(this);
		jp1_jb2=new JButton(new ImageIcon("image/quxiao.gif"));
		jp1_jb3=new JButton(new ImageIcon("image/xiangdao.gif"));
		
		//把三个按钮放入到jp1
		jp1.add(jp1_jb1);
		jp1.add(jp1_jb2);
		jp1.add(jp1_jb3);
		
		this.add(jbl1,"North");	//北部
		this.add(jtp,"Center");
		this.add(jp1,"South");	//南部
		
		this.setSize(350,240);
		this.setTitle("QQ登录");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//如果要用户点击了登录
		if(e.getSource()==jp1_jb1)
		{
			QqClientUser qqClientUser=new QqClientUser();
			User u=new User();
			u.setUserId(jp2_jtf.getText().trim());
			u.setPassWd(new String(jp2_jpf.getPassword()));
			
			if(qqClientUser.checkUser(u))
			{	
				try {
					//创建好友列表
					QqFriendList qqList=new QqFriendList(u.getUserId());
					//并将登录成功的加入到好友列表
					ManageQqFriendList.addQqFrendList(u.getUserId(), qqList);
					
					//发送一个要求返回在线好友的请求包
					ObjectOutputStream oos=new ObjectOutputStream
						(ManageClientConServerThread.getClientConServerThread
                                (u.getUserId()).getS().getOutputStream());
					
					//做一个message包
					Message m=new Message();
					//指明包的类型
					m.setMesType(MessageType.message_get_onLineFriend);
					//并在包中指明是这个QQ号的好友情况
					m.setSender(u.getUserId());
					oos.writeObject(m);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				//关闭掉登录界面
				this.dispose();
			}else{
				JOptionPane.showMessageDialog(this, "用户名或密码错误");
			}
		}
	}
}
