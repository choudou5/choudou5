package com.choudou5.file.store;

import com.choudou5.file.store.oss.OssHelper;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

/**
 * @Name：Oss助手
 * @Author：xuhaowen
 * @Date：2018-01-31
 * @Site：http://solrhome.com
 * @License：MIT
 * @Copyright：xuhaowende@sina.cn (@Copyright 2018-2020)
 */
public class OssHelperTest extends BaseApiTest {

    private OssHelper helper;

    @Before
    public void init() {
        String accessKeyId = "xx";
        String accessKeySecret = "ggg";
        String bucketName = "ttt";
        helper = new OssHelper(accessKeyId, accessKeySecret, bucketName);
    }

    @Test
    public void upload() {
        helper.upload("1111", new File(""));
    }

    @Test
    public void download() {
        helper.download("1111", "d:");
    }

    @Test
    public void getSignAccUrl() {
        String url = helper.getSignAccUrl("1111", 3600);
        log(url);
    }


}
