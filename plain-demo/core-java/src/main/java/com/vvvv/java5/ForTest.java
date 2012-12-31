package com.vvvv.java5;

import java.util.*;

public class ForTest {
    public static void main(String[] args) {
        List list = new ArrayList();
        list.add("abc");
        list.add("xyz");
        list.add("test");
        for (Object obj : list)    //遍历list
        {
            String string = (String) obj;
            System.out.println(string);
        }
        String[] strings = new String[]{"core java", "sql", "xml", "hibernate"};
        System.out.println("-------------------------------");
        for (String str : strings)    //遍历数组
        {
            System.err.println(str);
        }
        System.out.println("-------------------------------");
        Set set = new HashSet();    //遍历Set
        set.add("set1");
        set.add("set2");
        set.add("set3");
        for (Object obj : set) {
            String string = (String) obj;
            System.out.println(string);
        }
        System.out.println("-------------------------------");
        Map map = new HashMap();    //遍历Map
        map.put(1, "map1");
        map.put(2, "map2");
        map.put(3, "map3");
        for (Object obj : map.entrySet()) {
            Map.Entry entry = (Map.Entry) obj;
            System.out.println(entry.getKey() + "=>" + entry.getValue());
        }
    }
}