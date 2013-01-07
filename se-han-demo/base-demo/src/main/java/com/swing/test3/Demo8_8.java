/*
 * JCombobox/JList/JScrollPane
 */

package com.swing.test3;
import java.awt.*;
import javax.swing.*;

public class Demo8_8 extends JFrame {

	//定义
	JPanel jp1,jp2;
	JLabel jl1,jl2;
	JComboBox jcb1;
	JList jlist;
	JScrollPane jsp;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Demo8_8 demo8_8=new Demo8_8();
	}
	
	//构造函数
	public Demo8_8()
	{
		jp1=new JPanel();
		jp2=new JPanel();
		
		jl1=new JLabel("你的籍贯：");
		jl2=new JLabel("旅游地点：");
		
		String []jg={"北京","上海","天津","火星"};
		jcb1=new JComboBox(jg);
		
		String []dd={"九寨沟","故宫","长城","天安门"};
		jlist=new JList(dd);
		jlist.setVisibleRowCount(2);//设置你希望显示多少个选项
		
		jsp=new JScrollPane(jlist);
		
		
		//设置布局
		this.setLayout(new GridLayout(3,1));
		
		//添加组件
		jp1.add(jl1); //加到面板1当中
		jp1.add(jcb1);
		
		jp2.add(jl2); //加到面板2当中
		jp2.add(jsp);
		
		this.add(jp1); //把面板加入到窗体当中
		this.add(jp2);
		
		//设置窗体属性
		this.setSize(200,180);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}
