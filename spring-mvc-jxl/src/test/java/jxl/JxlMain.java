package jxl;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Title: Description: Copyright: Copyright (c) 2011
 * Company:http://liuzidong.iteye.com/ Makedate:2011-6-3 下午05:16:35
 *
 * @author liuzidong
 * @version 1.0
 * @since 1.0
 *
 */
public class JxlMain {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub

        List datas = new ArrayList();
        String[] data = { "1", "chenlb" };
        datas.add(data);
        try {
            OutputStream out = new FileOutputStream(new File("doc/chenlb.blogjava.net.xls"));
            JxlExcelWriter jxlExcelWriter = new JxlExcelWriter();
            jxlExcelWriter.writeExcel(out, datas, new String[] { "Id", "name" });
            out.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}