package com.vvvv.servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class UploadServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        //DiskFileItemFactory创建在内存中保存内容的FileItem实例
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //得到真实绝对路径
        String path = req.getRealPath("/upload");

        //把工厂的仓库设置为真实路径
        factory.setRepository(new File(path));
        //设置阀值
        factory.setSizeThreshold(1024 * 1024);
        //创建ServletFileUpload实例
        ServletFileUpload upload = new ServletFileUpload(factory);
        try {
            //解析HttpServletRequest到List到中
            List<FileItem> list = (List<FileItem>) upload.parseRequest(req);
            for (FileItem item : list) {
                //获得表单字段的名字
                String name = item.getFieldName();
                //判断是否是表单字段
                //如果是普通文本域返回true; 如果不是普通文本域返回false.
                if (item.isFormField()) {
                    //获得表单字段的值
                    String value = item.getString();
                    System.out.println(name + "=" + value);
                    req.setAttribute(name, value);
                } else {
                    String value = item.getName();
                    int start = value.lastIndexOf("\\");
                    String fileName = value.substring(start + 1);
                    req.setAttribute(name, fileName);
                    //上传文件
                    item.write(new File(path, fileName));

//					OutputStream os = new FileOutputStream(new File(path, fileName));
//					InputStream is = item.getInputStream();
//					byte[] buffer = new byte[400];
//					int length = 0;
//					while((length = is.read(buffer)) != -1)
//					{
//						os.write(buffer, 0, length);
//					}
//					//获取表单域提交来的文件的总的字节数
//					//item.getSize();

//					is.close();
//					os.close();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        req.getRequestDispatcher("fileUploadResult.jsp").forward(req, resp);
    }
}

