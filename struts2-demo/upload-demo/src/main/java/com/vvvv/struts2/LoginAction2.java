package com.vvvv.struts2;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.vvvv.bean.Person;

public class LoginAction2 extends ActionSupport implements ModelDriven<Person>, Preparable
{
	private Person person = new Person();
	
	public Person getModel()
	{
		System.out.println("getModel invoked!");
		
		return person;
	}
	
	public void prepare() throws Exception
	{
		System.out.println("prepare invoked!!");
	}
	
	public String execute() throws Exception
	{
		System.out.println("execute invoked!");
		
		//System.out.println(person.getUsername());
		
		return SUCCESS;
	}
}
