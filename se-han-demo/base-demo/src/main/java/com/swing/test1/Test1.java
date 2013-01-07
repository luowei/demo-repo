/*
 * 功能：位运算和移位运算
 */

package com.swing.test1;

public class Test1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("~2="+(~2));//取反
		System.out.println("2&3="+(2&3));//与
		System.out.println("2|3="+(2|3));//或
		System.out.println("~-5="+(~-5));//取反
		System.out.println("-3^3="+(-3^3));//异或

		//算术右移：低位溢出，符号位不变，并用符号位补溢出的高位
		//算术左移：符号位不变，低位补0
		//逻辑右移:低位溢出，高位补零
		System.out.println("1>>2="+(1>>2));
		System.out.println("-1>>2="+(-1>>2));
		System.out.println("1<<2="+(1<<2));
		System.out.println("-1<<2="+(-1<<2));
		System.out.println("3>>>2="+(3>>>2));//逻辑右移
		
	}
}
