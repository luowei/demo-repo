/*
 * 演示FileOutputStream的使用
 */

package com.io.test3;
import java.io.*;

public class Demo12_3 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//定义文件对象
		File f=new File("d:\\ss.txt");
		//字节输出流
		FileOutputStream fos=null;
		try {
			fos=new FileOutputStream(f);
			String s="άΨΪΪ OK,HelloWorld";
			String s1="中国好！";
			//定义字节数组
			//byte []bytes=new byte[1024];
			//如何把String->bytes数组
			try {
				fos.write(s.getBytes("UTF-8"));
				fos.write(s1.getBytes("UTF-8"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			try {
				fos.close(); //关闭文件流
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
