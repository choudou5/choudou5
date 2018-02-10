package com.choudou5.base.util;


import cn.hutool.core.date.DatePattern;

import java.util.Date;

public class DateUtil extends cn.hutool.core.date.DateUtil{

    /**
     * 返回 昨天
     * @return yyyyMMdd
     */
    public static String getYesterDayShort(){
        return format(DateUtil.yesterday(), DatePattern.PURE_DATE_FORMAT);
    }

    /**
     * 返回 当前时间 完整
     * @return yyyyMMddHHmmssSSS
     */
    public static String nowFull(){
        return format(new Date(), DatePattern.PURE_DATETIME_MS_PATTERN);
    }

}
