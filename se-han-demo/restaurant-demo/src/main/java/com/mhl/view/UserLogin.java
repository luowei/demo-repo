/*
 * 登录界面
 */

package com.mhl.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import com.mhl.model.*;
import com.mhl.tools.*;

import javax.imageio.*;

public class UserLogin extends JDialog implements ActionListener{
	//定义需要的组件
	JLabel jl1,jl2,jl3;
	JTextField jName;
	JPasswordField jPasswd;
	JButton jCon,jCancel;
	
	
	public static void main(String []args)
	{
		UserLogin ul=new UserLogin();
	}
	
	public UserLogin()
	{
		this.setLayout(null);
		//创建一个BackImage对象
		BackImage bi=new BackImage();
		//把位置确定
		bi.setBounds(0,0,660,660);
		//定义一个容器
		Container ct=this.getContentPane();
		
		//创建各个组件
		//用户名jl1标签
		jl1=new JLabel("请输入用户名：");
		jl1.setFont(MyTools.f1);
		jl1.setBounds(60,190,150,30);
		//放入jl1
		//this.add(jl1);
		ct.add(jl1); //这样也可以
		
		//用户名jName文本框
		jName=new JTextField(20);
		jName.setFocusable(true);//设置成焦点
		jName.setBounds(180,190,120,30);
		jName.setFont(MyTools.f1);
		//向下凹陷
		jName.setBorder(BorderFactory.createLoweredBevelBorder());
		ct.add(jName);
		
		//员工号标签
		jl2=new JLabel("(员工号)");
		jl2.setFont(MyTools.f2);
		jl2.setForeground(Color.RED);//设置前景色
		jl2.setBounds(100,210,100,30);
		ct.add(jl2);
		
		//密码标签
		jl3=new JLabel("请输入密码：");
		jl3.setFont(MyTools.f1);
		jl3.setBounds(60,240,150,30);
		ct.add(jl3);
		
		//密码框
		jPasswd=new JPasswordField(20);
		jPasswd.setBounds(180,240,120,30);
		jPasswd.setFont(MyTools.f1);
		jPasswd.setBorder(BorderFactory.createLoweredBevelBorder());
		ct.add(jPasswd);
		
		
		//确定按钮
		jCon=new JButton("确定");
		jCon.addActionListener(this);//监听
		jCon.setFont(MyTools.f1);
		jCon.setBounds(110,300,70,30);
		//放入到容器
		ct.add(jCon);
		
		//取消按钮
		jCancel=new JButton("取消");
		jCancel.addActionListener(this);//监听
		jCancel.setFont(MyTools.f1);
		jCancel.setBounds(200,300,70,30);
		//放入到容器
		ct.add(jCancel);
		
		//把一组件放入到JFrame,或者JDialog中可以直接放入
		this.add(bi);
		//也可以定义一个容器，再利用容器对象加入
		//ct.add(bi);//加入背景图片
		
		this.setUndecorated(true);//不使用上下框
		this.setSize(360,360);
		//确定登录框的初始位置
		int width=Toolkit.getDefaultToolkit().getScreenSize().width;
		int height=Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setLocation(width/2-200,height/2-150);
		
		this.setVisible(true);
	}
	//内部类
	class BackImage extends JPanel
	{
		Image im;
		public BackImage()//加载背景图片
		{
			try {
				im=ImageIO.read(new File("image/login.gif"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		public void paintComponent(Graphics g)//画上背景图片
		{
			g.drawImage(im, 0, 0, 360, 360, this);
		}
	}
	//响应用户登录的请求
	@Override
	public void actionPerformed(ActionEvent e) {
		//判断是否点击确定按钮
		if(e.getSource()==jCon)
		{
			//System.out.println("确定");
			//取出员工号，密码
			String uid=this.jName.getText().trim();
			String p=new String(this.jPasswd.getPassword());
			//System.out.println(uid+" "+p);
			UserModel um=new UserModel();
			String res=um.checkUser(uid, p);
			if(""==res)
			{
				JOptionPane.showMessageDialog(this,"您输入的用户名或密码有误，请重新输入！");
				return;
			}
			//System.out.println(uid+"职位是："+res);
			if(res.equals("经理")||res.equals("主管")||res.equals("管理员"))
			{
				new Windows1();
				//关闭登录界面
				this.dispose();
			}
			else
			{
				JOptionPane.showMessageDialog(this,"你无权登录！");
			}
		}
		else if(e.getSource()==this.jCancel)
		{
			//关闭登录界面
			this.dispose();
		}
	}
}
