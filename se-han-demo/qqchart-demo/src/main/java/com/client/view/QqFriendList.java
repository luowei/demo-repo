/**
 * 我的好友列表,(也包括陌生人，黑名单)
 */

package com.client.view;
import com.client.common.Message;
import com.client.tools.ManageQqChat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class QqFriendList extends JFrame implements ActionListener,MouseListener{

	//定义控件
	//第一张卡片(好友)
	JPanel jphy1,jphy2,jphy3;
	JButton jphy_jb1,jphy_jb2,jphy_jb3;
	JScrollPane jsp1;
	
	//第二张卡片(陌生人)
	JPanel jpmsr1,jpmsr2,jpmsr3;
	JButton jpmsr_jb1,jpmsr_jb2,jpmsr_jb3;
	JScrollPane jsp2;
	JLabel []jbls;
	JLabel []jbls2;
	String owner;
	
	//把整个JFrame设置成CardLayout布局
	CardLayout cl;
	
	public static void main(String[] args) {
		// 
		//QqFriendList qqFriendList=new QqFriendList();
	}

	//更新在线好友的情况
	public void updateFriend(Message m)
	{
		String onLineFriend[]=m.getCon().split(" ");
		for(int i=0;i<onLineFriend.length;i++)
		{
			//把对应的好友状态设置成在线
			jbls[Integer.parseInt(onLineFriend[i])-1].setEnabled(true);
			//System.out.println("创建了 "+jbls[Integer.parseInt(onLineFriend[i])-1]);
		}
	}
	
	public QqFriendList(String ownerId)
	{
		this.owner=ownerId;
		//处理第一张卡片(显示好友列表)
		jphy1=new JPanel(new BorderLayout());
		//假定有50个好友(50行,1列,两个4分别表示行间距与列间距)
		jphy2=new JPanel(new GridLayout(50,1,4,4));
		//给jphy2，初始化50好友
		jbls=new JLabel[50];
		for(int i=0;i<jbls.length;i++)
		{
			jbls[i]=new JLabel(i+1+"",new ImageIcon("image/mm.jpg"),JLabel.LEFT);
			jbls[i].addMouseListener(this);//监听每一个好友
			jbls[i].setEnabled(false);//把其它人物设置成不线状态
			if(jbls[i].getText().equals(ownerId))//把自己设置成在线状态
			{
				jbls[i].setEnabled(true);
			}
			jphy2.add(jbls[i]);
		}
		jphy3=new JPanel(new GridLayout(2,1));
		
		jphy_jb1=new JButton("我的好友");
		jphy_jb2=new JButton("陌生人");
		jphy_jb2.addActionListener(this);
		jphy_jb3=new JButton("黑名单");
		
		//把两个按钮加到jphy3中
		jphy3.add(jphy_jb2);
		jphy3.add(jphy_jb3);
		jsp1=new JScrollPane(jphy2);
		
		jphy1.add(jphy_jb1,"North");
		jphy1.add(jsp1,"Center");
		jphy1.add(jphy3,"South");
		
		
		//处理第二张卡片(陌生人)
		jpmsr1=new JPanel(new BorderLayout());
		jpmsr2=new JPanel(new GridLayout(20,1,4,4));
		jbls2=new JLabel[20];
		for(int i=0;i<jbls2.length;i++)
		{
			jbls2[i]=new JLabel(i+1+"",new ImageIcon("image/mm.jpg"),JLabel.LEFT);
			jbls2[i].addMouseListener(this);//监听每一个黑名单
//			jbls2[i].setEnabled(false);//把其它人物设置成不线状态
//			if(jbls2[i].getText().equals(ownerId))//把自己设置成在线状态
//			{
//				jbls2[i].setEnabled(true);
//			}
			jpmsr2.add(jbls2[i]);
		}
		jpmsr3=new JPanel(new GridLayout(2,1));
		
		jpmsr_jb1=new JButton("我的好友");
		jpmsr_jb1.addActionListener(this);
		jpmsr_jb2=new JButton("陌生人");
		jpmsr_jb3=new JButton("黑名单");
		
		//把两个按钮加到jpmsr3中
		jpmsr3.add(jpmsr_jb1);
		jpmsr3.add(jpmsr_jb2);
		jsp2=new JScrollPane(jpmsr2);
		
		jpmsr1.add(jpmsr3,"North");
		jpmsr1.add(jsp2,"Center");
		jpmsr1.add(jpmsr_jb3,"South");
		
		
		cl=new CardLayout();
		this.setLayout(cl);
		this.add(jphy1,"1");
		this.add(jpmsr1,"2");
		
		this.setSize(200,400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle(ownerId+"  好友列表");//窗口显示自己的编号
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//如果点击了陌生人按钮，就显示第二张卡片
		if(e.getSource()==jphy_jb2)
		{
			cl.show(this.getContentPane(),"2");
		}
		else if(e.getSource()==jpmsr_jb1)
		{
			cl.show(this.getContentPane(),"1");
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		//响应用户双击事件，并得到好友的编号
		if(e.getClickCount()==2)//双击
		{
			//得到该好友的编号
			String friendNo=((JLabel)e.getSource()).getText();
			//System.out.println("你希望和"+friendNo+"聊天");
			QqChat qqChat=new QqChat(owner,friendNo);
			//启动线程
			//Thread t=new Thread(qqChat);
			//t.start();
			
			//把聊天界面加入到管理类中
			ManageQqChat.addQqChat(this.owner + " " + friendNo, qqChat);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		//
		JLabel jl=(JLabel)e.getSource();
		jl.setForeground(Color.red);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		JLabel jl=(JLabel)e.getSource();
		jl.setForeground(Color.black);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
