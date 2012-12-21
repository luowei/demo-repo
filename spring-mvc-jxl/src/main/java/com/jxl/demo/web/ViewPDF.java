package com.jxl.demo.web;

import com.jxl.demo.Entity.Student;
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
* 生成PDF视图，可用PDF浏览器打开或者保存
* 由ViewController的return new ModelAndView(viewPDF, model)生成
* @version Version 1.0
*/
public class ViewPDF extends AbstractPdfView {   
    public void buildPdfDocument(Map model, Document document,   
            PdfWriter writer, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
  
    	String excelName = "用户信息.pdf";
		// 设置response方式,使执行此controller时候自动出现下载页面,而非直接使用excel打开
		response.setContentType("APPLICATION/OCTET-STREAM");
		response.setHeader("Content-Disposition", "attachment; filename="+ URLEncoder.encode(excelName, "UTF-8"));	
		
        List stuList = (List) model.get("list");          
		//显示中文
		BaseFont bfChinese = BaseFont.createFont("STSongStd-Light", "UniCNS-UTF8-H", BaseFont.NOT_EMBEDDED);
		com.lowagie.text.Font FontChinese = new com.lowagie.text.Font(bfChinese, 12, com.lowagie.text.Font.NORMAL );
  
		String value = null;
        for (int i = 0; i < stuList.size(); i++) {  
        	Student s = (Student)stuList.get(i);
        	value = "姓名: "+ s.getName()+",性别: "+s.getSex() + ",日期: " + s.getDate() + ",总数: " + s.getCount();
        	document.add(new Paragraph(value,FontChinese));   
        }
    }   
}