package com.vvvv.struts2;

import com.opensymphony.xwork2.ActionSupport;

public class Action2 extends ActionSupport
{
	private String username;

	private String password;

	private String usernameAndPassword;

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

	public String getUsernameAndPassword()
	{
		return usernameAndPassword;
	}

	public void setUsernameAndPassword(String usernameAndPassword)
	{
		this.usernameAndPassword = usernameAndPassword;
	}

	@Override
	public String execute() throws Exception
	{
		return SUCCESS;
	}
}
