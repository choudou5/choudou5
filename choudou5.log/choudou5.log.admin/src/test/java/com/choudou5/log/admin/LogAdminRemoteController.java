/*
* Powered By [javasaasx]
* Web Site: http://www.javasaas.top
* Github Code: https://github.com/choudou5
* License：MIT
* Since 2018 - 2020
*/
package com.choudou5.log.admin;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.alibaba.dubbo.rpc.ProxyFactory;
import com.alibaba.dubbo.rpc.protocol.dubbo.DubboInvoker;
import com.alibaba.dubbo.rpc.protocol.dubbo.DubboProtocol;
import com.alibaba.fastjson.JSON;
import com.choudou5.base.util.AssertUtil;
import com.choudou5.base.util.CollUtil;
import com.choudou5.base.util.StrUtil;
import com.choudou5.log.admin.service.ILogAdminService;
import com.choudou5.rpc.dubbo.domain.ProviderEntity;
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
 * @Name：远程日志管理 Controller
 * @Author：xuhaowen
 * @Date：2018-02-28
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "${adminPath}/framework/logAdminRemote")
public class LogAdminRemoteController  {

    private static DubboProtocol DUBBO_PROTOCOL = DubboProtocol.getDubboProtocol();
    private static ProxyFactory proxy = ExtensionLoader.getExtensionLoader(ProxyFactory.class).getAdaptiveExtension();
    private static String logServiceClass = "com.lianj.framework.logs.admin.service.ILogAdminService:1.0.0";

    @Autowired
    private ProviderService providerService;


    /**
     * @param page 页面
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = {""})
    public String page(String page, HttpServletRequest request, Model model) {
        if(StrUtil.isBlank(page))
            page = "List";
        model.addAttribute("type", request.getParameter("type"));
        model.addAttribute("domain", request.getParameter("domain"));
        model.addAttribute("application", request.getParameter("application"));
        return "modules/framework/logAdmin"+page;
    }

    /**
     * 服务列表
     * @param req
     * @return
     */
    @RequestMapping(value = {"serviceList"})
    public String serviceList(HttpServletRequest req, Model model) {
        List<ProviderEntity> list= providerService.findByService(logServiceClass);
        model.addAttribute("list", list);
        return "modules/framework/logAdminServiceList";
    }

    /**
     * 日志树
     * @param req
     * @param model
     * @return
     */
    @RequestMapping(value = {"ajaxLogTree"})
    @ResponseBody
    public Map<String, Object> ajaxLogTree(String domain, HttpServletRequest req, Model model) {
        Map result = new HashMap();
        URL url = getServiceURL(domain);
        if (url != null) {
            DubboInvoker<?> invoker = (DubboInvoker<?>) DUBBO_PROTOCOL.refer(ILogAdminService.class, url);
            if (invoker.isAvailable()) {
                ILogAdminService service = (ILogAdminService) proxy.getProxy(invoker);
                AssertUtil.isNotNull(service, "服务找不到");
                result = service.getLogListInfo();
            }
        }
        result.put("watcher", "Logback (ch.qos.logback.classic.LoggerContext)");
        return result;
    }


    /**
     * 设置
     * @param req
     * @return
     */
    @RequestMapping(value = {"setting"})
    @ResponseBody
    public String setting(String domain, String name, String level, HttpServletRequest req) {
        URL url = getServiceURL(domain);
        if (url != null) {
            DubboInvoker<?> invoker = (DubboInvoker<?>) DUBBO_PROTOCOL.refer(ILogAdminService.class, url);
            if (invoker.isAvailable()) {
                ILogAdminService service = (ILogAdminService) proxy.getProxy(invoker);
                AssertUtil.isNotNull(service, "服务找不到");
                service.setLogLevel(name, level);
            }
            return returnOk("日志级别-更改成功");
        }
        return returnFail("日志级别-失败");
    }

    /**
     * 日志-打印
     * @param req
     * @param model
     * @return
     */
    @RequestMapping(value = {"ajaxPrintLog"})
    @ResponseBody
    public Map<String, Object> ajaxPrintLog(String domain, Long since, HttpServletRequest req, Model model) {
        Map result = null;
        URL url = getServiceURL(domain);
        if (url != null) {
            DubboInvoker<?> invoker = (DubboInvoker<?>) DUBBO_PROTOCOL.refer(ILogAdminService.class, url);
            if (invoker.isAvailable()) {
                ILogAdminService service = (ILogAdminService) proxy.getProxy(invoker);
                AssertUtil.isNotNull(service, "服务找不到");
                result = service.getLogHi(since);
            }
        }
        return result;
    }


    private URL getServiceURL(String domain){
        List<URL> urls = providerService.findURLByService(logServiceClass);
        if(CollUtil.isNotEmpty(urls)){
            String dm = null;
            for (URL url : urls) {
                dm = url.getHost()+":"+url.getPort();
                if(dm.equals(domain)){
                    return url;
                }
            }
        }
        return null;
    }

    protected String returnOk(String message){
        return returnJson(200, null, message);
    }

    protected String returnFail(String message){
        return returnJson(500, null, message);
    }

    protected String returnJson(int statusCode, Object obj, String message){
        Map<String, Object> result = new HashMap<String, Object>(3);
        result.put("statusCode", statusCode);
        result.put("obj", obj);
        result.put("message", message);
        return JSON.toJSONString(result);
    }
}
