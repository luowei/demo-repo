package com.vvvv.struts2;

import com.opensymphony.xwork2.ActionSupport;

public class Action1 extends ActionSupport
{
	private String username;
	
	private String password;
	
	private String usernameAndPassword;
	
	public String getUsernameAndPassword()
	{
		return usernameAndPassword;
	}

	public void setUsernameAndPassword(String usernameAndPassword)
	{
		this.usernameAndPassword = usernameAndPassword;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}
	
	@Override
	public String execute() throws Exception
	{
		this.usernameAndPassword = this.username + this.password;
		
		System.out.println("execute invoked!!");
		
		return SUCCESS;
	}
	
	public String myExecute() throws Exception
	{
		System.out.println("myExecute invoked!!");
		
		return SUCCESS;
	}
}
