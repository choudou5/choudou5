package com.choudou5.base.bean;

import java.util.*;

/**
 * @Name：集合构建器
 * @Author：xuhaowen
 * @Date：2018-03-11
 */
public class ListBuilder {

    private static final int DEFAULT_CAPACITY = 5;

    private List list;

    public static MapBuilder create() {
        return new MapBuilder(DEFAULT_CAPACITY);
    }

    public ListBuilder() {
        this(DEFAULT_CAPACITY);
    }

    public ListBuilder(int initialCapacity) {
        list = new ArrayList<>(initialCapacity);
    }

    public ListBuilder(Class mapType) {
        this(mapType, DEFAULT_CAPACITY);
    }

    public ListBuilder(Class mapType, int initialCapacity) {
        if(mapType == LinkedList.class){
            list = new LinkedList<>();
        }else  if(mapType == Vector.class){
            list = new Vector<>(initialCapacity);
        }else if(mapType == Stack.class){
            list = new Stack<>();
        }else{
            list = new ArrayList<>(initialCapacity);
        }
    }

    public ListBuilder a(String value){
        list.add(value);
        return this;
    }

    public ListBuilder a(Object value){
        list.add(value);
        return this;
    }

    public List list(){
        return list;
    }
}
