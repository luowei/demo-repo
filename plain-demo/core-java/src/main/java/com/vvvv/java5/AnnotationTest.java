package com.vvvv.java5;

import java.util.*;

import static java.lang.System.out;

import java.lang.annotation.*;
import java.lang.reflect.*;

public class AnnotationTest {
    @RententionTest(input = "ABC", output = "abc")
    public static String toLowerCase(String str) {
        return str.toLowerCase();
    }

    public static void main(String args[]) {
        Class clz = AnnotationTest.class;
        Method[] method = clz.getDeclaredMethods();    //得到类中的所有方法
        for (int i = 0; i < method.length; i++) {
            boolean hasAnnotation = method[i].isAnnotationPresent(RententionTest.class);
            if (hasAnnotation) {
                RententionTest test = (RententionTest) method[i].getAnnotation(RententionTest.class);    //得Annotation
                String input = test.input();    //拿到Annotation的值
                String expectedOutput = test.output();
                String realOutput = toLowerCase(input);
                if (expectedOutput.equals(realOutput)) {
                    out.println("Test Successful");
                } else {
                    throw new AssertionError("Test" + "failed!!!");
                }
            }
        }
    }
}