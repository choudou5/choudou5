package com.choudou5.base.util.map.model;

import java.io.Serializable;

/**
 * @Name：BaiduArea 说明
 * @Author：xuhaowen
 * @Date：2018-06-02
 */
public class BaiduArea implements Serializable {

    private String country;    //国家
    private String country_code;    //国家编码
    private String province;    //省份
    private String city;    //城市
    private String district;    //区县
    private String adcode;    //行政区划代码
    private Geo geo;   //经纬度

    public BaiduArea() {
    }

    public BaiduArea(String country, String country_code, String province, String city, String district, String adcode, Geo geo) {
        this.country = country;
        this.country_code = country_code;
        this.province = province;
        this.city = city;
        this.district = district;
        this.adcode = adcode;
        this.geo = geo;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAdcode() {
        return adcode;
    }

    public void setAdcode(String adcode) {
        this.adcode = adcode;
    }

    public Geo getGeo() {
        return geo;
    }

    public void setGeo(Geo geo) {
        this.geo = geo;
    }

    @Override
    public String toString() {
        return "BaiduArea{" +
                "country='" + country + '\'' +
                ", country_code='" + country_code + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                ", adcode='" + adcode + '\'' +
                ", geo=" + geo +
                '}';
    }
}
