package com.vvvv.struts2;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import java.io.InputStream;

public class DownloadAction2 extends ActionSupport
{
	private int number;

	private String filename;

	public String getFilename()
	{
		return filename;
	}

	public void setFilename(String filename)
	{
		this.filename = filename;
	}

	public int getNumber()
	{
		return number;
	}

	public void setNumber(int number)
	{
		this.number = number;
	}

	public InputStream getDownloadFile()
	{
		try
		{
			if (1 == number)
			{
                //动态的修改文件的名字
				this.filename = "comment.txt";

				this.filename = new String(this.filename.getBytes("gbk"),
						"8859_1");

				return ServletActionContext.getServletContext()
						.getResourceAsStream("/upload/comment.txt");
			}
			else
			{
				this.filename = "CaptureSprite.exe";

				return ServletActionContext.getServletContext()
						.getResourceAsStream("/upload/CaptureSprite.exe");
			}
		}
		catch (Exception ex)
		{

		}
		
		return null;
	}

	@Override
	public String execute() throws Exception
	{
		return SUCCESS;
	}
}
