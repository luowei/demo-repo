package com.vvvv.struts2;

import com.opensymphony.xwork2.ActionSupport;

public class TokenAction extends ActionSupport
{
	private String username;
	
	private String password;

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
		return SUCCESS;
	}
}	
