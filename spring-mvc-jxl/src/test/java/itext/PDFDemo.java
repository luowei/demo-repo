package itext;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 项目名称： admin 后台管理系统 功能模块名称：IRM 文件名称为：PDFDemo.java 文件创建人：hongliang.dinghl 修改记录：
 * 修改人 修改日期 备注
 *
 * @author 丁宏亮
 * @version
 * @time 2008-11-11 下午02:27:34
 * @copyright www.alisoft.com
 */
public class PDFDemo {
    // 指定文件全路径
    @SuppressWarnings("finally")
    public boolean writerPDF(String fileName) {
        // 创建一个文档对象
        Document doc = new Document();
        try {
            // 定义输出文件的位置
            PdfWriter.getInstance(doc, new FileOutputStream(fileName));
            // 开启文档
            doc.open();
            // 设定字体 为的是支持中文
            BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniCNS-UTF8-H", BaseFont.NOT_EMBEDDED);
            com.lowagie.text.Font FontChinese = new com.lowagie.text.Font(
                    bfChinese, 12, com.lowagie.text.Font.NORMAL);
            // 向文档中加入文字
            doc.add(new Paragraph("Hello你好！世界world", FontChinese));
            // 关闭文档并释放资源
            doc.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (DocumentException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            return false;
        }
    }

    // 指定文件路径和文件名称
    public boolean writerPDF(String filePath, String fileName) {
        return false;
    }

    // 指定文件输入流
    public boolean writerPDF(InputStream inputStream) {
        return false;
    }

    public static void main(String args[]) {
        PDFDemo pdf = new PDFDemo();
        pdf.writerPDF("D:/itext.pdf");
    }
}