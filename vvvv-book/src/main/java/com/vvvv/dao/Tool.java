package com.vvvv.dao;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;



public class Tool {
	/**
	 * ����ת�ַ�
	 * @param df.format(date) 
	 * @return strDate
	 */
	public static String conventDateToString(Date date){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = df.format(date);
		return strDate;
	}
	 /**
	  * �ַ�ת����
	  * @param df.parse(strDate)
	  * @return date
	  * @throws java.text.ParseException
	  */
	public static Date conventStringToDate(String strDate) throws ParseException{
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date date = df.parse(strDate);
		return date;
	}
}
