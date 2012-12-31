package com.struts2;

import com.opensymphony.xwork2.ActionSupport;

import java.util.Date;

public class RegisterAction extends ActionSupport
{
	private String username;
	
	private String password;
	
	private String repassword;
	
	private int age;
	
	private Date birthday;
	
	private Date graduation;

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

	public String getRepassword()
	{
		return repassword;
	}

	public void setRepassword(String repassword)
	{
		this.repassword = repassword;
	}

	public int getAge()
	{
		return age;
	}

	public void setAge(int age)
	{
		this.age = age;
	}

	public Date getBirthday()
	{
		return birthday;
	}

	public void setBirthday(Date birthday)
	{
		this.birthday = birthday;
	}
	
	public Date getGraduation()
	{
		return graduation;
	}

	public void setGraduation(Date graduation)
	{
		this.graduation = graduation;
	}

	@Override
	public String execute() throws Exception
	{
		System.out.println(this.birthday);
		
		return SUCCESS;
	}
	
//	public String myExecute() throws Exception
//	{
//		System.out.println("myExecute invoked!");
//		
//		return SUCCESS;
//	}
//	
//	public void validateMyExecute()
//	{
//		System.out.println("validateMyExecute invoked!!");
//	}
//	
//	
//	@Override
//	public void validate()
//	{
//		System.out.println("validate invoked!");
//	}
	
	
	
	
	
//	@Override
//	public void validate()
//	{
//		if(null == username || username.length() < 4 || username.length() > 6)
//		{
//			this.addActionError("username invalid");
//			
//			this.addFieldError("username", "\u7528\u6237\u540d\u4e0d\u5408\u6cd5");
//		}
//		
//		if(null == password || password.length() < 4 || password.length() > 6)
//		{
//			this.addActionError("password invalid");
//		}
//		else if(null == repassword || repassword.length() < 4 || repassword.length() > 6)
//		{
//			this.addActionError("repassword invalid");
//		}
//		else if(!password.equals(repassword))
//		{
//			this.addActionError("the passwords not the same");
//		}
//		
//		if(age < 10 || age > 50)
//		{
//			this.addActionError("age invalid");
//		}
//		
//		if(null == birthday)
//		{
//			this.addActionError("birthday invalid");
//		}
//		
//		if(null == graduation)
//		{
//			this.addActionError("graduation invalid");
//		}
//		
//		if(null != birthday && null != graduation)
//		{
//			Calendar c1 = Calendar.getInstance();
//			c1.setTime(birthday);
//			
//			Calendar c2 = Calendar.getInstance();
//			c2.setTime(graduation);
//			
//			if(!c1.before(c2))
//			{
//				this.addActionError("birthday should be before graduation");
//			}
//		}
//		
//		//this.getFieldErrors().clear();
//		//this.getActionErrors().clear();
//		
////		this.clearActionErrors();
////		this.clearFieldErrors();
//		
//		System.out.println("invoked!!!");
//	}
	
}
