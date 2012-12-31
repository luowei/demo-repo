package com.vvvv.java5;

import java.util.*;

import static java.lang.System.out;

public class VarTest {
    public static int add(int... args) {
        int length = args.length;
        int result = 0;
        for (int i = 0; i < length; i++) {
            result += args[i];
        }
        out.println("args.length:" + args.length);
        return result;
    }

    public static void main(String... args) {
        out.println(add(1, 2));
        out.println(add());
        out.println(add(100, 200, 300, 800));
    }
}