package com.jxl.demo.Entity;

public class User{	
	private String id = null;
	private String name = null;
	private String password = null;	
	private String address = null;
	private String sex = null;
	private int age = 0;	
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public User(String id, String name, String password, String address,
			String sex, int age) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.address = address;
		this.sex = sex;
		this.age = age;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;		
	}

	public String getId() {
		return id;
	}
	public User setId(String id) {
		this.id = id;
		return this;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	
	public String toString() {		
		return "name: " + name + ",password: " + password+",sex: " + sex + ",age: " + age+",address: " + address + ",id: " + id;
	}
	public User() {
		
	}	

}
