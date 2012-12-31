package com.vvvv.xml;

import javax.xml.parsers.*;
import java.io.*;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.SAXException;
import org.xml.sax.Attributes;

public class SAXTest
{
	public static void main(String[] args) throws Exception
	{
		SAXParserFactory sf=SAXParserFactory.newInstance();
		SAXParser parser=sf.newSAXParser();
		StudentHandler handler=new StudentHandler();
		FileInputStream fis=new FileInputStream(new File("./xml/Student.xml"));
		parser.parse(fis,handler);
	}
}

class StudentHandler extends DefaultHandler
{
	@Override
	public void characters(char[] ch, int start, int length) 
	{
		String eleString=new String(ch,start,length);
		System.out.println(eleString.trim());
	}
	
	@Override
	public void startDocument() throws SAXException
	{
		System.out.println("<?xml vrsion='1.0' encoding='utf-8'/>");
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes)  throws SAXException
	{
		String eleName=qName;
		System.out.println("<"+eleName+">");
	}
	
	@Override
	public void endElement(String uri, String localName, String qName)  throws SAXException
	{
		String eleName=qName;
		System.out.println("</"+eleName+">");
	}
	
	@Override
	public void endDocument()  throws SAXException
	{
		System.out.println("xml processing finish");
	}
}
