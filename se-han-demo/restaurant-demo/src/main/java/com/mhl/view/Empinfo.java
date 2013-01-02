/*
 * 用于显示人事管理的界面
 */

package com.mhl.view;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import com.mhl.model.EmpModel;
import com.mhl.tools.MyTools;

public class Empinfo extends JPanel implements ActionListener{
	
	//定义需要的各个组件
	JPanel p1,p2,p3,p4,p5;
	JLabel p1_lab1,p3_lab1;
	JTextField p1_jtf1;
	JButton p1_jb1,p4_jb1,p4_jb2,p4_jb3,p4_jb4;
	//这个是用于显示人事信息的tabel
	JTable jtable; //表格面板
	JScrollPane jsp;//滚动面板
	EmpModel em=null; //定义数据模型
	
	//构造函数
	public Empinfo()
	{
		//创建需要的组件
		//处理p1(北面的)
		p1=new JPanel(new FlowLayout(FlowLayout.CENTER));
		p1_lab1=new JLabel("请输入姓名(员工号或职位):");
		p1_lab1.setFont(MyTools.f2);
		p1_jtf1=new JTextField(20);
		p1_jb1=new JButton("查询");
		p1_jb1.setFont(MyTools.f3);
		//把他们加入到p1
		p1.add(p1_lab1);
		p1.add(p1_jtf1);
		p1.add(p1_jb1);
		
		//处理p2(中间的)
		em=new EmpModel();
		String []paras={"1"};
		em.query("select cleId,cleName,cleSex,cleZw  from clerkInfo where 1=?",paras);
		jtable=new JTable(em);
		p2=new JPanel(new BorderLayout());
		jsp=new JScrollPane(jtable);
		p2.add(jsp);
		//p2.add(jtable);
		
		//处理p3(南面的)
		p3=new JPanel(new FlowLayout(FlowLayout.LEFT));
		p3_lab1=new JLabel("总记录数是？？条");
		p3_lab1.setFont(MyTools.f2);
		p3.add(p3_lab1);
		p4=new JPanel(new FlowLayout(FlowLayout.RIGHT));
		p4_jb1=new JButton("详细信息");
		p4_jb1.setFont(MyTools.f3);
		p4_jb2=new JButton("添加");
		p4_jb2.setFont(MyTools.f3);
		p4_jb3=new JButton("修改");
		p4_jb3.setFont(MyTools.f3);
		p4_jb4=new JButton("删除");
		p4_jb4.addActionListener(this);
		p4_jb4.setFont(MyTools.f3);
		p4.add(p4_jb1);
		p4.add(p4_jb2);
		p4.add(p4_jb3);
		p4.add(p4_jb4);
		p5=new JPanel(new BorderLayout());
		p5.add(p3,"West");
		p5.add(p4,"East");
		
		//把总JPanel设为BorderLayout
		this.setLayout(new BorderLayout());
		//把p1加入到总的JPanel
		this.add(p1,"North");//北面
		this.add(p2,"Center");//中间
		this.add(p5,"South");//南面
		//this.setBackground(Color.pink);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//如果用户要删除某个员工
		if(this.p4_jb4==e.getSource())
		{
			//System.out.println("删除");
			int selRowNum=jtable.getSelectedRow();
			String empNo=(String)em.getValueAt(selRowNum, 0);
			System.out.println("员工编号："+empNo);
			if(em.deEmpById(empNo))
			{
				JOptionPane.showMessageDialog(null, "删除成功");
			}
			else
				if(em.deEmpById(empNo))
				{
					JOptionPane.showMessageDialog(null, "删除失败");
				}
			EmpModel em=new EmpModel();
			String []paras={"1"};
			em.query("select cleId,cleName,cleSex,cleZw from clerkInfo where 1=?", paras);
			jtable.setModel(em); //更新数据模型
		}
	}
}
