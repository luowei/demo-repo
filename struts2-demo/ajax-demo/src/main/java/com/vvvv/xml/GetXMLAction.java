package com.vvvv.xml;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class GetXMLAction extends ActionSupport
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
		//zhang san
		People people1 = new People();
		
		people1.setId(1);
		people1.setName("zhangsan");
		people1.setAge(30);
		people1.setAddress("beijing");
		
		People people2 = new People();
		
		people2.setId(2);
		people2.setName("lisi");
		people2.setAge(50);
		people2.setAddress("tianjin");
		
		Document document = DocumentHelper.createDocument();
		
		Element rootElement = document.addElement("persons");
		
		rootElement.addComment("This is comment!!");
		
		Element e = rootElement.addElement("person");
		
		Element idElement = e.addElement("id");
		Element nameElement = e.addElement("name");
		Element ageElement = e.addElement("age");
		Element addressElement = e.addElement("address");
		
		if("zhangsan".equals(name))
		{
			idElement.setText(people1.getId() + "");
			nameElement.setText(people1.getName());
			ageElement.setText(people1.getAge() + "");
			addressElement.setText(people1.getAddress());
		}
		else
		{
			idElement.setText(people2.getId() + "");
			nameElement.setText(people2.getName());
			ageElement.setText(people2.getAge() + "");
			addressElement.setText(people2.getAddress());
		}
		
		HttpServletResponse response = ServletActionContext.getResponse();
		
		response.setContentType("text/xml; charset=utf-8");
		response.setHeader("cache-control", "no-cache"); 
		
		PrintWriter out = response.getWriter();
		
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("utf-8");
		
		XMLWriter writer = new XMLWriter(out, format);
		
		writer.write(document);
		
		out.flush();
		out.close();
		
		return null;
	}
}
