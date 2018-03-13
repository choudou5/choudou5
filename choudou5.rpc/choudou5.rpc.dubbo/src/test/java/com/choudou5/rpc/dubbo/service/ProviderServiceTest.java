package com.choudou5.rpc.dubbo.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

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
}
