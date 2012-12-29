package com.luowei.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * 文件工具类
 * User: luowei
 * Date: 12-7-8
 * Time: 下午7:56
 */
public abstract class FileUtil {
    protected static Logger logger = (Logger) LoggerFactory.getLogger(FileUtil.class);

    /**
     * read string from file
     * @return  文件中的字符串
     */
    public static synchronized String readStringFromFile(String filePath){
        InputStream in = null;
        InputStreamReader read = null ;
        BufferedReader reader = null ;
        try {
            File file = new File(filePath);
            StringBuffer strbuf = new StringBuffer();
            read = new InputStreamReader(new FileInputStream(file),"UTF-8");
            reader = new BufferedReader(read);
            String line;
            while ((line = reader.readLine()) != null) {
                strbuf.append(line);
            }
            return strbuf.toString();
        } catch (IOException e) {
            logger.error(e.getMessage(),e);
            return null;
        }  finally {
            close(read);
            close(reader);
        }
    }

    /**
     * write string to file
     * @return  成功或失败
     */
    public static synchronized Boolean writeString2File(String filePath, String string)  {
        OutputStream out = null;
        try {
            System.out.println(filePath);
            File file = new File(filePath);
            out = new FileOutputStream(file);
            out.write(string.getBytes());
            //out.flush();
            return true;
        } catch (IOException e) {
            logger.error(e.getMessage(),e);
            return false;
        }finally {
            //close(out);
            if(null != out){
                try{
                    out.close();
                }catch (IOException e){

                }

            }
        }
    }

    /**
     * mkdir with the specifiede directory path
     * @param dirPath   文件路径
     * @return      成功或失败
     */
    public static synchronized Boolean mkdir(String dirPath){
        Boolean flag = false;
        if(dirPath!=null && dirPath != ""){
            File file = new File(dirPath);
            if(!file.exists() || file.isFile()){
                file.mkdirs();
                flag = true;
            }
        }
        return flag;
    }

    /**
     * close resource
     * @param closeable   是否可克隆
     */
    public static Boolean close(Closeable closeable){
        try {
            if(closeable!=null){
                closeable.close();
            }
            return true;
        } catch (IOException e) {
            logger.error(e.getMessage(),e);
            return false;
        }
    }

}
