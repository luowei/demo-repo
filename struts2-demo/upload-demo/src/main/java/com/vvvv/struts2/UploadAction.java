package com.vvvv.struts2;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import java.io.*;

public class UploadAction extends ActionSupport
{
	private String username;

	private File file;

	private String fileFileName;

	private String fileContentType;

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public File getFile()
	{
		return file;
	}

	public void setFile(File file)
	{
		this.file = file;
	}

	public String getFileFileName()
	{
		return fileFileName;
	}

	public void setFileFileName(String fileFileName)
	{
		this.fileFileName = fileFileName;
	}

	public String getFileContentType()
	{
		return fileContentType;
	}

	public void setFileContentType(String fileContentType)
	{
		this.fileContentType = fileContentType;
	}

	@Override
	public String execute() throws Exception
	{
        //文件上传的步骤是：先将上传的文件缓存在struts.multipart.saveDir=xxx指定的目录下，
        //然后从xxx目录下，通过输出流将文件上传到服务器指定的目录("/upload")
        String root = ServletActionContext.getRequest().getRealPath("/upload");
		
		InputStream is = new FileInputStream(file);
		
		System.out.println("path: " + file.getAbsolutePath());
		
		System.out.println("file: " + file.getName());
		
		System.out.println("fileFileName: " + fileFileName);
		
		File destFile = new File(root, fileFileName);
		
		OutputStream os = new FileOutputStream(destFile);
		
		byte[] buffer = new byte[400];
		
		int length = 0;
		
		while(-1 != (length = is.read(buffer)))
		{
			os.write(buffer, 0, length);
			
			Thread.sleep(1000);
		}
		
		is.close();
		os.close();
		
		return SUCCESS;
		
	}
}
