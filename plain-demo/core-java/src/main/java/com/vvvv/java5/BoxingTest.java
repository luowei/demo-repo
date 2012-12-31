package com.vvvv.java5;


public class BoxingTest {
    public static void main(String[] args) {
        int i = 100;
        Integer iInteger = i;    //装箱
        System.out.println(iInteger);
        Integer iInteger1 = new Integer(200);
        int i1 = iInteger1;    //拆箱
        System.out.println(i1);

        Integer i3 = -128;
        Integer i4 = -128;    //判断是相等的
        //-128 ~ 127 用双等号判断是相等的
        if (i3 == i4) {
            System.out.println("Equal!");
        } else {
            System.out.println("No Equal!");
        }
    }
}