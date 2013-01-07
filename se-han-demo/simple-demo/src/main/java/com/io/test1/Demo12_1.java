/*
 * 功能 :File类的基本用法
 */

package com.io.test1;
import java.io.*;

public class Demo12_1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		//创建一个文件对象
		File f=new File("d:\\aa.txt");
		//得到文件的路径
		System.out.println("文件路径："+f.getAbsolutePath());
		//得到文件的大小，字节数
		System.out.println("文件大小："+f.length());
		System.out.println("可读:"+f.canRead()+" 可写："+
				f.canWrite()+" 可执行："+f.canExecute());
		
		//创建文件和创建文件夹
		File f1=new File("d:\\ff\\bb.txt");
		if(!f1.exists())
		{
			//可以创建新文件
			try {
				f1.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else 
		{
			System.out.println("文件存在不能创建！");
		}
		
		//创建文件夹
		File f2=new File("d:\\ff");
		if(f2.isDirectory())
		{
			System.out.println("文件夹已存在！");
		}
		else
		{
			//创建
			f2.mkdir();
		}
		
		//文件与文件夹都是文件，文件夹是一种特殊的文件
		//列出一个文件夹下的所有文件
		File f3=new File("d:\\ff");
		if(f3.isDirectory())
		{
			File lists[]=f3.listFiles();
			for(int i=0;i<lists.length;i++)
			{
				System.out.println("文件名："+lists[i].getName());
			}
		}
	}

}
