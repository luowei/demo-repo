package com.demo.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Sting工具
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 12-12-29
 * Time: 下午8:22
 * To change this template use File | Settings | File Templates.
 */
public abstract class StringUtil {

    /**
     * 去掉给定字符串前和后的空格，返回干净的字符串
     * @param args
     * @return String
     */
    public static String removeSpaces(String args) {

        if(args!=null){
            args = args.trim();
            while (args.startsWith(" ")) {
                args = args.substring(1, args.length()).trim();
            }
            while (args.endsWith(" ")) {
                args = args.substring(0, args.length() - 1).trim();
            }
        }else{
            args = "";
        }

        return args;
    }


    /**
     * 转全角的函数
     * @param input
     * @return String
     */
    public static String toSBC(String input) {
        //半角转全角：
        char[] c = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 32) {
                c[i] = (char) 12288;
                continue;
            }
            if (c[i] < 127) {
                c[i] = (char) (c[i] + 65248);
            }
        }
        return new String(c);
    }


    /**
     * 转半角的函数
     * @param input
     * @return String
     */
    public static String toDBC(String input) {
        char[] c = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 12288) {
                c[i] = (char) 32;
                continue;
            }
            if (c[i] > 65280 && c[i] < 65375){
                c[i] = (char) (c[i] - 65248);
            }
        }
        return new String(c);
    }


    /**
     * 为每添加一个元素前面增加指定的分隔 除第一个元素之外
     * @param strB
     * @param appStr
     * @param compart
     * @return
     */
    public static StringBuffer appendElement(StringBuffer strB,String appStr,String compart){

        //当出入参数为NULL时
        if(strB == null){
            return new StringBuffer(appStr);
        }

        //当没有元素时直接添加追加元素 否则先添加分隔符
        if(strB.length() == 0){
            strB.append(appStr);
        }else{
            strB.append(compart);
            strB.append(appStr);
        }

        return strB;
    }


    /**
     * 移除元素
     * @param strB
     * @param moveStr
     * @param compart
     * @return
     */
    public static StringBuffer moveElement(StringBuffer strB,String moveStr,String compart){

        //当出入参数为NULL时
        if(strB == null){
            return strB;
        }

        StringBuffer newStrB = new StringBuffer();

        String[] strArray = strB.toString().split(compart);
        for(int i=0;i<strArray.length;i++){

            if(moveStr.equals(strArray[i])){
                continue;
            }

            if(i == 0){
                newStrB.append(strArray[i]);
            }else{
                newStrB.append(compart);
                newStrB.append(strArray[i]);
            }
        }
        return newStrB;
    }


    /**
     * 移除第一个匹配的元素
     * @param strB
     * @param moveStr
     * @param compart
     * @return
     */
    public static StringBuffer moveFirstElement(StringBuffer strB,String moveStr,String compart){

        //当出入参数为NULL时
        if(strB == null){
            return strB;
        }

        StringBuffer newStrB = new StringBuffer();

        String[] strArray = strB.toString().split(compart);
        boolean tag = false;
        for(int i=0;i<strArray.length;i++){

            if(moveStr.equals(strArray[i]) == true && tag == false){
                tag = true;
                continue;
            }

            if(i == 0){
                newStrB.append(strArray[i]);
            }else{
                newStrB.append(compart);
                newStrB.append(strArray[i]);
            }
        }



        return newStrB;
    }


    /**
     * 从给定字符中 返回所含的中文字符 并按每组以以字符串数组的形式返回
     * @param src
     * @return
     */
    public static String[] getChinese(String src){

        List list = new ArrayList();

        byte[] srcByte = src.getBytes();
        int srcLength = srcByte.length;


        int begin = -1;
        int end = -1;

        for(int i=0;i<srcLength;i++){

            //设置中文的开始位
            if(srcByte[i] < 0 && begin == -1){
                begin = i;
            }

            //设置中文的结束位
            if(srcByte[i] > 0 && begin != -1 && end == -1){
                end = i;
            }


            //如果已经找到中文的开始 但直到最后也没找到中文的结束，则将字符的结束位当成中文的截止位
            if(begin != -1 && i == srcLength - 1){
                end = i;
            }


            //将中文提取出来
            if(begin != -1 && end != -1){


                int tempLength = end-begin+1;
                if(tempLength % 2 != 0){
                    tempLength = tempLength - 1;
                }


                byte[] tempByte = new byte[tempLength];
                System.arraycopy(srcByte,  begin,  tempByte,  0,  tempLength);

                list.add(new String(tempByte));

                begin = -1;
                end = -1;
            }

        }


        //将中文以数组输出
        int size = list.size();
        String[] chineseArray = new String[size];
        for(int i=0;i<size;i++){
            chineseArray[i] = list.get(i).toString();
        }

        return chineseArray;
    }


    public static String zeroleft(String str,Integer len){
        if(str==null||str.trim().length()<1)
            str = "1";
        while(str.length()<len){
            str ="0" + str;
        }
        return str;
    }

    public static void main(String[] args) {

        StringBuffer str = new StringBuffer("a,c,d,c");
        str = StringUtil.moveFirstElement(str, "c", ",");
        System.out.println(str.toString());
    }

}
