package com.demo.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 * 日期时间工具类
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 12-12-29
 * Time: 下午8:14
 * To change this template use File | Settings | File Templates.
 */
public abstract class DateUtil {

    public static String yyyy_MM_dd = "yyyy-MM-dd";
    public static String yyyyMMdd = "yyyyMMdd";
    public static String yyyyMM = "yyyyMM";
    public static String yyyy_MM = "yyyy-MM";
    public static String yyyy_MM_dd_HH_mm = "yyyy-MM-dd HH:mm";
    public static String yyyyMMddHHmm = "yyyyMMddHHmm";
    public static String yyyyMMddHHmmss = "yyyyMMddHHmmss";
    public static String yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";

    /**
     * 将字符串时间改成Date类型
     * @param format
     * @param dateStr
     * @return
     */
    public  static Date strToDate(String format,String dateStr) {

        Date date = null;

        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
            date = simpleDateFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }


    /**
     * 将Date时间转成字符串
     * @param format
     * @param date
     * @return
     */
    public static String DateToStr(String format,Date date){

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);

        return simpleDateFormat.format(date);
    }


    /**
     * 获取2个字符日期的天数差
     * @param p_startDate
     * @param p_endDate
     * @return 天数差
     */
    public static long getDaysOfTowDiffDate( String p_startDate, String p_endDate ){

        Date l_startDate = DateUtil.strToDate(DateUtil.yyyy_MM_dd, p_startDate);
        Date l_endDate = DateUtil.strToDate(DateUtil.yyyy_MM_dd, p_endDate);
        long l_startTime = l_startDate.getTime();
        long l_endTime = l_endDate.getTime();
        long betweenDays = (long) ( ( l_endTime - l_startTime ) / ( 1000 * 60 * 60 * 24 ) );
        return betweenDays;
    }


    /**
     * 获取2个Date型日期的分钟数差值
     * @param p_startDate
     * @param p_endDate
     * @return 分钟数差值
     */
    public static long getMinutesOfTowDiffDate( Date p_startDate, Date p_endDate ){

        long l_startTime = p_startDate.getTime();
        long l_endTime = p_endDate.getTime();
        long betweenMinutes = (long) ( ( l_endTime - l_startTime ) / ( 1000 * 60 ) );
        return betweenMinutes;
    }

    /**
     * 获取2个字符日期的天数差
     * @param l_startDate
     * @param l_endDate
     * @return 天数差
     */
    public static long getDaysOfTowDiffDate( Date l_startDate, Date l_endDate ){

        long l_startTime = l_startDate.getTime();
        long l_endTime = l_endDate.getTime();
        long betweenDays = (long) ( ( l_endTime - l_startTime ) / ( 1000 * 60 * 60 * 24 ) );
        return betweenDays;
    }


    /**
     * 给出日期添加一段时间后的日期
     * @param dateStr
     * @param plus
     * @return
     */
    public static String getPlusDays(String format,String dateStr,long plus){

        Date date = DateUtil.strToDate(format, dateStr);

        long time = date.getTime()+ plus*24*60*60*1000;


        return DateUtil.DateToStr(format,new Date(time));
    }


    /**
     * 给出日期添加一段时间后的日期
     * @param format
     * @param date
     * @param plus
     * @return
     */
    public static String getPlusDays(String format,Date date,long plus){


        long time = date.getTime()+ plus*24*60*60*1000;


        return DateUtil.DateToStr(format,new Date(time));
    }

    /**
     * <p>求两个日期相差天数</p>
     * @param sd 起始日期，格式yyyy-MM-dd
     * @param ed 终止日期，格式yyyy-MM-dd
     * @return 两个日期相差天数
     */
    public static long getIntervalDays(String sd, String ed) {
        //Date.valueOf转换成标准日期格式，getTime()获取到1970年以来的毫秒数
        return ((java.sql.Date.valueOf(ed)).getTime() - (java.sql.Date.valueOf(sd)).getTime())/ (3600 * 24 * 1000);
    }

    /**
     * <p>获取与指定日期相差指定天数的日期</p>
     * @param date yyyy/MM/dd
     * @param offset 正整数
     * @return yyyy/MM/dd
     */
    public static String getDateOffset(String date, int offset) {

        //Date tempDate = getDateObj(date, "[/]");
        Date tempDate = null;
        if(date.indexOf("/")>0){
            tempDate = getDateObj(date, "[/]");
            tempDate = getDateAdd(tempDate, offset);
            return getFormatDateTime(tempDate, "yyyy-MM-dd");
        }
        if(date.indexOf("-")>0){
            tempDate = getDateObj(date, "[-]");
            tempDate = getDateAdd(tempDate, offset);
            return getFormatDateTime(tempDate, "yyyy-MM-dd");
        }
        return "";
    }

    /**
     * <p>取得指定分隔符分割的年月日的日期对象</p>
     * @param argsDate 格式为"yyyy-MM-dd"
     * @param split 时间格式的间隔符，例如“-”，“/”，要和时间一致起来。
     * @return 一个java.util.Date()类型的对象
     */
    public static Date getDateObj(String argsDate, String split) {
        String[] temp = argsDate.split(split);
        int year = new Integer(temp[0]).intValue();
        int month = new Integer(temp[1]).intValue();
        int day = new Integer(temp[2]).intValue();
        return getDateObj(year, month, day);
    }

    /**
     * <p>取得指定年月日的日期对象</p>
     * @param year 年
     * @param month 月注意是从1到12
     * @param day 日
     * @return 一个java.util.Date()类型的对象
     */
    public static Date getDateObj(int year, int month, int day) {
        Calendar c = new GregorianCalendar();
        c.set(year, month - 1, day);
        return c.getTime();
    }

    /**
     * <p>取得给定日期加上一定天数后的日期对象</p>
     * @param date 给定的日期对象
     * @param amount 需要添加的天数，如果是向前的天数，使用负数就可以.
     * @return Date 加上一定天数以后的Date对象
     */
    public static Date getDateAdd(Date date, int amount) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(GregorianCalendar.DATE, amount);
        return cal.getTime();
    }

    /**
     * <p>根据给定的格式与时间(Date类型的)，返回时间字符串。最为通用</p>
     * @param date 指定的日期
     * @param format 日期格式字符串
     * @return String 指定格式的日期字符串.
     */
    public static String getFormatDateTime(Date date, String format) {
        //Date date = new Date();
        //System.out.println(getFormatDateTime(date, "yyyy-MM-dd HH:mm:ss"));
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static void main(String[] args) {
        //  System.out.println(getIntervalDays("2010-10-11", "2010-12-11"));
        System.out.println(getDateOffset("2010-10-10", 30));
    }

}
