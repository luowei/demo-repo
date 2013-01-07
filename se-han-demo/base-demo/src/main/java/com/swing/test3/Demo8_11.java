/*
 * qq登录界面制作
 */

package com.swing.test3;
import java.awt.*;
import javax.swing.*;

public class Demo8_11 extends JFrame{

	//北部区域
	JLabel jl1;
	
	//南部区域
	JButton jb1,jb2,jb3;
	JPanel jp1;
	
	//中部区域
	JTabbedPane jtp;//选项卡窗格
	JPanel jp2,jp3,jp4;//三个子面板
	
	JLabel jl2,jl3,jl4,jl5;
	
	//号码输入文本框
	JTextField jtf;
	//密码
	JPasswordField jpf;
	//清除号码button
	JButton jb4;
	//隐身登录，记住密码
	JCheckBox jcb1,jcb2;
	
	public Demo8_11()
	{
		//创建组件
		jl2=new JLabel("QQ号码",JLabel.CENTER);
		jl3=new JLabel("QQ密码",JLabel.CENTER);
		jl4=new JLabel("忘记密码",JLabel.CENTER);
		
		jl4.setFont(new Font("宋体",Font.PLAIN,16));//设置字体样式
		jl4.setForeground(Color.BLUE);//设置字体颜色
		jl5=new JLabel("<html><a href='www.qq.com'>申请密码保护</a></html>");
		jl5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		//输入号码框
		jtf=new JTextField();
		//输入密码框
		jpf=new JPasswordField();
		jb4=new JButton(new ImageIcon("images\\清除号码.jpg"));
		
		//复选框
		jcb1=new JCheckBox("隐身登录");
		jcb2=new JCheckBox("记住密码");
		
		//北部区域
		jl1=new JLabel(new ImageIcon("images\\老虎.jpg"));
		
		//南部区域
		jp1=new JPanel();
		jb1=new JButton(new ImageIcon("images\\登录.gif"));
		jb2=new JButton(new ImageIcon("images\\取消.gif"));
		jb3=new JButton(new ImageIcon("images\\向导.gif"));
		
		//中部区域
		jtp=new JTabbedPane();
		jp2=new JPanel();
		jp3=new JPanel();
		jp3.setBackground(Color.RED);//给面板设置背景颜色
		jp4=new JPanel();
		jp4.setBackground(new Color(0,0,255));//用另一种方法设置背景颜色
		
		//将面板添加到选项卡窗格上
		jtp.add("QQ号码",jp2);	//第一个参数代表选项卡的名称，第二个参数代表面板
		jtp.add("手机号码",jp3);	
		jtp.add("电子邮箱",jp4);
		
		//设置布局
		jp2.setLayout(new GridLayout(3,3));
		
		//添加组件
		//南部
		jp1.add(jb1);	
		jp1.add(jb2);
		jp1.add(jb3);
		
		//中部
		jp2.add(jl2);//QQ号码
		jp2.add(jtf);//号码框
		jp2.add(jb4);//清除号码按钮
		jp2.add(jl3);//密码
		jp2.add(jpf);//密码框
		jp2.add(jcb1);//记住密码
		jp2.add(jcb2);//隐身登录
		jp2.add(jl5);//申请密码保护
		
		this.add(jp1,BorderLayout.SOUTH);//加入南部子面板
		this.add(jl1,BorderLayout.NORTH);//加入北部子面板
		this.add(jtp,BorderLayout.CENTER);//把选项卡加入中部
		
		//展现组件
		ImageIcon icon=new ImageIcon("images\\rowin.gif");
		this.setIconImage(icon.getImage());
		this.setSize(350,240);
		this.setLocation(300,400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}
	
	public static void main(String[] args) {
		new Demo8_11();
	}
}
