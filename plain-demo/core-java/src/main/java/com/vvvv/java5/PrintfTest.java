package com.vvvv.java5;

import java.util.*;

public class PrintfTest {
    public static void main(String[] args) {
        int i = 100;
        String string = "test";
        System.out.printf("%d,%s,%S \n", i, string, string);
        System.out.printf(i + "," + string + "," + string.toUpperCase() + "\n");
        System.out.println(i + "," + string + "," + string.toUpperCase());
        System.out.printf("%1$d,%2$s,%2$S,%1$d \n", i, string);
        //1$代表第一个参数，2$代表第二个参数
        //%d %s 格式符
    }
}