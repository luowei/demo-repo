package com.vvvv.json;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.json.annotations.JSON;

public class GetJsonAction2 extends ActionSupport
{
	private String name;
	
	private int id;
	
	private int age;
	
	private String address;

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	} 

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	@JSON(name="myAge")
	public int getAge()
	{
		return age;
	}

	public void setAge(int age)
	{
		this.age = age;
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}
	
	@Override
	public String execute() throws Exception
	{
		this.id = 1;
		this.age = 30;
		this.address = "beijing";
		
		return SUCCESS;
	}
}
