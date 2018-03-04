package com.choudou5.base.util;


import cn.hutool.core.date.DatePattern;

import java.util.Date;

/**
 * 日期工具类
 */
public class DateUtil extends cn.hutool.core.date.DateUtil{

    /**
     * 转 搜索日期格式 字符串
     * @param date
     * @return
     */
    public static String toSearchDateStr(Date date){
        return format(date, "yyyy-MM-dd'T'HH:mm:ss'Z'");
    }

    /**
     * 返回 昨天
     * @return yyyyMMdd
     */
    public static String yesterdayShort(){
        return format(yesterday(), DatePattern.PURE_DATE_FORMAT);
    }

    /**
     * 返回 当前时间 完整
     * @return yyyyMMddHHmmssSSS
     */
    public static String nowFull(){
        return format(new Date(), DatePattern.PURE_DATETIME_MS_PATTERN);
    }

    public static Date todayStart(){
       return beginOfDay(new Date());
    }

    public static Date todayEnd(){
        return endOfDay(new Date());
    }

    public static Date weekStart(){
        return beginOfWeek(new Date());
    }

    public static Date weekEnd(){
        return endOfWeek(new Date());
    }

    public static Date lastWeekStart(){
        return beginOfWeek(lastWeek());
    }

    public static Date lastWeekEnd(){
        return endOfWeek(lastWeek());
    }

    public static Date monthStart(){
        return beginOfMonth(new Date());
    }

    public static Date monthEnd(){
        return endOfMonth(new Date());
    }

    public static Date lastMonthStart(){
        return beginOfMonth(lastMonth());
    }

    public static Date lastMonthEnd(){
        return endOfMonth(lastMonth());
    }


    public static void main(String[] args) {
        System.out.println("------- today-------------");
        System.out.println(now());
        System.out.println(today());
        System.out.println(todayStart());
        System.out.println(todayEnd());
        System.out.println("------- week-------------");
        System.out.println(yesterday());
        System.out.println(yesterdayShort());

        System.out.println("------- week-------------");
        System.out.println(weekStart());
        System.out.println(weekEnd());
        System.out.println("--------last week-------------");
        System.out.println(lastWeek());
        System.out.println(lastWeekStart());
        System.out.println(lastWeekEnd());
        System.out.println("--------last month-------------");
        System.out.println(lastMonth());
        System.out.println(lastMonthStart());
        System.out.println(lastMonthEnd());
        System.out.println(toSearchDateStr(new Date()));
    }

}
