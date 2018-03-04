package com.choudou5.base.bean;

import com.choudou5.base.util.DateUtil;
import com.choudou5.base.util.StrUtil;

import java.io.Serializable;
import java.util.Date;

/**
 * @Name：范围 Bean
 * @Author：xuhaowen
 * @Date：2018-01-13
 */
public class BetweenBean implements Serializable {

    private Object begin;
    private Object end;

    public BetweenBean() {
    }

    public BetweenBean(Object begin, Object end) {
        this.begin = begin;
        this.end = end;
    }

    public Object getBegin() {
        return begin;
    }

    public void setBegin(Object begin) {
        this.begin = begin;
    }

    public Object getEnd() {
        return end;
    }

    public void setEnd(Object end) {
        this.end = end;
    }

    public String toSql() {
        return toSql(null);
    }

    public String toSql(String pattern) {
        int type = 0;
        if(begin != null)
            type += 1;
        if(end != null)
            type += 2;

        if(type==1){
            return " >= "+getStrVal(begin, pattern);
        }else if(type==2){
            return " <= "+getStrVal(end, pattern);
        }else{
            return " BETWEEN "+getStrVal(begin, pattern)+" AND "+getStrVal(end, pattern);
        }
    }

    private String getStrVal(Object objVal, String pattern){
        if(objVal instanceof String){
            return "\""+objVal.toString()+"\"";
        }if(objVal instanceof Date){
            if(StrUtil.isBlank(pattern))
                pattern = "yyyy-MM-dd HH:mm:ss";
            return "\""+DateUtil.format((Date)objVal, pattern)+"\"";
        }else{
            return objVal.toString();
        }
    }

}
