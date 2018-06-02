package com.choudou5.base.util.ip.model;

import java.io.Serializable;

/**
 * @Name：IPLocation 说明
 * @Author：xuhaowen
 * @Date：2018-06-02
 */
public class IPLocation implements Serializable {

    public String country;
    public String area;

    public IPLocation()
    {
        country = area = "";
    }

    public IPLocation getCopy()
    {
        IPLocation ret = new IPLocation();
        ret.country = country;
        ret.area = area;
        return ret;
    }

    public String getArea()
    {
        return area;
    }

    public void setArea( String area )
    {
        this.area = area;
    }

    public String getCountry()
    {
        return country;
    }

    public void setCountry( String country )
    {
        this.country = country;
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        return "country:" + country + " ,area:" + area;
    }
}
