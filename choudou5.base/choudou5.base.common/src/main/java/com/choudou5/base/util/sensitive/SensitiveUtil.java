package com.choudou5.base.util.sensitive;


import cn.hutool.core.thread.ThreadUtil;
import com.choudou5.base.exception.SysException;
import com.choudou5.base.helper.CacheHelper;
import com.choudou5.base.util.LogPrintUtil;
import com.choudou5.base.util.StrUtil;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * 敏感词工具类
 * @author Looly
 * @author2  xuhaowen
 */
public class SensitiveUtil {

    public static final char DEFAULT_SEPARATOR = StrUtil.C_COMMA;

    public static final String CACHE_KEY = "cache_sensitiveTree";

    // get/set
    private static CacheHelper cache;
    public CacheHelper getCache() {
        return cache;
    }
    public void setCache(CacheHelper cache) {
        SensitiveUtil.cache = cache;
    }
    protected static void staticSetCache(CacheHelper cache) {
        SensitiveUtil.cache = cache;
    }

    private static WordTree getCacheWordTree() {
        if(cache == null){
            throw new SysException("请为 SensitiveUtil类注入 cache");
        }
        Object obj = cache.getObj(CACHE_KEY);
        if(obj == null){
            throw new SysException("SensitiveUtil类：未初始化敏感词库！");
        }else{
            return (WordTree)obj;
        }
    }

    private static void clearWordTree() {
        cache.put(CACHE_KEY, null);
    }

    /**
     * 初始化敏感词树
     * @param isAsync 是否异步初始化
     * @param sensitiveWords 敏感词列表
     */
    public static void init(final Collection<String> sensitiveWords, boolean isAsync){
        if(isAsync){
            LogPrintUtil.debug("Sensitive isAsync init");
            ThreadUtil.execAsync(new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    init(sensitiveWords);
                    return true;
                }

            });
        }else{
            init(sensitiveWords);
        }
    }

    /**
     * 初始化敏感词树
     * @param sensitiveWords 敏感词列表
     */
    public static void init(Collection<String> sensitiveWords){
        LogPrintUtil.debug("Sensitive init start");
        clearWordTree();
        WordTree tree = new WordTree();
        tree.addWords(sensitiveWords);
        cache.put(CACHE_KEY, tree);
        LogPrintUtil.debug("Sensitive init finished, sensitives: {}", sensitiveWords.size());
    }

    /**
     * 初始化敏感词树
     * @param sensitiveWords 敏感词列表组成的字符串
     * @param isAsync 是否异步初始化
     * @param separator 分隔符
     */
    public static void init(String sensitiveWords, char separator, boolean isAsync){
        if(StrUtil.isNotBlank(sensitiveWords)){
            init(StrUtil.split(sensitiveWords, separator), isAsync);
        }
    }

    /**
     * 初始化敏感词树，使用逗号分隔每个单词
     * @param sensitiveWords 敏感词列表组成的字符串
     * @param isAsync 是否异步初始化
     */
    public static void init(String sensitiveWords, boolean isAsync){
        init(sensitiveWords, DEFAULT_SEPARATOR, isAsync);
    }

    /**
     * 是否包含敏感词
     * @param text 文本
     * @return 是否包含
     */
    public static boolean exist(String text){
        return getCacheWordTree().isMatch(text);
    }

    /**
     * 查找敏感词，返回找到的第一个敏感词
     * @param text 文本
     * @return 敏感词
     */
    public static String getFirst(String text){
        return getCacheWordTree().match(text);
    }

    /**
     * 查找敏感词，返回找到的所有敏感词
     * @param text 文本
     * @return 敏感词
     */
    public static List<String> getAll(String text){
        return getCacheWordTree().matchAll(text);
    }

    /**
     * 查找敏感词，返回找到的所有敏感词<br>
     * 密集匹配原则：假如关键词有 ab,b，文本是abab，将匹配 [ab,b,ab]<br>
     * 贪婪匹配（最长匹配）原则：假如关键字a,ab，最长匹配将匹配[a, ab]
     *
     * @param text 文本
     * @param isDensityMatch 是否使用密集匹配原则
     * @param isGreedMatch 是否使用贪婪匹配（最长匹配）原则
     * @return 敏感词
     */
    public static List<String> getAll(String text, boolean isDensityMatch, boolean isGreedMatch){
        return getCacheWordTree().matchAll(text, -1, isDensityMatch, isGreedMatch);
    }

}