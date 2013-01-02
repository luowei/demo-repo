/*
 * 记事本界面
 */

package com.swing.test3;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Demo8_12 extends JFrame{
		JMenuBar jmb;//菜单条组件
		JMenu menu1,menu2,menu3,menu4,menu5;
		JMenuItem item2,item3,item4,item5,item6,item7;
		JMenu xinjian;//二级菜单
		JMenuItem file,project;
		
		JTextArea jta; //文本输入区域
		
		//工具条
		JToolBar jtb;
		JButton jb1,jb2,jb3,jb4,jb5,jb6;
	
	public Demo8_12()
	{
		//创建组件
		jtb=new JToolBar();
		jb1=new JButton(new ImageIcon("images\\new.jpg"));
		jb1.setToolTipText("新建");//设置提示信息
		jb2=new JButton(new ImageIcon("images\\open.jpg"));
		jb2.setToolTipText("打开");
		jb3=new JButton(new ImageIcon("images\\save.jpg"));
		jb4=new JButton(new ImageIcon("images\\copy.jpg"));
		jb5=new JButton(new ImageIcon("images\\cut.jpg"));
		jb6=new JButton(new ImageIcon("images\\paste.jpg"));
		
		jmb=new JMenuBar();
		
		menu1=new JMenu("文件(F)");
		menu1.setMnemonic('F');//设置助记符
		menu2=new JMenu("编辑(E)");
		menu2.setMnemonic('E');
		menu3=new JMenu("格式(O)");
		menu3.setMnemonic('O');
		menu4=new JMenu("查看(V)");
		menu4.setMnemonic('V');
		menu5=new JMenu("帮助(H)");
		menu5.setMnemonic('H');
		
		//item1=new JMenuItem("新建");
		xinjian=new JMenu("新建");
		file=new JMenuItem("文件");
		project=new JMenuItem("工程");
		
		item2=new JMenuItem("打开",new ImageIcon("new.gif"));
		item3=new JMenuItem("保存(s)");
		item3.setMnemonic('s');
		
		//给菜单添加快捷方式
		item3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,InputEvent.ALT_MASK));
		item4=new JMenuItem("另存为");
		item5=new JMenuItem("页面设置");
		item6=new JMenuItem("打印");
		item7=new JMenuItem("退出");
		
		jta=new JTextArea();
		
		//设置布局
		
		//添加组件
		//将按钮添加到工具条上
		jtb.add(jb1);
		jtb.add(jb2);
		jtb.add(jb3);
		jtb.add(jb4);
		jtb.add(jb5);
		jtb.add(jb6);
		
		//将菜单项添加到菜单上
		xinjian.add(file);
		xinjian.add(project);
		
		menu1.add(xinjian);
		menu2.add(item2);
		menu1.add(item3);
		menu1.add(item4);
		menu1.addSeparator();//添加分割线
		menu1.add(item5);
		menu1.add(item6);
		menu1.addSeparator();
		menu1.add(item7);
		
		//将菜单添加到菜单条上
		jmb.add(menu1);
		jmb.add(menu2);
		jmb.add(menu3);
		jmb.add(menu4);
		jmb.add(menu5);
		
		//将菜单条添加到窗体上
		this.setJMenuBar(jmb);
		
		//将工具条添加到窗体上
		this.add(jtb,BorderLayout.NORTH);
		
		//给文本域添加滚动条
		JScrollPane jsp=new JScrollPane(jta);
		jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		this.add(jsp);
		
		//展现
		this.setTitle("记事本");
		ImageIcon icon=new ImageIcon("images\\rowin.gif");
		this.setIconImage(icon.getImage());//设置窗体图标
		this.setSize(500,400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
		
	public static void main(String[] args) {
		Demo8_12 demo8_12=new Demo8_12();
	}

}
