package com.demo.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.zip.CRC32;

/**
 * @className:SHA1.java
 * @classDescription:
 */
public class SHA1 {
	public  String byteArrayToHexString(byte[] b) throws Exception
	{   String result = "";  
	 	 for (int i=0; i < b.length; i++) 
	 	 	{    
	 	 		 result +=Integer.toString( ( b[i] & 0xff ) + 0x100, 16).substring( 1 );  
	 	 		 	
	 	 	 }  
	 	 return result;
	} 


	/**
	 * 采用SHA1校验
	 * @createTime: 2012-06-11
	 * @param convertme 需要转换的数组
	 * @return 循环冗余校验码
	 */
	public  String toSHA1(byte[] convertme) throws Exception
	{    
		MessageDigest md = null;    
		 try {       
		 	  md = MessageDigest.getInstance("SHA-1");     
		 }
		 catch(NoSuchAlgorithmException e){         
			 e.printStackTrace();     
		 }   
		 return byteArrayToHexString(md.digest(convertme)); 
	}
	/**
	 * 计算循环冗余校验码
	 * @createTime: 2012-06-11
	 * @param b 数组字节
	 * @return 循环冗余校验码
	 */
	public  long crc(byte[] b)
	{
		CRC32 c32=new CRC32();
		c32.update(b);
		return c32.getValue();
	}
}
