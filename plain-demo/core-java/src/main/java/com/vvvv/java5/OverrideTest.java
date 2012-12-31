package com.vvvv.java5;

import java.util.*;

import static java.lang.System.out;


public class OverrideTest {

    public static void main(String... args) {
        OverrideTest test = new OverrideTest();
        System.out.println(test);
    }

    @Override
    public String toString() {
        return "something";
    }

}