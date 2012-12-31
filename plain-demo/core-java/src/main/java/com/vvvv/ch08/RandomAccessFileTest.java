//byte的所有数字 -128 ~ 127 ,向文件中写入byte类型数据
package com.vvvv.ch08;

import java.io.*;

public class RandomAccessFileTest {
    public static void main(String[] args) throws Exception {
        File file = new File("ch08/raf.db");
        RandomAccessFile raf = new RandomAccessFile(file, "rw");
        //for(int i=-128;i<128;i++)
        //for(byte i=-128;i<128;i++)	//这样会死循环
        for (byte i = -128; i < 127; i++) {
            raf.writeByte(i);
            if (i == 126) {
                raf.writeByte(127);
            }
            System.out.println(i);
        }
        raf.seek(255);
        int result = raf.readByte();
        System.out.println(result);
        System.out.println("File-Pointer Loc:" + raf.getFilePointer());
        raf.close();
    }

}