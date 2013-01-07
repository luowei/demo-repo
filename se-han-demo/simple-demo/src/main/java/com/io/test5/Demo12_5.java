/*
 * 演示文件字符流的案例
 */

package com.io.test5;
import java.io.*;

public class Demo12_5 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//文件读取出字符流对象(输入流)
		FileReader fr=null;
		//写入到文件(输出流)
		FileWriter fw=null;
		try{
			//创建fr输入对象
			fr=new FileReader("d://test.txt");
			//创建fw输出对象
			fw=new FileWriter("d:/vvvv.txt");
			int n=0;//记录实际读取的字符数
			//读入内存
			char c[]=new char[1024];
			while((n=fr.read(c))!=-1)//循环读出
			{
				String s=new String(c,0,n);
				//将c中有效字符存到s中
				System.out.println(s);
				fw.write(c, 0, n);
			}
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			try {
				fr.close();
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
