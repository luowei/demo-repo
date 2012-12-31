package com.vvvv.ch04;

import java.util.Calendar;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 12-12-31
 * Time: 下午4:10
 * To change this template use File | Settings | File Templates.
 */
public class CalendarTest {

    public static void main(String[] args) {
        //获取当前日期
        Calendar cal = Calendar.getInstance();
        cal.get(Calendar.YEAR);        //得到当前日期的年份
        cal.get(Calendar.MONTH);    //得到当前日期的月份
//        cal.set();	//设置日期
    }
}
