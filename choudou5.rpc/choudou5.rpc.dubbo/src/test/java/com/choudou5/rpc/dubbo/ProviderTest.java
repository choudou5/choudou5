package com.choudou5.rpc.dubbo;

import com.choudou5.base.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Name：ProviderTest 说明
 * @Author：xuhaowen
 * @Date：2018-03-14
 */
public class ProviderTest {

    private static final Logger logger = LoggerFactory.getLogger(ProviderTest.class);
    private static volatile boolean running = true;

    public static void main(final String[] args) {
        try {
            String profile = "development";
            if (args != null && args.length > 0) {
                profile = args[0];
            }
            System.setProperty("spring.profiles.active", profile);
            new ClassPathXmlApplicationContext("spring-dubbo.xml");
            logger.info(DateUtil.now()+ " service server started!");
        } catch (RuntimeException e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            System.exit(1);
        }
//        synchronized (Main.class) {
//            while (running) {
//                try {
//                    Provider.class.wait();
//                } catch (Throwable e) {
//                }
//            }
//        }
        com.alibaba.dubbo.container.Main.main(args);
    }
}
