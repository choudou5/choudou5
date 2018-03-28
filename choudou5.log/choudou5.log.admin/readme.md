# 日志管理插件
    日志管理

### 使用
    1、demo参考 test 的controller
    
    2、修改 logback.xml filter类
    <filter class="com.choudou5.log.admin.logback.CustomEventFilter">
    
    3、初始化 日志观察者
        LogWatcher watcher = new LogbackWatcher();
        watcher.registerListener(new ListenerConfig(50));//保留50条最新日志
        CustomEventFilter.setWatcher(watcher);
        LogAdminHelper.setWatcher(watcher);
        
    4、停止日志监控
        LogAdminHelper.stop();
    
### 效果图
    ![图片说明](https://choudoufu-hd2.oss-cn-shanghai.aliyuncs.com/readme/choudou5/log/log-level.jpg "设置")
    ![图片说明](https://choudoufu-hd2.oss-cn-shanghai.aliyuncs.com/readme/choudou5/log/log-console.jpg "控制台")



