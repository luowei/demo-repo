package com.vvvv.test;

//类没有protected和private修饰符
class TestAA
{
    TestA testa=new TestA();
    public void visit()
    {

        // System.out.println(testa.a);	// 同一包中，无法访问其它类的private 属性a
        System.out.println(testa.b);	//同一包中，可以访问其它类的默认 属性b
        System.out.println(testa.c);	//同一包中，可以访问其它类的protected 属性c
        System.out.println(testa.d);	//同一包中，可以访问其它类的public 属性d

        // testa.printA();	//同一包中，无法访问其它类的private 方法printA
        testa.printB();	//同一包中，可以访问其它类的默认 方法printB
        testa.printC();	//同一包中，可以访问其它类的protected 方法printC
        testa.printD();	//同一包中，可以访问其它类的public 方法printD
    }
}

//同一包中的子类
class TestBB extends TestB
{
    TestB testb=new TestB();
    public void visit()
    {
        // System.out.println(testb.a);	//同一包中，无法访问父类的private 属性a
        System.out.println(testb.b);	//同一包中，可以访问父类的默认 属性b
        System.out.println(testb.c);	//同一包中，可以访问父类的protected 属性c
        System.out.println(testb.d);	//同一包中，可以访问父类的public 属性d

        // testb.printA();	//同一包中，无法访问父类的private 方法printA
        testb.printB();	//同一包中，可以访问父类的默认 方法printB
        testb.printC();	//同一包中，可以访问父类的protected 方法printC
        testb.printD();	//同一包中，可以访问父类的public 方法printD
    }
}

/*
TestC被final修饰，无法被继承
class TestCC extends TestC
{
	TestC testc=new TestC();
	public void visit()
	{
		System.out.println(testc.a);	//同一包中，访问其它类的private 属性a
		System.out.println(testc.b);	//同一包中，访问其它类的默认 属性b
		System.out.println(testc.c);	//同一包中，访问其它类的protected 属性c
		System.out.println(testc.d);	//同一包中，访问其它类的public 属性d

		testc.printA();	//同一包中，访问其它类的private 方法printA
		testc.printB();	//同一包中，访问其它类的默认 方法printB
		testc.printC();	//同一包中，访问其它类的protected 方法printC
		testc.printD();	//同一包中，访问其它类的public 方法printD
	}
}
*/

public class TestMain
{
    public static void main(String []args)
    {
        System.out.println("-----------------同一包中------------------");
        TestAA testaa=new TestAA();
        testaa.visit();
        System.out.println("-----------------同一包中子类------------------");
        TestBB testbb=new TestBB();
        testbb.visit();

        // TestCC testcc=new TestCC();
        // testcc.visit();
    }
}