/*
 * 功能：演示java集合的用法
 */

package com.swing.test1;
import java.util.*;
public class Demo7_1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//定数组
		//Clerk []clerks=new Clerk[???];
		//定义ArrayList对象
		ArrayList al=new ArrayList();
		//显示大小
		System.out.println("al大小："+al.size());
		//向al中加入数据(类型是object)
		//创建一个职员
		Clerk clerk1=new Clerk("宋江",50,1000);
		Clerk clerk2=new Clerk("吴用",45,1200);
		Clerk clerk3=new Clerk("林冲",35,1300);
		//将clerk1加入到al中
		al.add(clerk1);
		al.add(clerk2);
		al.add(clerk3);
		//显示大小
		System.out.println("al大小："+al.size());
		//如何访问al中对象（数据）
		//访问第一个对象
		Clerk temp=(Clerk)al.get(0);//因为get返回的是一个object
		System.out.println("第一个名字是："+temp.getName());
		
		//遍历al所有的对象
		System.out.println("删除前");
		for(int i=0;i<al.size();i++)
		{
			Clerk temp1=(Clerk)al.get(i);
			System.out.println("名字是："+temp1.getName());
		}
		//从al中删除一个对象
		al.remove(1);//删除吴用
		System.out.println("=====删除吴用======");
		//删除后
		System.out.println("删除后");
		for(int i=0;i<al.size();i++)
		{
			Clerk temp1=(Clerk)al.get(i);
			System.out.println("名字是："+temp1.getName());
		}
	}

}

//定义一个员工类
class Clerk
{
	private String name;
	private int age;
	private float sal;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public float getSal() {
		return sal;
	}

	public void setSal(float sal) {
		this.sal = sal;
	}
	
	public Clerk(String name,int age,float sal)
	{
		this.name=name;
		this.age=age;
		this.sal=sal;
	}
}


