package itext;

import com.lowagie.text.*;
import com.lowagie.text.html.simpleparser.HTMLWorker;
import com.lowagie.text.html.simpleparser.StyleSheet;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.ArrayList;

public class htmltopdf {
    public static void main(String[] args) {
        htmltopdf ih = new htmltopdf();
        ih.htmlCodeComeFromFile("D:/main.htm", "D:/iText_3.pdf");
        // ih.htmlCodeComeString("Hello中文", "D: \\test\\iText_2.pdf");

    }

    public void htmlCodeComeFromFile(String filePath, String pdfPath) {
        Document document = new Document();
        try {
            StyleSheet st = new StyleSheet();
            st.loadTagStyle("body", "leading", "16,0");
            PdfWriter.getInstance(document, new FileOutputStream(pdfPath));
            document.open();
            BaseFont bfChinese = BaseFont.createFont("STSongStd-Light", "UniCNS-UTF8-H", BaseFont.NOT_EMBEDDED);
            Font FontChinese = new Font(bfChinese, 12, Font.NORMAL);
            Paragraph t = new Paragraph(filePath, FontChinese);
            ArrayList p = HTMLWorker.parseToList(new FileReader(filePath), st);
            for (int k = 0; k < p.size(); ++k) {
                document.add((Element) p.get(k));
            }
            document.close();
            System.out.println("文档创建成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void htmlCodeComeString(String htmlCode, String pdfPath) {
        Document doc = new Document(PageSize.A4);
        try {
            PdfWriter.getInstance(doc, new FileOutputStream(pdfPath));
            doc.open();
            // 解决中文问题
            BaseFont bfChinese = BaseFont.createFont("CNS-EUC-H",
                    "GBK-EUC-H", BaseFont.NOT_EMBEDDED);
            Font FontChinese = new Font(bfChinese, 12, Font.NORMAL);
            Paragraph t = new Paragraph(htmlCode, FontChinese);
            doc.add(t);
            doc.close();
            System.out.println("文档创建成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}