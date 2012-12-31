package com.vvvv.java5;

import java.util.*;

import static java.lang.System.out;

enum Size    //枚举类
{
    //LARGE,MIDDLE,SMALL	//加个封号，和不加封号都没错,最好加封号
    LARGE(50) {
        public String toString() {
            return "size:large value:" + this.getValue();
        }
    },
    MIDDLE(30) {
        public String toString() {
            return "Size:middle value:" + this.getValue();
        }
    },
    SMALL(20) {
        public String toString() {
            return "Size:small value:" + this.getValue();
        }
    };

    Size() {
    }

    Size(int value) {
        this.value = value;
    }

    private int value;

    public int getValue() {
        return this.value;
    }
}


public class EnumTest {
    private Size size;

    public EnumTest(Size size) {
        this.size = size;
    }

    public static void main(String... args) {
        EnumTest test = new EnumTest(Size.LARGE);
        out.println(test);
    }

    public String toString() {
        out.println(size.toString());
        return Arrays.deepToString(size.values());
    }

}