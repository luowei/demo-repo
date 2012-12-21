package com.jxl.demo.web;

import com.jxl.demo.Entity.User;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.Alignment;
import jxl.format.VerticalAlignment;
import jxl.write.*;
import jxl.write.biff.RowsExceededException;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.web.servlet.view.document.AbstractJExcelView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;


public class JXLExcelView extends AbstractJExcelView {

	private String[] columnNames = new String[] { "编号", "姓名", "年龄", "性别", "密码",
			"地址" };

	private String[] dbColumnNames = new String[] { "id", "name", "age", "sex",
			"password", "address" };

	private Integer[] columnWidths = new Integer[] { 20, 20, 20, 20, 20, 20 };

	@Override
	public void buildExcelDocument(Map<String, Object> map,
			WritableWorkbook work, HttpServletRequest req,
			HttpServletResponse response) {
		String [] titles = (String[])map.get("titles");
		if(null != titles && titles.length > 0){
			 columnNames = titles;
		}
		String [] columns = (String[])map.get("columns");
		if(null != columns &&  columns.length > 0){
			dbColumnNames = columns;
		}
		
		OutputStream os = null;
		try {

			String excelName = "用户信息.xls";
			// 设置response方式,使执行此controller时候自动出现下载页面,而非直接使用excel打开
			response.setContentType("APPLICATION/OCTET-STREAM");
			response.setHeader("Content-Disposition", "attachment; filename="
					+ URLEncoder.encode(excelName, "UTF-8"));
			os = response.getOutputStream();
			// sheet名称
			String sheetName = "用户信息";

			// 全局设置
			WorkbookSettings setting = new WorkbookSettings();
			java.util.Locale locale = new java.util.Locale("zh", "CN");
			setting.setLocale(locale);
			setting.setEncoding("ISO-8859-1");
			// 创建工作薄
			work = Workbook.createWorkbook(os); // 建立excel文件
			// 创建第一个工作表
			jxl.write.WritableSheet ws = work.createSheet(sheetName, 1); // sheet名称
			// 添加标题
			addColumNameToWsheet(ws);

			List<User> list = (List<User>) map.get("list");
			writeContext(ws, list);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			// 写入文件
			try {
				work.write();
				work.close();
				os.flush();
				os.close();
			} catch (WriteException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

	private <T> void writeContext(WritableSheet wsheet, List<T> list) {
		int rows = list.size();
		jxl.write.Label wlabel = null;
		jxl.write.WritableCellFormat wcf = getFormat();
		int cols = dbColumnNames.length;
		String columnName = null;
		Object value = null;
		try {
			for (int i = 0; i < rows; i++) {
				T t = (T) list.get(i);				
				for (int j = 0; j < cols; j++) {
					columnName = dbColumnNames[j].toLowerCase();
					value = PropertyUtils.getProperty(t, columnName);
					wlabel = new jxl.write.Label(j, (i + 1), value + "", wcf);
					wlabel = new jxl.write.Label(j, (i + 1), value + "");
					wsheet.addCell(wlabel);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 添加标题样式
	private void addColumNameToWsheet(jxl.write.WritableSheet wsheet)
			throws RowsExceededException, WriteException {

		// 设置excel标题
		jxl.write.WritableFont wfont = getFont();
		if (null == wfont) {
			wfont = new WritableFont(WritableFont.ARIAL,
					WritableFont.DEFAULT_POINT_SIZE, WritableFont.BOLD);

		}
		jxl.write.WritableCellFormat wcfFC = getFormat();
		if (null == wcfFC) {
			wcfFC = new jxl.write.WritableCellFormat(wfont);
			try {
				wcfFC.setWrap(true);// 自动换行
				wcfFC.setAlignment(Alignment.CENTRE);
				wcfFC.setVerticalAlignment(VerticalAlignment.CENTRE);// 设置对齐方式
			} catch (WriteException e) {
				e.printStackTrace();
			}
		}

		jxl.write.Label wlabel1 = null;
		String[] columNames = columnNames;
		if (null == columNames)
			return;
		int colSize = columNames.length;

		Integer[] colsWidth = columnWidths;
		if (null == colsWidth) {
			colsWidth = new Integer[colSize];
			for (int i = 0; i < colSize; i++) {
				colsWidth[i] = 20;
			}
		}

		int temp = 0;
		String colName = null;
		for (int i = 0; i < colSize; i++) {
			colName = columNames[i];
			if (null == colName || "".equals(colName))
				colName = "";
			wlabel1 = new jxl.write.Label(i, 0, colName, wcfFC);
			wsheet.addCell(wlabel1);
			temp = colsWidth[i].intValue();
			// 默认设置列宽
			temp = temp == 0 ? 20 : temp;
			wsheet.setColumnView(i, temp);
		}

	}

	// 设置格式
	private WritableCellFormat getFormat() {

		jxl.write.WritableFont wfont = getFont();
		jxl.write.WritableCellFormat wcfFC = new jxl.write.WritableCellFormat(
				wfont);
		try {
			wcfFC.setWrap(true);
			wcfFC.setAlignment(Alignment.CENTRE);
			wcfFC.setVerticalAlignment(VerticalAlignment.CENTRE);
		} catch (WriteException e) {
			e.printStackTrace();
		}
		return wcfFC;
	}

	// 设置字体
	private WritableFont getFont() {
		return new WritableFont(WritableFont.ARIAL,
				WritableFont.DEFAULT_POINT_SIZE, WritableFont.BOLD);
	}

}
