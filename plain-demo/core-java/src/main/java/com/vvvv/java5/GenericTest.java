package com.vvvv.java5;

import java.util.*;

import static java.lang.System.out;

public class GenericTest {
    public static void main(String... args) {
        List<String> list = new ArrayList<String>();
        list.add("string1");
        list.add("string2");
        list.add((new java.util.Date()).toString());
        for (Object obj : list) {
            //String string=(String)obj;
            System.out.println(obj);
        }

        out.println("------------------------------");
        Map<String, String> map = new HashMap<String, String>();
        map.put("Hello", "java");
        map.put("World", "sun");
        map.put("HelloWorld", "eclipse");
        for (String str : map.keySet()) {
            out.println("key:" + str + "\tvalue:" + (String) map.get(str));
        }

        out.println("------------------------------");
        for (Object obj : map.entrySet()) {
            Map.Entry entry = (Map.Entry) obj;
            System.out.println(entry.getKey() + "=>" + entry.getValue());
        }

        out.println("------------------------------");
        List<Number> list1 = new ArrayList<Number>();
        list1.add(5.5f);
        list1.add(100);
        sum(list1);

        out.println("------------------------------");
        Integer[] arrayInt = new Integer[]{1, 100, 999};
        String[] arayString = new String[]{"str1", "str2", "str3"};
        print(arrayInt);
        print(arayString);
    }

    public static void sum(List<? extends Number> list) {
        Number result = new Float(0);
        for (Number num : list) {
            //out.println("result.floatValue:"+result.floatValue());
            //result=new Float(result.floatValue()+num.floatValue());
            //给对象赋值,用new的方式，太浪费空间
            result = result.floatValue() + num.floatValue();    //自动装箱
            //out.println("result:"+result.floatValue());
            out.println("result:" + result.floatValue());        //自动拆箱
        }
    }

    //add(E element)
    public static <E> void print(E[] array)    //E代表可以传入任意类型,<E> 表示声明E为任意类型
    {
        for (Object obj : array) {
            out.println(obj);
        }
    }
}