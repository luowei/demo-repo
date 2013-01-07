/*
 * 演示缓冲字符流案例
 */

package com.io.test6;
import java.io.*;

public class Demo12_6 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//
		BufferedReader br=null;//Buf输入流
		BufferedWriter bw=null;//Buf输出流
		try {
			//先创建FileReader对象
			FileReader fr=new FileReader("d:/test.txt");
			br=new BufferedReader(fr);
			
			//创建FileWriter对象
			FileWriter fw=new FileWriter("d:/赵云.txt");
			bw=new BufferedWriter(fw);
			
			//循环读取文件
			String s="";
			while((s=br.readLine())!=null) //从buf流中读取
			{
				System.out.println(s);
				//输出到磁盘
				bw.write(s+"\r\n");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				br.close();
				bw.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}

}
