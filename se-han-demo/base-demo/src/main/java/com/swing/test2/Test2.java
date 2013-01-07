/*
 * java的反射机制，泛型的经典应用
 */

package com.swing.test2;

import java.lang.reflect.Method;

public class Test2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// 
		Gen<String> gen1=new Gen<String>("aaa");
		gen1.showTypeName(gen1);
		Gen<Integer> gen2=new Gen<Integer>(1);
		gen2.showTypeName(gen2);
		
		Gen<Bird> gen3=new Gen<Bird>(new Bird());
		gen3.showTypeName(gen3);
	}

}

//定义一个类
class Gen<T> //定义的一种未知类型T，即泛型
{
	private T o;//用泛型定义一个变量(或对象)
	//构造函数
	public Gen(T a)
	{
		o=a;
	}
	
	//得到T的类型名称
	public void showTypeName(Gen<T> obj)//参数是泛型
	{
		System.out.println("类型是："+o.getClass().getName());
		//通过反射机制，可以得到T这个类型的很多信息
		Method[] m=o.getClass().getDeclaredMethods();//得到某个类型的函数
		//打印
		for(int i=0;i<m.length;i++)
		{
			System.out.println(m[i].getName());
		}
	}
}

//定义一个Bird
class Bird
{
	public void test1()
	{
		System.out.println("aa");
	}
	public void count(int a,int b)
	{
		System.out.println(a+b);
	}
}

/*
 *泛型的优点
 *1.类型安全
 *2.向后兼容
 *3. 层次清晰
 *4.性能较高，用Gj编写的代码可以为java编译器和虚拟机带来更多的类型信息。
 *
 *泛型主要解决安全和代码重用的问题
 *在没有泛型之前，通过对类型Object的引用来实现参数的”任意化“，”任意化“
 *带来的缺点是要做显式的类型转换，而这种转换是要求开发者对实际参数类型
 *可以预知的情况下进行的。对于强制类型转换错误的情况，编译器可能不提示
 *错误 ，在运行时才出现异常，这是一个安全隐患。
 *泛型的好处是在编译的时候检查类型安全，并且所有的强制转换都是自动和隐
 *式的，提高代码的重用率。
*/
