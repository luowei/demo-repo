/*
 * 图片拷贝
 */

package com.io.test4;
import java.io.*;

public class Demo12_4 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//思路 先把图片读入到内存-->写入到某个文件
		//因为是二进制文件，因此只能用字节流完成
		
		FileInputStream fis=null;//定义输入流
		FileOutputStream fos=null;//定义输出流
		try {
			fis=new FileInputStream("d:/ff/a.jpg");//输入流
			fos=new FileOutputStream("d:/a.jpg");//输出流
			byte buf[]=new byte[512];
			int n=0;
			//循环读取
			while((n=fis.read(buf))!=-1)
			{
				//输出到指定文件
				fos.write(buf);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				fis.close();//关闭输入流
				fos.close();//关闭输出流
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
