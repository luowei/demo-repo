package com.vvvv.ch02;

public class PrimitiveTest {
    public static void main(String[] args) {
        int ivar1 = 0x11;        //16进制
        int ivar2 = 011;        //8进制
        int ivar3 = 17;        //10进制
        float fvar1 = 12.5e5f;
        double dvar1 = 0.1234d;
        System.out.println("fvar1=" + fvar1);
        System.out.println("dvar1=" + dvar1);

        boolean flag = true;
        System.out.println("boolean:" + flag);

        System.out.println("ivar1=" + ivar1);
        System.out.println("ivar2=" + ivar2);
        System.out.println("ivar3=" + ivar3);

        char gender = 'F';
        char test = '\u0065';        //unicode码
        System.out.println("gender:" + gender);
        System.out.println("test:" + test);
    }
}