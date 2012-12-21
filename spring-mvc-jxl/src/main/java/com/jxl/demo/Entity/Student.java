package com.jxl.demo.Entity;
/**   
 *Title:     
 *Description:     
 *Copyright: Copyright (c) 2011   
 *Company:http://liuzidong.iteye.com/    
 *Makedate:2011-6-3 ����03:49:51   
 * @author liuzidong   
 * @version 1.0   
 * @since 1.0    
 *   
 */
public class Student {
	
	private String name;
	private String sex;
	private String date;
	private Integer count;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Student(String name, String sex, String date, Integer count) {
		super();
		this.name = name;
		this.sex = sex;
		this.date = date;
		this.count = count;
	}
	
	
	

}
