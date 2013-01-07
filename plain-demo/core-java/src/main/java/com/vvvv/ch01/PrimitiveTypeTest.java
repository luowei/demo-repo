package com.vvvv.ch01;

//java中的基本数据类型 .
public class PrimitiveTypeTest {
    //private static PrimitiveTypeTest primitiveType;
    private int i1;

    public int getI1() {
        return i1;
    }

    public Integer getI2()//调用它是因为自动拆箱、装箱，所有不报错
    {
        return i1;
    }

    public int obtainInt() {
        return i1;
    }

    /**
     * public void initVal()
     * {
     * System.out.println("-----------------initVal  int i1:"+i1);
     * primitiveType=new PrimitiveTypeTest();
     * }
     */

    private Integer i2;

    public Integer getInteger() {
        return i2;
    }

    private byte b1;

    public Byte getB1() {
        return b1;
    }

    boolean bool;

    public Boolean getBoolean() {
        return bool;
    }

    short srt;

    public short getShort() {
        return srt;
    }

    long lg;

    public long getLong() {
        return lg;
    }

    float flt;

    public float getFloat() {
        return flt;
    }

    double dble;

    public double getDouble() {
        return dble;
    }

    char cha;

    public char getChar() {
        return cha;
    }

    public static void main(String[] args) {
        PrimitiveTypeTest primitiveType = new PrimitiveTypeTest();
        // byte    
        System.out.println("基本类型：byte 二进制位数：" + Byte.SIZE);
        System.out.println("包装类：java.lang.Byte");
        System.out.println("最小值：Byte.MIN_VALUE=" + Byte.MIN_VALUE);
        System.out.println("最大值：Byte.MAX_VALUE=" + Byte.MAX_VALUE);
        //Byte b1=new Byte("0");
        //System.out.println("Byte默认赋值为："+b1);
        System.out.println("byte默认赋值为：" + primitiveType.getB1());
        System.out.println("-----------------------------------");

        //-------------------short-----------------------  
        System.out.println("基本类型：short 二进制位数：" + Short.SIZE);
        System.out.println("包装类：java.lang.Short");
        System.out.println("最小值：Short.MIN_VALUE=" + Short.MIN_VALUE);
        System.out.println("最大值：Short.MAX_VALUE=" + Short.MAX_VALUE);
        System.out.println("short默认赋值为：" + primitiveType.getShort());
        //Short s1;
        //short s2;
        //System.out.println("Short默认赋值为："+(s1==0));
        //System.out.println("short默认赋值为："+s2);
        System.out.println("-----------------------------------");
        //-------------------------int-------------------------
        System.out.println("基本类型：int 二进制位数：" + Integer.SIZE);
        System.out.println("包装类：java.lang.Integer");
        System.out.println("最小值：Integer.MIN_VALUE=" + Integer.MIN_VALUE);
        System.out.println("最大值：Integer.MAX_VALUE=" + Integer.MAX_VALUE);
        //Integer i1;
        //System.out.println("Integer默认赋值为："+(i1==0));
        //只有通过new 了之后才能取到默认值
        System.out.println("int默认赋值为：" + primitiveType.getI1());
        System.out.println("int默认赋值为：" + primitiveType.getI2());
        System.out.println("int默认赋值为：" + primitiveType.obtainInt());
        System.out.println("Integer默认赋值为：" + primitiveType.getInteger());
        //System.out.println("int默认赋值为："+i1);
        System.out.println("-----------------------------------");

        //--------------------------long-------------------  
        System.out.println("基本类型：long 二进制位数：" + Long.SIZE);
        System.out.println("包装类：java.lang.Long");
        System.out.println("最小值：Long.MIN_VALUE=" + Long.MIN_VALUE);
        System.out.println("最大值：Long.MAX_VALUE=" + Long.MAX_VALUE);
        System.out.println("long默认赋值为：" + primitiveType.getLong());
        //Long l1;
        //long l2;
        //System.out.println("Long默认赋值为："+(l1==0));
        //System.out.println("long默认赋值为："+l2);
        System.out.println("-----------------------------------");

        //------------------------float---------------------------    
        System.out.println("基本类型：float 二进制位数：" + Float.SIZE);
        System.out.println("包装类：java.lang.Float");
        System.out.println("最小值：Float.MIN_VALUE=" + Float.MIN_VALUE);
        System.out.println("最大值：Float.MAX_VALUE=" + Float.MAX_VALUE);
        System.out.println("float默认赋值为：" + primitiveType.getFloat());
        //Float f1;
        //float f2;
        //System.out.println("Float默认赋值为："+(f1==0));
        System.out.println("-----------------------------------");

        //-------------------------double------------------------   
        System.out.println("基本类型：double 二进制位数：" + Double.SIZE);
        System.out.println("包装类：java.lang.Double");
        System.out.println("最小值：Double.MIN_VALUE=" + Double.MIN_VALUE);
        System.out.println("最大值：Double.MAX_VALUE=" + Double.MAX_VALUE);
        System.out.println("double默认赋值为：" + primitiveType.getDouble());
        //Double d1;
        //double d2;
        //System.out.println("Double默认赋值为："+(d1==0));
        //System.out.println("double默认赋值为："+d2);
        System.out.println("-----------------------------------");

        //---------------------char----------------------    
        System.out.println("基本类型：char 二进制位数：" + Character.SIZE);
        System.out.println("包装类：java.lang.Character");
        // 以数值形式而不是字符形式将Character.MIN_VALUE输出到控制台    
        System.out.println("最小值：Character.MIN_VALUE="
                + (int) Character.MIN_VALUE);
        // 以数值形式而不是字符形式将Character.MAX_VALUE输出到控制台    
        System.out.println("最大值：Character.MAX_VALUE="
                + (int) Character.MAX_VALUE);
        System.out.println("char默认赋值为：" + primitiveType.getChar());
        //Character c1;
        //char c2;
        //System.out.println("Character默认赋值为："+(c1=='\0'));
        //System.out.println("char默认赋值为："+c2);
        System.out.println("-----------------------------------");

        //-----------------------Boolean---------------------
        System.out.println("包装类：java.lang.Boolean");
        Boolean b = new Boolean(false);
        System.out.println("boolean默认赋值为：" + primitiveType.getBoolean());
        System.out.println("-----------------------------------");
    }
} 