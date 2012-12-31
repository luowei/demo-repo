package com.vvvv.xml;

import java.util.Properties;
import java.io.*;

public class PropertiesTest
{
	public static void main(String []args) throws Exception
	{
		Properties pros=new Properties();
		pros.setProperty("port","8888");
		pros.setProperty("ip","localhost");
		Object value=pros.setProperty("port","9999");
		System.out.println(pros.getProperty("port"));
		System.out.println(value);
		
		for(Object obj:pros.keySet())	//遍历
		{
			String key=(String)obj;
			System.out.println(key+" => "+pros.getProperty(key));
		}
		
		System.err.println("++++++++++++++++++++++++++");
		Properties pro1 = new Properties();
		FileInputStream fis = new FileInputStream("./pros/config.properties");
		InputStream is = PropertiesTest.class.getResourceAsStream("/pros/config.properties");
		pro1.load(is);
		for(Object obj:pro1.keySet())	//遍历
		{
			String key=(String)obj;
			System.out.println(key+" => "+pro1.getProperty(key));
		}
	}

}
