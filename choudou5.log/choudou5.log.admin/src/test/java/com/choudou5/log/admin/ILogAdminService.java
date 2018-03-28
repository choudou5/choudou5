package com.choudou5.log.admin;


import java.util.Map;

public interface ILogAdminService {

    void setLogLevel(String level);

    Map getLogHi(long time);

}
