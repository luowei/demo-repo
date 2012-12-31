package com.struts2;

import com.bean.User;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport
{
	private User user;

	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}
	
	@Override
	public String execute() throws Exception
	{
		System.out.println("username: " + user.getUsername());
		System.out.println("password: " + user.getPassword());
		
		
		return SUCCESS;
	}
}
