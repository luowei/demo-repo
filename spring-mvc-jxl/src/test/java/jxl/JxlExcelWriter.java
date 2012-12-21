package jxl;

import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * Jxl 的 Excel写数据器.
 *
 * @author chenlb 2007-10-29 上午10:39:31
 */
public class JxlExcelWriter {

    /**
     * @param datas
     *            封装着Object[]的列表, 一般是String内容.
     * @param title
     *            每个sheet里的标题.
     */
    public void writeExcel(OutputStream out, List datas, String[] title) {
        if (datas == null) {
            throw new IllegalArgumentException("写excel流需要List参数!");
        }
        try {
            WritableWorkbook workbook = Workbook.createWorkbook(out);
            WritableSheet ws = workbook.createSheet("sheet 1", 0);
            int rowNum = 0; // 要写的行
            if (title != null) {
                putRow(ws, 0, title);// 压入标题
                rowNum = 1;
            }
            for (int i = 0; i < datas.size(); i++, rowNum++) {// 写sheet
                Object[] cells = (Object[]) datas.get(i);
                putRow(ws, rowNum, cells); // 压一行到sheet
            }

            workbook.write();
            workbook.close(); // 一定要关闭, 否则没有保存Excel
        } catch (RowsExceededException e) {
            System.out.println("jxl write RowsExceededException: "
                    + e.getMessage());
        } catch (WriteException e) {
            System.out.println("jxl write WriteException: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("jxl write file i/o exception!, cause by: "
                    + e.getMessage());
        }
    }

    private void putRow(WritableSheet ws, int rowNum, Object[] cells)
            throws RowsExceededException, WriteException {
        for (int j = 0; j < cells.length; j++) {// 写一行
            Label cell = new Label(j, rowNum, "" + cells[j]);
            ws.addCell(cell);
        }
    }
}