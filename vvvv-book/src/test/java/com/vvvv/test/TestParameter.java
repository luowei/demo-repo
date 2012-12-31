package com.vvvv.test;

public class TestParameter
{
    public static void test(String[] args)
    {

    }
    //可变参数
    public static void test2(String... args)
    {
        for(int i=0;i<args.length;i++)
        {
            System.out.println(args[i]);
        }
        int a=10;
        Integer b=a;	//装箱
        int c=b;		//拆箱
        for (String string : args)
        {
            System.out.println(string);
        }
    }
    /**
     * @param args
     */
    public static void main(String[] args)
    {
        TestParameter.test2();
        TestParameter.test2("aaaa","bbbbb");;
        String[] ggg={"gggg","ggggggg"};
        TestParameter.test2(ggg);
    }
}

/**
 JDK1.5的新功能：
 1.自动拆箱装箱
 2.foreach循环
 3.泛型
 4.静态导入
 5.元数据(注解)
 */

