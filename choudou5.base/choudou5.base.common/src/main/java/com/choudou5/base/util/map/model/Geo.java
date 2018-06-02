package com.choudou5.base.util.map.model;

import java.io.Serializable;

/**
 * @Name: 经纬度Bo
 * @Author: xuhaowen
 * @Date: 2018-01-15
 */
public class Geo implements Serializable {

    private double lng;
    private double lat;

    public Geo() {
    }

    public Geo(double lng, double lat) {
        this.lng = lng;
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public Geo setLng(double lng) {
        this.lng = lng;
        return this;
    }

    public double getLat() {
        return lat;
    }

    public Geo setLat(double lat) {
        this.lat = lat;
        return this;
    }

    @Override
    public String toString() {
        return lat + "," + lng;
    }
}
