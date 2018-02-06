package com.choudou5.file.store;

import java.io.File;
import java.io.InputStream;

/**
 * @Name：文件 助手
 * @Author：xuhaowen
 * @Date：2018-01-31
 * @Site：http://solrhome.com
 * @License：MIT
 * @Copyright：xuhaowende@sina.cn (@Copyright 2018-2020)
 */
public interface FileHelper {

    String getSignAccUrl(String key, int timeoutSecond);

    void upload(String key, InputStream is);

    void upload(String key, File file);

    File download(String key, String destPath);

    void delete(String key);


    String getSignAccUrl(String bucketName, String key, int timeoutSecond);

    void upload(String bucketName, String key, InputStream is);

    void upload(String bucketName, String key, File file);

    File download(String bucketName, String key, String destPath);

    void delete(String bucketName, String key);

}
