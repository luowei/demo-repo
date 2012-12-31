package com.vvvv.struts2;

import com.opensymphony.xwork2.ActionSupport;
import com.vvvv.bean.User;

import java.util.List;

public class UserAction2 extends ActionSupport
{
	private List<User> user;

	public List<User> getUser()
	{
		return user;
	}

	public void setUser(List<User> user)
	{
		this.user = user;
	}
	
	@Override
	public String execute() throws Exception
	{
		for(User u : user)
		{
			System.out.println(u.getUsername() + ", " + u.getPassword());
		}
		
		return SUCCESS;
	}
}
