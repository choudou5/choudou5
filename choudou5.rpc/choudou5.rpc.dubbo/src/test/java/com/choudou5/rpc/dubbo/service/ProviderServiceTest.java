package com.choudou5.rpc.dubbo.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

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

        print(providerService.findByService("com.lianj.payment.center.invoice.service.IPayInvoiceDataService:1.0.0"));

        print(providerService.findAddressesByService("com.lianj.payment.center.invoice.service.IPayInvoiceDataService:1.0.0"));
    }
/*

    @Test
    public void getService(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-dubbo.xml");
        UserChannelService service = getService(applicationContext, "192.168.36.225", 21880, UserChannelService.class);
        AssertUtil.isNotNull(service, "服务找不到");
        System.out.println(11111);
        print(service.selectAll());
        System.out.println(22222);
    }

    private UserChannelService getService(ApplicationContext applicationContext, String host, int port, Class serviceClass) {
        String url = "dubbo://"+host+":"+port+"/"+serviceClass.getName();//更改不同的Dubbo服务暴露的ip地址&端口
        ReferenceBean<UserChannelService> referenceBean = new ReferenceBean<UserChannelService>();
        referenceBean.setApplicationContext(applicationContext);
        referenceBean.setVersion("1.0.0");
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
*/

}
