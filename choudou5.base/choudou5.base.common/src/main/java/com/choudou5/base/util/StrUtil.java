package com.choudou5.base.util;


public class StrUtil extends cn.hutool.core.util.StrUtil {


    public static boolean contains(String str, String searchStr) {
        if (str == null || searchStr == null) {
            return false;
        }
        return str.indexOf(searchStr) >= 0;
    }
}
