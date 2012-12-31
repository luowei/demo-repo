package com.vvvv.java5;

import java.util.*;

import static java.lang.System.out;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RententionTest {
    String input();

    String output();
}