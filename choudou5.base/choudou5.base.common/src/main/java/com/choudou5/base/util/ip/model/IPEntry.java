package com.choudou5.base.util.ip.model;

import java.io.Serializable;

/**
 * @Name：IPEntry ip实体
 * @Author：xuhaowen
 * @Date：2018-06-02
 */
public class IPEntry implements Serializable {

    private String beginIp;
    private String endIp;
    private String country;
    private String area;

    public IPEntry() {
    }

    public IPEntry(String country, String area) {
        this.country = country;
        this.area = area;
    }


    public String getBeginIp() {
        return beginIp;
    }

    public void setBeginIp(String beginIp) {
        this.beginIp = beginIp;
    }

    public String getEndIp() {
        return endIp;
    }

    public void setEndIp(String endIp) {
        this.endIp = endIp;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
