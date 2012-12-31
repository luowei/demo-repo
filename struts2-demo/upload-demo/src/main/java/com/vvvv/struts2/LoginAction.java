package com.vvvv.struts2;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.vvvv.bean.User;
import com.vvvv.service.LoginService;
import com.vvvv.service.impl.LoginServiceImpl;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import java.util.Date;

@ParentPackage("struts-default")
@Action(value = "login", results = {
		@Result(name = "success", location = "/login.jsp"),
		@Result(name = "input", location = "/login.jsp") })
//@InterceptorRef("defaultStack")
//@InterceptorRefs({@InterceptorRef(""), @InterceptorRef("")})
//@ExceptionMappings({@ExceptionMapping(),@E.....})
public class LoginAction extends ActionSupport
{
	private String username;

	private String password;

	private int age;

	private Date date;

	private LoginService loginService = new LoginServiceImpl();

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
		// if(!"hello".equals(username))
		// {
		// throw new UsernameException("username invalid");
		// }
		// if(!"world".equals(password))
		// {
		// throw new PasswordException("password invalid");
		// }
		//		
		// return SUCCESS;

		// HttpServletRequest request = ServletActionContext.getRequest();
		// HttpSession session = request.getSession();
		//		
		// session.setAttribute("hello", "helloworld");
		//		
		// ActionContext actionContext = ActionContext.getContext();
		// Map<String, Object> map = actionContext.getSession();
		//		
		// Object object = map.get("hello");
		// System.out.println(object);

		if (this.loginService.isLogin(username, password))
		{
			User user = new User();

			user.setUsername(username);
			user.setPassword(password);

			ActionContext.getContext().getSession().put("userInfo", user);

			return SUCCESS;
		}

		return INPUT;
	}

	public String myExecute() throws Exception
	{
		System.out.println("myExecute invoked!!");

		return SUCCESS;
	}

	public void validateMyExecute()
	{
		System.out.println("validateMyExecute invoked!!");

		this.addActionError("action error");
	}

	@Override
	public void validate()
	{
		// System.out.println("validate invoked!");

		// this.addActionError("action error");
	}

}
