package com.swing.test2;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class Test4 {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		//异常抛给JVM去处理
		//创建一个Father
		Father father=new Father();
		father.test1();
	}

}

class Father
{
	private Son son=null;
	public Father()
	{
		son=new Son();
	}
	public void test1() throws Exception
	{
		//异常抛给调用者(main函数)去处理
		System.out.println("1");
		/*
		try {
			son.test2();
		} catch (Exception e) {
			System.out.println("\n父亲在处理！");
			e.printStackTrace();
		}
		*/
		son.test2();
	}
}

class Son
{
	public void test2() throws FileNotFoundException
	{
		//异常抛给调用者(Father.test1函数)去处理
		FileReader fr=null;
		fr=new FileReader("d:\\dd.txt");
	}
}
