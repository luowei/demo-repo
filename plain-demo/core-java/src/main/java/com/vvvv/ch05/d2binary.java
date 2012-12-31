package com.vvvv.ch05;

public class d2binary {
    public static void main(String[] args) {
        int a = 10;
        for (int i = 31; i >= 0; i--) {
            System.out.print(a >> i & 1);    //输出二进制
        }
        System.out.print("\n\n");

        d2binary op = new d2binary();

        int b = 15;
        int c;

//        c=op.add(1,1);
//        System.out.println("\nAdd(10,15)-->c="+c);

    }

	/*交换两个变量的值，有三种方法：
    1.利用位操作(异或 ^)
	2.两数相加、相减、相减
	3.使用中间变量
	*/

    //利用位操作交换两变量的值
    public void shift3(int num1, int num2) {
        num1 = num1 ^ num2;    //异或，相同为0，不同为1，找出不同的位(异或之后，为1的位)，
        num2 = num1 ^ num2;    //再异或得到初始num1的值赋给num2
        num1 = num1 ^ num2;    //再异或得到初始num2的值赋给num1
        //num2的各位与0异或(两数中相同的位)保持不变，num2的各位与1异或(两数中不相同的位)反转，
    }

    //从32位的单元中取出某几位
    public int getMidBits(int val, int n1, int n2) {
        int z;
        z = ~0;    //将z初始化16位的1
        z = (z >> n1) & (z << (32 - n2));    //将两端的化成0,中间的化成1
        z = val & z;
        z = z >> (32 - n2);
        return z;
    }

    //对32的二进制数取出它的奇数位(从左边起1，3，5 。。。)
    public int getOddBits(int val) {
        int z, a, q;
        z = 0;
        for (int i = 1; i <= 31; i += 2) {
            q = 1;
            for (int j = 1; j <= (32 - i - 1) / 2; j++)    //要取的数的位数为原来的数的位数的1/2
                q = q * 2;        //原数进位指针进两位，要取的数的指针进一位
            a = val >> (32 - i);    //将第i个位置的数移到最低位
            a = a << 31;        //通过左移31位，将将最低位移到最高位去，其后的位全都补0
            a = a >> 31;        //右移31位，将最高位移到最低，其前面的位全都补零，得到第i位
            z = z + a * q;        //积加取出的数
        }
        return z;
    }

    //算术右移：低位溢出，符号位不变，并用符号位补溢出的高位
    //算术左移：符号位不变，低位补0
    //逻辑右移:低位溢出，高位补零

    //实现算术右移函数
    public int shiftRightArithmetic(int val, int n) {
        int z;
        z = ~0;
        z = z >> n;
        z = ~z;
        z = z | (val >> n);
        return z;
    }

    //实现逻辑右移函数
    public int shiftRightLogical(int val, int n) {
        int z;
        z = (~(1 >> n)) & (val >> n);
        return z;
    }

    //实现右在循环移位
    public int moveRightCircle(int val, int n) {
        int z;
        z = (val >> n) | (val << (32 - n));
        return z;
    }

    //实现左循环移位
    public int moveLeftCircle(int val, int n) {
        int z;
        z = (val >> (32 - n)) | (val << n);
        return z;
    }

    //根据原码求补码(求二进制数的补码)
    public int realBits2MaskBit(int val) {
        int z;
        z = val & 10000000;
        if (z == 10000000)
            z = ~val + 1;
        else
            z = val;
        return z;
    }
    //一个正数的补码等于该数的原码，一个负数的补码等于该数的反码加1
}

/*
写一个日期类Date
1.属性 year month day,假每个月有30天
2.日期的输出格式为"DD-MM-YYYY"
3.可以处理任意日期的加法和减法操作
	22-11-2011 + 10-》
	提供一个方法add(int day)
4.处理范围1900.1.1-9999.12.30
	非法日期1900.1.1
*/
//螺旋数组问题









