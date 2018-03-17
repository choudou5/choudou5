package com.choudou5.base.util;

import java.util.ResourceBundle;

public class PropUtil {

	private static ResourceBundle resource = null;

	private PropUtil() {}
	
	/** 
     * 通过key值获取文件的String类型数据 
     * @param key 
     * @return 
     */  
    public static String getStrFrom(String propFilePath, String key){
    	if(resource == null)
    		resource = ResourceBundle.getBundle(propFilePath);
        return resource.getString(key);  
    }
    
    /** 
     * 通过key值获取文件的String类型数据 
     * @param key 
     * @return 
     */  
    public static String getStr(String key){
    	if(resource == null)
    		resource = ResourceBundle.getBundle("system");
        return resource.getString(key);  
    }
    
    /** 
     * 通过key值获取文件的String类型数据 
     * @param key 
     * @return 
     */  
    public static String getStr(String key, String def){
    	String value = getStr(key);
        return value==null?def:value;  
    }
    
    /** 
     * 通过key值获取文件的int类型数据 
     * @param key 
     * @return 
     */  
    public static Integer getInt(String key){
        return Integer.parseInt(getStr(key));
    }
    
    /** 
     * 通过key值获取文件的int类型数据 
     * @param key 
     * @return 
     */  
    public static Integer getInt(String key, int def){
        try {
			return Integer.parseInt(getStr(key));
		} catch (Exception e) {
			return def;
		}  
    }
    
    
    /** 
     * 通过key值获取文件的double类型数据 
     * @param key 
     * @return 
     */  
    public static Double getDouble(String key){  
        return Double.parseDouble(getStr(key));
    }
    
    /** 
     * 通过key值获取文件的boolean类型数据 
     * @param key 
     * @return 
     */  
    public static Boolean getBool(String key){
        return Boolean.parseBoolean(getStr(key));
    }  
    
    public static Boolean getBool(String key, boolean def){
        try {
			return Boolean.parseBoolean(getStr(key));
		} catch (Exception e) {
			return def;
		}
    }  

}
