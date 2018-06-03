package com.choudou5.base.util.map;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.choudou5.base.util.ArrayUtil;
import com.choudou5.base.util.PrintUtil;
import com.choudou5.base.util.StrUtil;
import com.choudou5.base.util.map.model.BaiduArea;
import com.choudou5.base.util.map.model.Geo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * @Name: 百度地图 工具类
 * @Author: xuhaowen
 * @Date: 2018-01-15
 */
public class BaiduMapUtil {

    private static Logger log = LoggerFactory.getLogger(BaiduMapUtil.class);

    /** 百度地理编码 地址*/
    private static final String GEOCODE_URL = "http://api.map.baidu.com/geocoder/v2/";

    /** 百度地图 经纬度-->获取省市区信息 接口 */
    private static final String MAP_API_GEO_BY_GEO = "http://api.map.baidu.com/geocoder/v2/?ak=%s&callback=renderReverse&location=%s&batch=%s&output=json&pois=0&extensions_poi=null";

    //http://lbsyun.baidu.com/index.php?title=webapi/guide/webservice-geocoding

    public static void main(String[] args) throws Exception {
        String appKey = "A1LU7iHS0avqQwPLAxbhKn0UYSQCuRVH";

        Geo geo = getLngLat(appKey, "上海市");
        PrintUtil.println(geo);

        List<BaiduArea> areas = getAreas(appKey, geo);
        PrintUtil.println(areas);
    }

    /**
     * 获得 经纬度
     * @param appKey 授权key
     * @return
     */
    public static Geo getLngLat(String appKey, String address){
        //json&&json({"status":0,"result":{"location":{"lng":116.52169489108084,"lat":39.95895316640668},"precise":0,"confidence":16,"level":"区县"}})
        String jsonStr = HttpUtil.get(GEOCODE_URL + "?address=" + address + "&output=json&ak=" + appKey);
        if(StrUtil.isNotBlank(jsonStr)){
            JSONObject obj = JSON.parseObject(jsonStr);
            int status = obj.getIntValue("status");
            if(status != 0){
                throw new IllegalArgumentException("result status:"+status);
            }
            return obj.getJSONObject("result").getJSONObject("location").toJavaObject(Geo.class);
        }
        return null;
    }


    /**
     * 批量获取 百度地区
     * @param appKey
     * @param geos
     * @return
     */
    public static List<BaiduArea> getAreas(String appKey, Geo... geos){
        int len = geos.length;
        if(len == 0){
            throw new IllegalArgumentException("经纬度地址 不能为空.");
        }
        if(len > 20){
            throw new IllegalArgumentException("批量最多解析20个经纬度地址.");
        }
        String batch = "false";
        String geoStr = null;
        if(len >= 1){
            batch = "true";
            try {
                geoStr = URLEncoder.encode(ArrayUtil.join(geos, "|"), "utf-8");
            } catch (UnsupportedEncodingException e) {
                log.error("URLEncoder geos 失败.", e);
            }
        }else{
            geoStr = geos[0].toString();
        }
        String url = String.format(MAP_API_GEO_BY_GEO, appKey, geoStr, batch);
        String resp = HttpUtil.get(url);
        if(StrUtil.isNotBlank(resp)){
            String prefix = "renderReverse&&renderReverse(";
            if(resp.startsWith(prefix)){
                try {
                    String jsonStr = StrUtil.sub(resp, prefix.length(), resp.length() - 1);
                    JSONObject jsonObject = JSON.parseObject(jsonStr);
                    if(jsonObject.getIntValue("status") == 0){
                        JSONArray areas = jsonObject.getJSONArray("areas");
                        List<BaiduArea> list = areas.toJavaList(BaiduArea.class);
                        return list;
                    }
                } catch (Exception e) {
                    log.error(geoStr+" 解析成经纬度失败.", e);
                }
            }
            log.info(geoStr + " 解析经纬度失败，resp:" + resp);
        }
        return null;
    }
}
