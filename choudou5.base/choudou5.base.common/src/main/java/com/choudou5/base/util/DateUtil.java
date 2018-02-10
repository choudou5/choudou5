package com.choudou5.base.util;


import cn.hutool.core.date.DatePattern;

public class DateUtil extends cn.hutool.core.date.DateUtil{

    /**
     * 返回 昨天
     * @return yyyyMMdd
     */
    public static String getYesterDayShort(){
        return format(DateUtil.yesterday(), DatePattern.PURE_DATE_FORMAT);
    }
}
