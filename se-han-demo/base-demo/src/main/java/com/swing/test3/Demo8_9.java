/*
 * 词霸
 */

package com.swing.test3;
import javax.swing.*;

public class Demo8_9 extends JFrame{

	//定义组件
	JSplitPane jsp;
	JList jList;
	JLabel jl1;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Demo8_9 demo8_9=new Demo8_9();
	}

	public Demo8_9()
	{
		//创建组件
		String []words={"迈克尔-杰克逊","中国","导游"};
		jList=new JList(words);
		
		jl1=new JLabel(new ImageIcon("images/迈克尔-杰克逊2.jpg"));
		
		//拆分窗格
		jsp=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,jList,jl1);
		
		//设置窗格可以伸缩
		jsp.setOneTouchExpandable(true);
		
		//设置布局管理器
		
		
		//添加组件
		this.add(jsp);
		
		//设置大小
		this.setSize(550,632);
		this.setLocation(200,200);
		this.setVisible(true);
	}
}
