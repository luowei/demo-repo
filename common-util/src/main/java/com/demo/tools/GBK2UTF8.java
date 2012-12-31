package com.demo.tools;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 12-12-31
 * Time: 上午10:13
 * To change this template use File | Settings | File Templates.
 */
public class GBK2UTF8 {

    public static void main(String[] args) throws IOException {

        //GBK编码格式源码路径
        String srcDirPath = "D:\\java_prj\\demo-repo\\borrow-book\\src\\main";
        //转为UTF-8编码格式源码路径
        String utf8DirPath = "D:\\java_prj\\demo-repo\\borrow-book\\src\\main2";

        //获取所有java文件
        Collection<File> javaGbkFileCol =  FileUtils.listFiles(new File(srcDirPath), new String[]{"java"}, true);

        for (File javaGbkFile : javaGbkFileCol) {
            //UTF8格式文件路径
            String utf8FilePath = utf8DirPath+javaGbkFile.getAbsolutePath().substring(srcDirPath.length());
            //使用GBK读取数据，然后用UTF-8写入数据
            FileUtils.writeLines(new File(utf8FilePath), "UTF-8", FileUtils.readLines(javaGbkFile, "GBK"));
        }

    }
}
