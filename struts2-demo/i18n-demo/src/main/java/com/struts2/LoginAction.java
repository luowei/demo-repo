package com.struts2;

import com.exception.PasswordException;
import com.exception.UsernameException;
import com.opensymphony.xwork2.ActionSupport;

import java.util.Date;

public class LoginAction extends ActionSupport
{
	private String username;
	private String password;
	private int age;
	private Date date;
	
	public Date getDate()
	{
		return date;
	}

	public void setDate(Date date)
	{
		this.date = date;
	}

	public int getAge()
	{
		return age;
	}

	public void setAge(int age)
	{
		this.age = age;
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
	
	public String execute() throws Exception
	{
		if(!"hello".equals(username))
		{
			throw new UsernameException("username invalid");
		}
		if(!"world".equals(password))
		{
			throw new PasswordException("password invalid");
		}
		return SUCCESS;
	}
	
	public String myExecute() throws Exception
	{
		System.out.println("myExecute invoked!!");
		return SUCCESS;
	}

    //Action自定义方法的输入校验
    public void validateMyExecute()	//此验证方法规定为将myExecute()方法名首字母大写后，再加前缀validate构成
    {
        System.out.println("validateMyExecute invoked!!");
        this.addActionError("action error");
    }


    @Override
	public void validate()
	{
		//System.out.println("validate invoked!");
		//this.addActionError("action error");
	}
}
