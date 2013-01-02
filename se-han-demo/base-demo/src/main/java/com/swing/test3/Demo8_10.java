/*
 * qq聊天器
 */

package com.swing.test3;
import java.awt.*;
import javax.swing.*;

public class Demo8_10 extends JFrame{

	JTextArea jta=null;
	JScrollPane jsp=null; //滚动面板
	JPanel jp1=null;
	JComboBox jcb=null;
	JTextField jtf=null;
	JButton jb=null;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Demo8_10 demo8_10=new Demo8_10();
	}

	//构造
	public Demo8_10()
	{
		jta=new JTextArea();
		jsp=new JScrollPane(jta); //为文本域添加滚动条
		jp1=new JPanel();
		String []chatter={"布什","拉登"};
		jcb=new JComboBox(chatter);
		jtf=new JTextField(10);
		jb=new JButton("发送");
		
		//设置布局
		
		//添加组件
		jp1.add(jcb);
		jp1.add(jtf);
		jp1.add(jb);
		
		//加入JFrame
		this.add(jsp);
		this.add(jp1,BorderLayout.SOUTH);
		
		//设置窗体属性
		this.setSize(300,200);
		this.setIconImage((new ImageIcon("images\\rowin2.gif")).getImage());
		this.setTitle("简单QQ");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}
