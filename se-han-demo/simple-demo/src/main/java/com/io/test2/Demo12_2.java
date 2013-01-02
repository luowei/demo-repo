/*
 * 演示FileInputStream类的使用
 */

package com.io.test2;
import java.io.*;

public class Demo12_2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//得到一个文件对象
		File f=new File("d:/test.txt");
		//因为File没有读写的能力，所以要使用InputStream流。
		FileInputStream fis=null;
		try {
			//文件输入流
			fis=new FileInputStream(f);
			byte []bytes=new byte[1024];//定义一个字节数组，相当于缓存
			int n=0; //用于存放实际读取到的字节数
			//循环读取
			while((n=fis.read(bytes))!=-1)
				//如果文件流中的字节数大于数组长度(1024)，循环一次，就从输入文件流中
				//读取等于数组长度(1024)的字节存到字节数组bytes中，第二次再读取剩余的，
				//如果文件流中的字节数大于数组长度(1024),则一次循环就能文件流中的字节全都存入bytes数组中
			{
				//把字节转成String
				String s=new String(bytes,0,n);
				System.out.println(s);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//关闭文件流必须放在finally块里
			try {
				fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
