package com.vvvv.struts2;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import java.io.InputStream;

public class DownloadAction extends ActionSupport
{
	public InputStream getDownloadFile()
	{
		return ServletActionContext.getServletContext().getResourceAsStream(
				"/upload/comments.txt");
	}

	@Override
	public String execute() throws Exception
	{
		return SUCCESS;
	}
}
