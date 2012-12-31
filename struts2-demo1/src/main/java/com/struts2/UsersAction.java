package com.struts2;

import com.bean.User;
import com.opensymphony.xwork2.ActionSupport;

import java.util.List;

public class UsersAction extends ActionSupport
{
	private List<User> users;

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	@Override
	public String execute() throws Exception {
		for(User u:users)
		{
			System.out.println(u.getUsername()+", "+u.getPassword());
		}
		return SUCCESS;
	}
}
