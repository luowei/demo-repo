package com.demo.util;

/**
 * “超大数”实用类
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 12-12-29
 * Time: 下午8:48
 * To change this template use File | Settings | File Templates.
 */
public abstract class GreatNumber {

    /**
     * 超大数字相加
     *
     * @param {String}greatNumber1 待加“超大数1”
     * @param {String}greatNumber2 待加“超大数2”
     * @param {int}precision       精度(如果为负数，不限精度)
     * @return String 两个“超大数”之和
     * @throws NumberFormatException
     */
    public static String add(String greatNumber1, String greatNumber2, int precision) throws RuntimeException {
        StringBuffer result = new StringBuffer("");
        //小数部分Fractional part
        String greatNumber1_FraPart = "";
        String greatNumber2_FraPart = "";
        //整数部分Integral part
        String greatNumber1_IntPart = "";
        String greatNumber2_IntPart = "";
        //小数点位置
        int locationOfDot = 0;
        //整数的小数部分补充0
        String zero = "";

        for (int i = 0; i < precision; i++) {
            zero += "0";
        }


        //划分整数部分和小数部分
        if (greatNumber1.indexOf('.') == -1) {
            greatNumber1_FraPart = zero;
            greatNumber1_IntPart = greatNumber1;
        } else {
            locationOfDot = greatNumber1.indexOf('.');
            greatNumber1_FraPart = greatNumber1.substring(locationOfDot + 1);
            greatNumber1_IntPart = greatNumber1.substring(0, locationOfDot);
        }
        //System.out.println("greatNumber1="+greatNumber1_IntPart+"."+greatNumber1_FraPart);

        if (greatNumber2.indexOf('.') == -1) {
            greatNumber2_FraPart = zero;
            greatNumber2_IntPart = greatNumber2;
        } else {
            locationOfDot = greatNumber2.indexOf('.');
            greatNumber2_FraPart = greatNumber2.substring(locationOfDot + 1);
            greatNumber2_IntPart = greatNumber2.substring(0, locationOfDot);
        }
        //System.out.println("greatNumber2="+greatNumber2_IntPart+"."+greatNumber2_FraPart);

        //小数部分
        StringBuffer result_FraPart = new StringBuffer();
        int carry = 0;
        int g1_FraPart_len = greatNumber1_FraPart.length();
        int g2_FraPart_len = greatNumber2_FraPart.length();
        int len = g1_FraPart_len > g2_FraPart_len ? g1_FraPart_len : g2_FraPart_len;

        //精度控制
        if (precision < 0) precision = len;
        len = len < precision ? precision : len;//取大
        //String up_FraPart = g1_FraPart_len>g2_FraPart_len?greatNumber1_FraPart:greatNumber2_FraPart;
        //if(precision<up_FraPart.length()) carry = up_FraPart.charAt(precision)-48>4?1:0;

        for (int i = len - 1; i >= 0; i--) {
            char c1 = g1_FraPart_len > i ? greatNumber1_FraPart.charAt(i) : '0';
            char c2 = g2_FraPart_len > i ? greatNumber2_FraPart.charAt(i) : '0';

            if (Character.isDigit(c1) && Character.isDigit(c2)) {
                int c = ((c1 - 48) + (c2 - 48) + carry) % 10;
                //进位
                if (i == precision) {
                    carry = ((c1 - 48) + (c2 - 48) + carry) / 5;
                    carry = carry > 0 ? 1 : 0;
                } else {
                    carry = ((c1 - 48) + (c2 - 48) + carry) / 10;
                }

                //精度控制
                if (i < precision) result_FraPart.insert(0, c);

            } else {
                throw new NumberFormatException("输入参数存在非数字字符.");
            }
        }


        //整数部分
        StringBuffer result_IntPart = new StringBuffer("");
        int g1_IntPart_len = greatNumber1_IntPart.length();
        int g2_IntPart_len = greatNumber2_IntPart.length();
        len = g1_IntPart_len > g2_IntPart_len ? g1_IntPart_len : g2_IntPart_len;

        for (int i = 0; i < len; i++) {
            char c1 = g1_IntPart_len > i ? greatNumber1_IntPart.charAt(g1_IntPart_len - 1 - i) : '0';
            char c2 = g2_IntPart_len > i ? greatNumber2_IntPart.charAt(g2_IntPart_len - 1 - i) : '0';

            if (Character.isDigit(c1) && Character.isDigit(c2)) {
                int c = ((c1 - 48) + (c2 - 48) + carry) % 10;
                carry = ((c1 - 48) + (c2 - 48) + carry) / 10;
                result_IntPart.insert(0, c);
            } else {
                throw new NumberFormatException("输入参数存在非数字字符.");
            }
        }
        if (carry != 0) result_IntPart.insert(0, carry);//最后一个进位

        result.append(result_IntPart);
        result.append(".");
        result.append(result_FraPart);

        return result.toString();
    }

    /**
     * 超大数字相加(不限精度)
     *
     * @param {String}greatNumber1 待加超大数1
     * @param {String}greatNumber2 待加超大数1
     * @return String 两个超大数之和
     * @throws NumberFormatException
     */
    public static String add(String greatNumber1, String greatNumber2) throws RuntimeException {
        return GreatNumber.add(greatNumber1, greatNumber2, -1);
    }

    public static void main(String[] aa) {
        String greatNumber1 = "9999999999999999999999999999999999999983.123456789";
        String greatNumber2 = "17.2";
        String result = GreatNumber.add(greatNumber1, greatNumber2, -6);
        System.out.println("   " + greatNumber1);
        System.out.println(" + " + greatNumber2);
        System.out.println("----------------------------------------------------------");
        System.out.println(" = " + result);
    }
}
