package com.vvvv.java5;

import java.lang.reflect.Method;

public class AnnotationTest2 {
    @Test(input = "ABC", output = "acc")
    public static String toLowerCase(String str) {
        return str.toLowerCase();
    }

    public static void main(String[] args) {
        Class clz = AnnotationTest.class;
        Method[] method = clz.getDeclaredMethods();
        for (int i = 0; i < method.length; i++) {
            boolean hasAnnotation = method[i].isAnnotationPresent(Test.class);
            if (hasAnnotation) {
                Test test = (Test) method[i].getAnnotation(Test.class);
                String input = test.input();
                String expectedoutput = test.output();
                String realOutput = toLowerCase(input);
                if (expectedoutput.equals(realOutput)) {
                    System.out.println("Test successed!!");
                } else {
                    throw new AssertionError("Test failed!!!");
                }
            }
        }
    }

}
