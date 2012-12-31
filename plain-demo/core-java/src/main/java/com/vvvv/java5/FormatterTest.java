package com.vvvv.java5;

import java.util.*;

public class FormatterTest {
    public static void main(String[] args) {
        StringBuffer sb = new StringBuffer();
        StringBuffer sb2 = new StringBuffer();
        StringBuffer sb3 = new StringBuffer();
        Formatter formatter = new Formatter(sb);
        Formatter formatter2 = new Formatter(sb2);
        Formatter formatter3 = new Formatter(sb3);
        //formatter.format("%1$tY",new java.util.Date());
        //System.out.println(sb);

        formatter.format("%1$tY-%1$tm-%1$td  %1$tD  %1$tH:%1$tM:%1$tS "
                + "\n %1$tr \n%1$tF \n %1$tH/%1$tI:%1$tM:%1$tS.%1$tL", new Date());
        System.out.println(sb);

        System.out.println("-------------------------------");
        formatter2.format("%1$tY-%1$tm-%1$td  %1$tD  %1$tH:%1$tM:%1$tS "
                + "\n %1$tr \n%1$tF \n %1$tH/%1$tI:%1$tM:%1$tS.%1$tL", System.currentTimeMillis());
        System.out.println(sb2);

        System.out.println("-------------------------------");
        formatter3.format("%1$tY-%1$tm-%1$td  %1$tD  %1$tH:%1$tM:%1$tS "
                + "\n %1$tr \n%1$tF \n %1$tH/%1$tI:%1$tM:%1$tS.%1$tL", Calendar.getInstance());
        System.out.println(sb3);
    }
}