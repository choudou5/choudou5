package com.choudou5.base.util.ip.model;

import java.io.Serializable;

/**
 * @Name：TaoBaoIpEntry 说明
 * @Author：xuhaowen
 * @Date：2018-06-02
 */
public class TaoBaoIpEntry implements Serializable {

    private String ip;
    private String country;
    private String region;
    private String city;
    private String area;
    /** 运营商 */
    private String isp;

    private String region_id;
    private String city_id;
    private String area_id;
    private String isp_id;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        if("XX".equals(region))
            return null;
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        if("XX".equals(city))
            return null;
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        if("XX".equals(area))
            return null;
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getIsp() {
        if("XX".equals(isp))
            return null;
        return isp;
    }

    public void setIsp(String isp) {
        this.isp = isp;
    }

    public String getRegion_id() {
        return region_id;
    }

    public void setRegion_id(String region_id) {
        this.region_id = region_id;
    }

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }

    public String getArea_id() {
        return area_id;
    }

    public void setArea_id(String area_id) {
        this.area_id = area_id;
    }

    public String getIsp_id() {
        return isp_id;
    }

    public void setIsp_id(String isp_id) {
        this.isp_id = isp_id;
    }
}
