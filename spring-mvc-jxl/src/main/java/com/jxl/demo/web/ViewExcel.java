package com.jxl.demo.web;

import com.jxl.demo.Entity.Student;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
* 生成excel视图，可用excel工具打开或者保存
* 由ViewController的return new ModelAndView(viewExcel, model)生成
*/
public class ViewExcel extends AbstractExcelView {   
   
    public void buildExcelDocument(Map model, HSSFWorkbook workbook,   
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {  
    	
    	String excelName = "用户信息.xls";
		// 设置response方式,使执行此controller时候自动出现下载页面,而非直接使用excel打开
		response.setContentType("APPLICATION/OCTET-STREAM");
		response.setHeader("Content-Disposition", "attachment; filename="+ URLEncoder.encode(excelName, "UTF-8"));	
		
		List stuList = (List) model.get("list");   
		// 产生Excel表头
		HSSFSheet sheet = workbook.createSheet("studentList");
		HSSFRow header = sheet.createRow(0); // 第0行
		// 产生标题列
		header.createCell((short) 0).setCellValue("name");
		header.createCell((short) 1).setCellValue("sex");
		header.createCell((short) 2).setCellValue("date");
		header.createCell((short) 3).setCellValue("count");
		HSSFCellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("mm/dd/yyyy"));

		// 填充数据
		int rowNum = 1;
		for (Iterator iter = stuList.iterator(); iter.hasNext();) {
			Student element = (Student) iter.next();
			HSSFRow row = sheet.createRow(rowNum++);
			row.createCell((short) 0)
					.setCellValue(element.getName().toString());
			row.createCell((short) 1).setCellValue(element.getSex().toString());
			row.createCell((short) 2)
					.setCellValue(element.getDate().toString());
			row.getCell((short) 2).setCellStyle(cellStyle);
			row.createCell((short) 3).setCellValue(element.getCount());
		}

		// 列总和计算
		HSSFRow row = sheet.createRow(rowNum);
		row.createCell((short) 0).setCellValue("TOTAL:");
		String formual = "SUM(D2:D" + rowNum + ")"; // D2到D[rowNum]单元格起(count数据)
		row.createCell((short) 3).setCellFormula(formual);
    }   
}