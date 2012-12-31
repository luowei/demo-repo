package com.vvvv.test;

//类没有protected和private修饰符
//被public 修饰的类，可以被其它包中的类访问
public class TestA
{
    //private 级别的属性和方法
    private int a=101;
    private void printA()
    {
        System.out.println("printA方法："+this.a);
    }

    //默认级别的属性和方法
    int b=102;
    void printB()
    {
        System.out.println("printB方法："+this.b);
    }

    //protected级别的属性和方法
    protected int c=103;
    protected void printC()
    {
        System.out.println("printC方法："+this.c);
    }

    //public级别的属性和方法
    public int d=104;
    public void printD()
    {
        System.out.println("printD方法："+this.d);
    }
}

//没有被public 修饰的类不能被其它包中的类访问
class TestB
{
    //private 级别的属性和方法
    private int a=101;
    private void printA()
    {
        System.out.println("printA方法："+this.a);
    }

    //默认级别的属性和方法
    int b=102;
    void printB()
    {
        System.out.println("printB方法："+this.b);
    }

    //protected级别的属性和方法
    protected int c=103;
    protected void printC()
    {
        System.out.println("printC方法："+this.c);
    }

    //public级别的属性和方法
    public int d=104;
    public void printD()
    {
        System.out.println("printD方法："+this.d);
    }
}

//被final修饰的类不能被继承
final class TestC
{
    //private 级别的属性和方法
    private int a=101;
    private void printA()
    {
        System.out.println("printA方法："+this.a);
    }

    //默认级别的属性和方法
    int b=102;
    void printB()
    {
        System.out.println("printB方法："+this.b);
    }

    //protected级别的属性和方法
    protected int c=103;
    protected void printC()
    {
        System.out.println("printC方法："+this.c);
    }

    //public级别的属性和方法
    public int d=104;
    public void printD()
    {
        System.out.println("printD方法："+this.d);
    }
}

