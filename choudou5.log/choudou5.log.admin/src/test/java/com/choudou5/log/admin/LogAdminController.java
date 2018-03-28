package com.choudou5.log.admin;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.alibaba.dubbo.rpc.ProxyFactory;
import com.alibaba.dubbo.rpc.protocol.dubbo.DubboInvoker;
import com.alibaba.dubbo.rpc.protocol.dubbo.DubboProtocol;
import com.choudou5.base.util.AssertUtil;
import com.choudou5.base.util.CollUtil;
import com.choudou5.base.util.JsonUtil;
import com.choudou5.log.admin.util.LogAdminHelper;
import com.choudou5.rpc.dubbo.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Name：日志管理 助手
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



    @Autowired
    private ProviderService providerService;

    String logServiceClass = "com.xx.center.log.service.ILogAdminService:1.0.0";

    /**
     * 服务列表
     * @param req
     * @return
     */
    @RequestMapping(value = {"remote/serviceList"})
    @ResponseBody
    public String remoteServiceList(HttpServletRequest req) {
        List<String> urls = providerService.findAddressesByService(logServiceClass);
        return returnJson(200, urls, "获取成功");
    }

    /**
     * 服务列表
     * @param req
     * @return
     */
    @RequestMapping(value = {"remote/setting"})
    @ResponseBody
    public String remoteSetting(String level, HttpServletRequest req) {
        DubboProtocol protocol = DubboProtocol.getDubboProtocol();
        ProxyFactory proxy = ExtensionLoader.getExtensionLoader(ProxyFactory.class).getAdaptiveExtension();
        List<URL> urls = providerService.findURLByService(logServiceClass);
        if (CollUtil.isNotEmpty(urls)) {
            for (URL url : urls) {
                DubboInvoker<?> invoker = (DubboInvoker<?>) protocol.refer(ILogAdminService.class, url);
                if (invoker.isAvailable()) {
                    ILogAdminService service = (ILogAdminService) proxy.getProxy(invoker);
                    AssertUtil.isNotNull(service, "服务找不到");
                    service.setLogLevel(level);
                }
            }
        }
        return returnJson(200, null, "日志级别-更改成功");
    }

    /**
     * 系统日志-打印
     * @param req
     * @param model
     * @return
     */
    @RequestMapping(value = {"remote/ajaxPrintLog"})
    @ResponseBody
    public Map<String, Object> remoteAjaxPrintLog(Long since, String address, HttpServletRequest req, Model model) {
        Map result = null;
        DubboProtocol protocol = DubboProtocol.getDubboProtocol();
        ProxyFactory proxy = ExtensionLoader.getExtensionLoader(ProxyFactory.class).getAdaptiveExtension();
        List<URL> urls = providerService.findURLByService(logServiceClass);
        if (CollUtil.isNotEmpty(urls)) {
            for (URL url : urls) {
                if(address.startsWith(url.getAddress())){
                    DubboInvoker<?> invoker = (DubboInvoker<?>) protocol.refer(ILogAdminService.class, url);
                    if (invoker.isAvailable()) {
                        ILogAdminService service = (ILogAdminService) proxy.getProxy(invoker);
                        AssertUtil.isNotNull(service, "服务找不到");
                        result = service.getLogHi(since);
                        break;
                    }
                }
            }
        }
        return result;
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
