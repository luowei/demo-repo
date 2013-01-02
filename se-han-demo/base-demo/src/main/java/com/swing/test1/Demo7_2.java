/*
 * 功能：做一个公司职员薪水管理系统，要求完成如下功能 ：
 * 1.当有新员工时，将该员工加入到管理系统
 * 2.可以根据员工号，显示该员工的信息
 * 3.可以显示所员工信息
 * 4.可以修改员工薪水
 * 5.当员工离职时，将该员工从管理系统中删除
 * 6.可以按照薪水从低到高顺序排序
 * 7.可以统计员工的平均工资和最低、最高工资
 */

package com.swing.test1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Demo7_2 {

	/**
	 * @param args
	 * @throws java.io.IOException
	 */
	public static void main(String[] args) throws IOException {
		//演示
		String a=new String("abc");
		String b=new String("abc");
		if(a==b)
		{
			System.out.println("== ok");
		}
		else if(a.equals(b))
		{
			System.out.println("equals ok");
		}
		else ;
		
		//创建EmpManage对象
		EmpManage em=new EmpManage();
		//Emp emptemp=new Emp(null,null,0);
		//定义一个BufferedReader流
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		//作出一个简单菜单
		while(true)
		{
			System.out.println("请选择你要进行的操作：");
			System.out.println("1:表示添加一个雇员");
			System.out.println("2:查找一个雇员");
			System.out.println("3:显示所有雇员信息");
			System.out.println("4:修改一个雇员工资");
			System.out.println("5:删除一个雇员");
			System.out.println("6：退出系统");
			
			String operType=br.readLine();
			
			if(operType.equals("1"))
			{
				System.out.println("请输入编号：");
				String empNo=br.readLine();
				System.out.println("请输入名字：");
				String name=br.readLine();
				System.out.println("请输入工资：");
				float sal=Float.parseFloat(br.readLine());
				
				Emp emp=new Emp(empNo,name,sal);
				Emp emptemp=em.addEmp(emp);
				//这里的"="相当于将返回的emp内存块直接复制一份给emptemp,即创建的同时
				//也就初始化了，所心不必再考的emptemp内部的属性是private，还是public,"="相当于直接复制
				System.out.println("=====添加"+emptemp.getName()+"成功！======\n");
			}
			else if(operType.equals("2"))
			{
				System.out.println("请输入编号：");
				String empNo=br.readLine();
				//查找
				Emp emptemp=em.showInfo(empNo);
				System.out.println("=====查找"+empNo+"("+emptemp.getName()+")"+"成功！======\n");
			}
			else if(operType.equals("3"))
			{
				//显示所有员工信息
				em.showAllInfo();
				System.out.println("=====已列出！======\n");
			}
			else if(operType.equals("4"))
			{
				System.out.println("请输入编号：");
				String empNo=br.readLine();
				System.out.println("请输入工资：");
				float sal=Float.parseFloat(br.readLine());
				//修改
				Emp emptemp=em.updateSal(empNo, sal);
				System.out.println("=====修改"+empNo+"("+emptemp.getName()+")"+"工资成功！======\n");
			}
			else if(operType.equals("5"))
			{
				System.out.println("请输入编号：");
				String empNo=br.readLine();
				//删除
				Emp emptemp=em.delEmp(empNo);
				System.out.println("=====删除"+empNo+"("+emptemp.getName()+")"+"成功！======\n");
			}
			else if(operType.equals("6"))
			{
				//退出系统
				System.exit(0);//0表示正常退出JVM,非0表示异常退出JVM
			}
		}
		
	}

}

//雇员类
class Emp
{
	//学号
	private String empNo;
	private String name;
	private float sal;
	public String getEmpNo() {
		return empNo;
	}
	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getSal() {
		return sal;
	}
	public void setSal(float sal) {
		this.sal = sal;
	}
	
	//构造函数
	public Emp(String empNo,String name,float sal)
	{
		this.empNo=empNo;
		this.name=name;
		this.sal=sal;
	}
}

//雇员管理类
class EmpManage
{
	private ArrayList al=null;
	public EmpManage()
	{
		al=new ArrayList();
	}
	//加入员工
	public Emp addEmp(Emp emp)
	{
		if(al.add(emp))//如果添加成功
			return emp;
		else
			return null;
	}
	//显示指定员工的相关信息
	public Emp showInfo(String empNo)
	{
		//遍历整个ArrayList
		for(int i=0;i<al.size();i++)
		{
			//取出Emp对象
			Emp emp=(Emp)al.get(i);
			//比较编号
			if(emp.getEmpNo().equals(empNo));
			{
				System.out.println("找到该员工，他的信息是：");
				System.out.println("编号="+emp.getEmpNo());
				System.out.println("名字="+emp.getName());
				System.out.println("工资="+emp.getSal());
				
				return emp;
			}
		}
		return null;
	}
	//显示所有员工的信息
	public void showAllInfo()
	{
		//遍历整个ArrayList
		for(int i=0;i<al.size();i++)
		{
			Emp emp=(Emp)al.get(i);
			System.out.print("编号："+emp.getEmpNo()+" "
					+"名字："+emp.getName()+" "
					+"工资："+emp.getSal()+" "+"\n");
		}
	}
	//修改工资
	public Emp updateSal(String empNo,float newSal)
	{
		for(int i=0;i<al.size();i++)
		{
			Emp emp=(Emp)al.get(i);
			if(emp.getEmpNo().equals(empNo))
			{
				//修改薪水
				emp.setSal(newSal);
				return emp;
			}
		}
		return null;
	}
	//删除员工
	public Emp delEmp(String empNo)
	{
		
		for(int i=0;i<al.size();i++)
		{
			Emp emp=(Emp)al.get(i);
			if(emp.getEmpNo().equals(empNo))
			{
				al.remove(i);//按编号删除员工
				al.remove(emp);//按对象删除员工
				return emp;
			}
		}
		return null;
	}
	
}
