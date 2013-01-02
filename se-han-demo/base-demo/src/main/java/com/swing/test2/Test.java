/*
 * 功能：泛型的必要性
 */

package com.swing.test2;

import java.util.ArrayList;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//泛型
		//System.out.println("a="+(1>>>2));
		ArrayList<Dog> al=new ArrayList<Dog>();//将ArrayList转成对Dog的泛型
		//创建一只狗
		Dog dog1=new Dog();
		//放入到集合
		al.add(dog1);
		//取出
		//Dog temp=(Dog)al.get(0);
		//Cat temp2=(Cat)al.get(0);//类型转换错误
		Dog temp=al.get(0);//因为前定义了ArrayList对Dog泛型，所以不报错
	}

}

class Dog
{
	private String name;
	private int age;
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
	
}

class Cat
{
	private String color;
	private int age;
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
}
