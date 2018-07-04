package com.choudou5.log.admin;

import com.choudou5.base.util.JsonUtil;
import com.choudou5.log.admin.util.LogAdminHelper;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Name：本地日志管理
 * @Author：xuhaowen
 * @Date：2018-03-27
 */
@Controller
@Scope("prototype")
@RequestMapping("/log/logAdmin")
public class LogAdminController {


    /**
     * 系统日志管理-列表
     * @param req
     * @param model
     * @return
     */
    @RequestMapping(value = {"ajaxList"})
    @ResponseBody
    public Map<String, Object> ajaxList(HttpServletRequest req, Model model) {
        Map result = LogAdminHelper.getLogListInfo();
        result.put("watcher", "Log4j (org.slf4j.impl.Log4jLoggerFactory)");
        return result;
    }

    /**
     * 系统日志管理-设置
     * @param name
     * @param level
     * @param req
     * @return
     */
    @RequestMapping(value = {"setting"})
    @ResponseBody
    public String setting(String name, String level, HttpServletRequest req) {
        LogAdminHelper.setLevel(name, level);
        return returnJson(200, null, "日志级别-更改成功");
    }


    /**
     * 系统日志-打印
     * @param req
     * @param model
     * @return
     */
    @RequestMapping(value = {"ajaxPrintLog"})
    @ResponseBody
    public Map<String, Object> ajaxPrintLog(HttpServletRequest req, Model model) {
        String sinceStr = req.getParameter("since");
        Long time = Long.parseLong(sinceStr);
        return LogAdminHelper.getLogHistory(time);
    }

    /*------------------------commmon-----------------------------------------*/
    protected String returnJson(int statusCode, Object data, String message){
        Map<String, Object> result = new HashMap<String, Object>(6);
        result.put("statusCode", statusCode);
        result.put("data", data);
        result.put("message", message);
        return JsonUtil.toStr(result);
    }

}
