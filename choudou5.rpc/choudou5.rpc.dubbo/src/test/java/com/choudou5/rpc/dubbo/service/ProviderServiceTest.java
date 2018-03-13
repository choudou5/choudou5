package com.choudou5.rpc.dubbo.service;

import com.alibaba.dubbo.config.spring.ReferenceBean;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.util.List;

/**
 * @Name：ProviderServiceTest 说明
 * @Author：xuhaowen
 * @Date：2018-03-14
 */
public class ProviderServiceTest extends BaseTest{


    @Autowired
    private ProviderService providerService;


    @Test
    public void findAddresses(){

        print(providerService.findAll());
    }

    @Test
    public void getService(){
        RemoteLogService service = getService(null, "123", 123, RemoteLogService.class);
        service.findList(10);
    }

    private RemoteLogService getService(ApplicationContext applicationContext, String host, int port, Class serviceClass) {
        String url = "dubbo://"+host+":"+port+"/"+serviceClass.getName();//更改不同的Dubbo服务暴露的ip地址&端口
        ReferenceBean<RemoteLogService> referenceBean = new ReferenceBean<RemoteLogService>();
        referenceBean.setApplicationContext(applicationContext);
        referenceBean.setInterface(serviceClass);
        referenceBean.setUrl(url);
        try {
            referenceBean.afterPropertiesSet();
            return referenceBean.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    interface RemoteLogService {
        List<String> findList(int top);
    }
}
