/*
求两个数的最大公约数 与 最大公倍数
*/
package com.vvvv.ch03;

public class MaxDivisor {
    public static void main(String[] args) {
        MaxDivisor opt = new MaxDivisor();
        //System.out.println(Integer.valueOf(args[0])%Integer.valueOf(args[1]));
        System.out.println("最大公约数为：" + opt.commonDivisor2(Integer.valueOf(args[0]), Integer.valueOf(args[1])));
        System.out.println("最小公倍数为：" + opt.commonMultiple(Integer.valueOf(args[0]), Integer.valueOf(args[1])));

        //问号表达式
        int b = Integer.valueOf(args[0]);
        int a = (b == 0 ? b : (b + 2));

        System.out.println("opt.ret(b): " + opt.ret(b) + "\na = " + a + " b = " + b);
        System.out.println("Add(a,b) = " + opt.Add(a, b));
        System.out.println("1^1:" + (1 ^ 1));    //得0
        System.out.println("0^0:" + (0 ^ 0));    //得0
    }

    //测试问号表达式
    public int ret(int b) {
        return b == 0 ? b : (b += 2);
    }

    //最大公倍数
    public int commonDivisor1(int num1, int num2) {
        return (num1 % num2 == 0) ? num2 : commonDivisor1(num2, num1 % num2);
    }

    public int commonDivisor2(int num1, int num2) {
        int t, a;
        if (num1 < num2) {
            //shift1(num1,num2);
            //shift2(num1,num2);
            shift3(num1, num2);
        }
        while ((a = num1 % num2) != 0) {
            num1 = num2;
            num2 = a;
        }
        return num1;
    }

    //最小公倍数
    public int commonMultiple(int num1, int num2) {
        return num1 * num2 / commonDivisor1(num1, num2);
    }

    //交换函数
    public void shift1(int num1, int num2) {
        int t = num1;
        num1 = num2;
        num2 = t;
    }

    public void shift2(int num1, int num2) {
        num1 = num1 + num2;
        num2 = num1 - num2;
        num1 = num1 - num2;
    }

    public void shift3(int num1, int num2) {
        num1 = num1 ^ num2;    //异或，相同为0，不同为1，找出不同的位(异或之后，为1的位)，
        num2 = num1 ^ num2;    //再异或得到初始num1的值赋给num2
        num1 = num1 ^ num2;    //再异或得到初始num2的值赋给num1
        //num2的各位与0异或(两数中相同的位)保持不变，num2的各位与1异或(两数中不相同的位)反转，
    }

    //位运算实现加法
    public int Add(int num1, int num2) {
        return num2 == 0 ? num1 : Add(num1 ^ num2, (num1 & num2) << 1);
        //先不计进位相加(num1^num2),得出的结果，再与进位相加，随着递归，直至进位变为0，递归结束
    }
}