package com.vvvv.ch07;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 12-12-31
 * Time: 下午4:14
 * To change this template use File | Settings | File Templates.
 */
/*
断言：在程序运行过程中
        assert 布尔表达式;
assert 布尔表达式:"断言信息"

        开启断言：java -ea cho7.test
        布尔表达式返回false,抛出AsserttionError,判断程序运行过程中变量的值是否符合预期。

*/

public class AssertTest {
    public static void main(String[] args) {
        System.out.println("before assert");
        //assert 1!=1;
        assert 1 != 1 : "1!=1 return false";
        System.out.println("after assert");

        //运行：java -ea ch07.AssertTest
    }
}
