/*
 * 功能：各种集合的使用
 */
package com.swing.test1;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;
import java.util.Vector;

public class Demo7_3 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//LinkedList的用法
		LinkedList ll=new LinkedList();
		Emp emp1=new Emp("sa01","宋江",1.2f);
		Emp emp2=new Emp("sa02","卢俊义",1.8f);
		Emp emp3=new Emp("sa03","吴用",1.5f);
		//表示把emp1加在链表的最前面
		ll.addFirst(emp1);//向链表的当前结点的前面添加一个emp1结点
		ll.addFirst(emp2);//前加
		ll.addLast(emp3);//后加
		for(int i=0;i<ll.size();i++)
		{
			System.out.println(((Emp)ll.get(i)).getName());
		}
		//------------------------------------------------------
		//Vector的用法
		//Vector与ArrayList的区别
		//1.Vector有同步机制，ArrayList没有同步机制 
		//2.ArrayList缺省情况下自动增长原来的一倍，Vector是原来的50%增长
		Vector vv=new Vector();
		Emp emp4=new Emp("1","林冲",1.6f);
		vv.add(emp4);
		for(int i=0;i<vv.size();i++)
		{
			Emp emp=(Emp)vv.get(i);
			System.out.println(emp.getName());
		}
		//------------------------------------------------------
		//栈的用法
		Stack stack=new Stack();
		Emp emp5=new Emp("8","李逵",1.9f);
		Emp emp6=new Emp("9","武松",1.7f);
		Emp emp7=new Emp("5","公孙胜",1.7f);
		stack.add(emp5);
		stack.add(emp6);
		stack.add(emp7);
		for(int i=0;i<stack.size();i++)
		{
			Emp emp=(Emp)stack.get(i);
			System.out.println(emp.getName());
		}
		//------------------------------------------------------
		//HashMap的用法
		//创建一个HashMap对象
		HashMap hm=new HashMap();
		Emp emp8=new Emp("s001","秦平",2.2f);
		Emp emp9=new Emp("s002","花荣",2.3f);
		Emp emp10=new Emp("s002","燕青",2.4f);
		//将emp放入到
		hm.put("s001", emp8);
		hm.put("s002", emp9);
		hm.put("s002", emp10);//出现键值相同时，会将emp10替换前面的
		System.out.println(emp8.getName());
		System.out.println(emp9.getName());
		
		//如果要查找编号是s002
		if(hm.containsKey("s002"))
		{
			System.out.println("-----------\n有该员工");
			//如何取出,键<-->值
			Emp emp=(Emp)hm.get("s002");
			System.out.println("名字："+emp.getName());
		}
		else
		{
			System.out.println("-----------\n没有该员工");
		}
		
		//遍历HashMap中所有的key和value
		Iterator it=hm.keySet().iterator();//迭代器
		//hasNext返回一个boolean
		System.out.println("-------开始遍历HashMap！------");
		while(it.hasNext())//hasNext判断是否还有下一个
		{
			//取出Key
			String key=it.next().toString();
			//通过Key取出value
			Emp emp=(Emp)hm.get(key);
			System.out.println("名字："+emp.getName());
		}
		//HashMap在多线程并发操作时，因为没有同步机制作，
		//可能会发生读脏数据，所以引入了安全的Hashtable
		//------------------------------------------------------
		//Hashtable的用法
		Hashtable ht=new Hashtable();
		/*
		ht.put(null, null);
		System.out.println("Hashtable null值测试"+ht.get(null));
		//Hashtable不可以存放空值
		*/
		HashMap hm2=new HashMap();
		hm2.put(null, null);//HashMap可以存放空值
		System.out.println("HashMap null值测试:"+hm2.get(null));
		//Hashtable不可以存放空值
		
		/*
		 * 集合总结：
		 * 1.如果要求线程安全，使用Vector,Hashtable
		 * 2.如果要求线程安全，使用ArrayList,LinkedList,HashMap
		 * 3.如果要求键值对，则使用HashMap,Hashtable
		 * 4.如果数据量很大，又要线程安全考虑Vector
		 */
		
	}

}


