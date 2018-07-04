package com.choudou5.log.admin.service.impl;


import com.choudou5.log.admin.LogWatcher;
import com.choudou5.log.admin.LoggerInfo;
import com.choudou5.log.admin.logback.CustomEventFilter;
import com.choudou5.log.admin.logback.LogbackWatcher;
import com.choudou5.log.admin.service.ILogAdminService;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class LogAdminServiceImpl implements ILogAdminService {

    private LogWatcher logWatcher;

    public LogAdminServiceImpl() {
        this(50);
    }

    /**
     * 保留50条最新日志
     * @param listenerRow
     */
    public LogAdminServiceImpl(int listenerRow) {
        //注册 日志观察者
        this.logWatcher = new LogbackWatcher(listenerRow);
        CustomEventFilter.setWatcher(logWatcher);
    }

    @Override
    public void setLogLevel(String name, String level) {
        logWatcher.setLogLevel(name, level);
    }

    @Override
    public Map getLogHi(long time) {
        Map result = new HashMap<>(2);
        if(logWatcher==null) {
            return result;
        }
        AtomicBoolean found = new AtomicBoolean(false);
        List<Map> docs = logWatcher.getHistory(time, found);
        if(docs==null) {
            return result;
        }
        else {
            Map info = new HashMap<>();
            if(time>0) {
                info.put("since", time);
                info.put("found", found);
            }else {
                info.put("levels", logWatcher.getAllLevels()); // show for the first request
            }
            info.put("last", logWatcher.getLastEvent());
            info.put("buffer", logWatcher.getHistorySize());

            result.put("info", info);
            result.put("history", docs);
            result.put("watcher", "Logback (ch.qos.logback.classic.LoggerContext)");
        }
        return result;
    }

    @Override
    public Map getLogListInfo() {
        Map result = new HashMap<>(2);
        if(logWatcher==null) {
            return result;
        }
        result.put("levels", logWatcher.getAllLevels());
        List<LoggerInfo> loggers = new ArrayList<>(logWatcher.getAllLoggers());
        Collections.sort(loggers);
        List<LoggerInfo> infos = new ArrayList<>();
        for(LoggerInfo info: loggers) {
            infos.add(info);
        }
        result.put("loggers", infos);
        return result;
    }

    public void close() {
        if(logWatcher==null) {
            return;
        }
        logWatcher.reset();
    }

    public LogWatcher getLogWatcher() {
        return logWatcher;
    }
}


