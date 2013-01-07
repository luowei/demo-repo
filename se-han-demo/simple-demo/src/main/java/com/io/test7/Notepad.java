/*
 * 我的记事本(界面+功能)
 */

package com.io.test7;
import java.io.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Notepad extends JFrame implements ActionListener{

	//定义需要的组件
	JTextArea jta=null; //文本域
	JMenuBar jmb=null;	//菜单条
	JMenu jml=null;		//第一JMenu
	JMenuItem jmi1=null;//菜单项
	JMenuItem jmi2=null;//菜单项
	public static void main(String[] args) {
		//
		Notepad notepad=new Notepad();
	}

	//构造函数
	public Notepad()
	{
		//创建jta
		jta=new JTextArea();
		jmb=new JMenuBar();
		jml=new JMenu("文件");
		//设置助记符
		jml.setMnemonic('F');
		jmi1=new JMenuItem("打开(o)",new ImageIcon("./src/new.jpg"));
		//注册监听
		jmi1.addActionListener(this);
		jmi1.setActionCommand("open");
		jmi2=new JMenuItem("保存(s)",new ImageIcon("./src/save.jpg"));
		jmi2.addActionListener(this);
		jmi2.setActionCommand("save");
		//加入菜单条
		this.setJMenuBar(jmb);
		//把菜单jml放入菜单条jmb
		jmb.add(jml);
		//把菜单项(jmi1)放入菜单(jml)
		jml.add(jmi1);
		jml.add(jmi2);
		
		//放入到JFrame
		this.add(jta);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(400,500);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//判断是哪个菜单被选中
		if(e.getActionCommand().equals("open"))//打开菜单
		{
			System.out.println("点击了打开菜单项！");
			//隆重推荐JFileChooser
			//文件选择组件
			JFileChooser jfc1=new JFileChooser();
			//设置名字
			jfc1.setDialogTitle("请选择文件....");
			//按默认方式显示打开文件对话框
			jfc1.showOpenDialog(null);
			//显示
			jfc1.setVisible(true);
			
			//得到用户选择的文件全路径
			String filename=jfc1.getSelectedFile().getAbsolutePath();
			
			//读取文件中的内容
			FileReader fr=null;
			BufferedReader br=null;
			try {
				fr=new FileReader(filename);
				br=new BufferedReader(fr);
				//从文件中读取信息并显示到jta
				String s="";
				String allCon="";
				while((s=br.readLine())!=null)
				{
					allCon+=s+"\r\n";
				}
				//放置到jta
				jta.setText(allCon);
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}finally{
				try {
					fr.close();
					br.close();
				} catch (Exception e3) {
					e3.printStackTrace();
				}
			}
		}
		else if(e.getActionCommand().equals("save"))
		{
			System.out.println("点击了保存菜单项！");
			//隆重推荐JFileChooser
			//出现保存对话框
			JFileChooser jfc1=new JFileChooser();
			//设置名字
			jfc1.setDialogTitle("另存为....");
			//按默认方式显示保存文件对话框
			jfc1.showSaveDialog(null);
			//显示
			jfc1.setVisible(true);
			
			//得到用户选择的文件全路径
			String filename=jfc1.getSelectedFile().getAbsolutePath();
			
			//把内容写入到指定的文件
			FileWriter fw=null;
			BufferedWriter bw=null;
			try {
				fw=new FileWriter(filename);
				bw=new BufferedWriter(fw);
				//写入
				bw.write(this.jta.getText());
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}finally{
				try {
					bw.close();
					fw.close();
				} catch (Exception e3) {
					e3.printStackTrace();
				}
			}
		}
	}
}
