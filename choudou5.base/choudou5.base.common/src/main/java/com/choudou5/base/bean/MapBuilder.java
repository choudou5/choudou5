package com.choudou5.base.bean;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Name：Map构造器
 * @Author：xuhaowen
 * @Date：2018-03-11
 */
public class MapBuilder {

    private static final int DEFAULT_CAPACITY = 8;

    private Map map;


    public static MapBuilder create() {
        return new MapBuilder(DEFAULT_CAPACITY);
    }

    public MapBuilder() {
        this(DEFAULT_CAPACITY);
    }

    public MapBuilder(int initialCapacity) {
        map = new HashMap<>(initialCapacity);
    }

    public MapBuilder(Class mapType) {
        this(mapType, DEFAULT_CAPACITY);
    }

    public MapBuilder(Class mapType, int initialCapacity) {
        if(mapType == HashMap.class){
            map = new HashMap<>(initialCapacity);
        }else  if(mapType == TreeMap.class){
            map = new TreeMap<>();
        }else if(mapType == LinkedHashMap.class){
            map = new LinkedHashMap<>(initialCapacity);
        }else if(mapType == ConcurrentHashMap.class){
            map = new ConcurrentHashMap<>(initialCapacity);
        }else if(mapType == WeakHashMap.class){
            map = new WeakHashMap<>(initialCapacity);
        }else if(mapType == IdentityHashMap.class){
            map = new IdentityHashMap<>(initialCapacity);
        }else if(mapType == Hashtable.class){
            map = new Hashtable<>(initialCapacity);
        }else{
            map = new HashMap<>(initialCapacity);
        }
    }

    public MapBuilder p(Object key, Object value){
        map.put(key, value);
        return this;
    }

    public MapBuilder p(String key, Object value){
        map.put(key, value);
        return this;
    }

    public Map map(){
        return map;
    }


    public Map result(boolean success, String message){
        map.clear();
        map.put("success", success);
        map.put("message", message);
        return map;
    }

    public Map result(boolean success, String message, Object content){
        map.clear();
        map.put("success", success);
        map.put("message", message);
        map.put("content", content);
        return map;
    }

}
