package com.jsf.backing;

import com.jsf.validator.UserValidator;

public class LoginBean {
	private String username;
	private String password;
	private UserValidator uv;
	
	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public UserValidator getUv() {
		return uv;
	}


	public void setUv(UserValidator uv) {
		this.uv = uv;
	}


	public String login() {
		return uv.validator(username, password);
	}
}
