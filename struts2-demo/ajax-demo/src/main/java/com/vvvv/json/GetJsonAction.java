package com.vvvv.json;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import com.vvvv.xml.People;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class GetJsonAction extends ActionSupport
{
	private String name;
	
	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	@Override
	public String execute() throws Exception
	{
		People people = new People();
		
		people.setId(1);
		people.setName(name);
		people.setAge(30);
		people.setAddress("beijing");
		
		Gson gson = new Gson();
		
		String result = gson.toJson(people);
		
		HttpServletResponse response = ServletActionContext.getResponse();
		
		response.setContentType("application/json; charset=utf-8");
		response.setHeader("cache-control", "no-cache"); 
		
		PrintWriter out = response.getWriter();
		
		out.print(result);
		
		out.flush();
		out.close();
		
		return null;
	}
}
