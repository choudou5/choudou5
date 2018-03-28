package com.choudou5.log.admin.logback;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.core.filter.AbstractMatcherFilter;
import ch.qos.logback.core.spi.FilterReply;


public class CustomEventFilter<E> extends AbstractMatcherFilter<ILoggingEvent> {

    Level level;
    private static com.choudou5.log.admin.LogWatcher<LoggingEvent> watcher;
    private boolean watcherEnabled = true;

    public CustomEventFilter() {
    }

    public static void setWatcher(com.choudou5.log.admin.LogWatcher<LoggingEvent> watcher) {
        CustomEventFilter.watcher = watcher;
    }

    @Override
    public FilterReply decide(ILoggingEvent event) {
        if (!isStarted()) {
            return FilterReply.NEUTRAL;
        }

        if(watcherEnabled) {
            LoggingEvent e = (LoggingEvent) event;
            watcher.add(e, e.getTimeStamp());
            //推送到 日志引擎
            //do something...
        }
        if (event.getLevel().equals(level)) {
            return onMatch;
        } else {
            return onMismatch;
        }
    }

    public void start() {
        if (this.level != null) {
            super.start();
        }
    }

    @Override
    public void stop() {
        super.stop();
        watcher.reset();
    }

    public void setLevel(Level level) {
        this.level = level;
    }
    public void setWatcherEnabled(boolean watcherEnabled) {
        this.watcherEnabled = watcherEnabled;
    }
}
