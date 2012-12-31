package com.jsf.validator;

public class UserValidator {
	public String validator(String username,String password){
		if(username==null||username.length()<1)
			return "failure";
		if(password==null||password.length()<1)
			return "failure";
		if(password.equals("luowei")&&username.equals("luowei"))
			return "success";
		else
			return "failure";
		
	}
}
