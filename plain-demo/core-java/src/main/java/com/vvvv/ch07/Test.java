package com.vvvv.ch07;

public class Test {
    public static void main(String[] args) {
        StringBuffer strbf = new StringBuffer("new StringBuffer ok!");
        System.out.println(strbf.toString());

        StringBuffer[] strbfs = new StringBuffer[10];

        for (int i = 0; i < 10; i++) {
            strbfs[i] = new StringBuffer("aaaaaa");
            System.out.println(strbfs[i].toString());
        }
    }
}