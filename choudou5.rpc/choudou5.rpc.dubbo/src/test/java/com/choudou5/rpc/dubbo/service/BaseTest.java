package com.choudou5.rpc.dubbo.service;

import com.choudou5.base.util.JsonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Name：BaseTest 说明
 * @Author：xuhaowen
 * @Date：2018-03-14
 */

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( {"classpath:spring-dubbo.xml"} )
public class BaseTest {

    @Test
    public void testInit() {
        System.out.println("测试");
    }

    protected void print(Object object){
        if(object != null){
            JsonUtil.toStr(object);
        }
    }

}
