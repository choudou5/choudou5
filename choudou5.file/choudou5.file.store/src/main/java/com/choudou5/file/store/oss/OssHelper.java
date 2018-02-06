package com.choudou5.file.store.oss;

import cn.hutool.core.io.FileUtil;
import com.aliyun.oss.HttpMethod;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.*;
import com.choudou5.file.store.FileHelper;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Name：Oss 助手
 * @Author：xuhaowen
 * @Date：2018-01-31
 * @Site：http://solrhome.com
 * @License：MIT
 * @Copyright：xuhaowende@sina.cn (@Copyright 2018-2020)
 */
public class OssHelper implements FileHelper {

    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;
    /**
     * 使用STS凭证构造签名
     */
    private String securityToken;

    private OSS client = null;

    private OssHelper(){}

    public OssHelper(String accessKeyId, String accessKeySecret, String bucketName) {
        this.accessKeyId = accessKeyId;
        this.accessKeySecret = accessKeySecret;
        this.bucketName = bucketName;
        try {
            client = new OSSClient(endpoint, accessKeyId, accessKeySecret);
            safeReferer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public OssHelper(String endpoint, String accessKeyId, String accessKeySecret, String bucketName, String securityToken) {
        this.endpoint = endpoint;
        this.accessKeyId = accessKeyId;
        this.accessKeySecret = accessKeySecret;
        this.bucketName = bucketName;
        this.securityToken = securityToken;
        try {
            client = new OSSClient(endpoint, accessKeyId, accessKeySecret, securityToken);
            safeReferer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void shutdown(){
        if(client != null)
            client.shutdown();
    }

    /**
     * 防盗链 doc:https://help.aliyun.com/document_detail/32021.html?spm=a2c4g.11186623.6.684.BAcMVD
     */
    private void safeReferer(){
        List<String> refererList = new ArrayList<String>();
        // 添加referer项
        refererList.add("http://www.aliyun.com");
        refererList.add("http://www.javasaas.com");
        refererList.add("http://www.?.aliyuncs.com");
        // 允许referer字段为空，并设置Bucket Referer列表
        BucketReferer br = new BucketReferer(true, refererList);
        client.setBucketReferer(bucketName, br);
    }

//    public void createBucket(String bucketName, CannedAccessControlList controlType){
//        if (!client.doesBucketExist(bucketName)) {
//            System.out.println("Creating bucket " + bucketName + "\n");
//            client.createBucket(bucketName);
//            CreateBucketRequest createBucketRequest= new CreateBucketRequest(bucketName);
//            createBucketRequest.setCannedACL(controlType);
//            client.createBucket(createBucketRequest);
//        }
//    }

    public boolean existObject(String bucketName, String key){
        return client.doesObjectExist(bucketName, key);
    }

    public void setObjectAcl(String bucketName, String key, CannedAccessControlList controlType){
        client.setObjectAcl(bucketName, key, controlType);
    }

    @Override
    public String getSignAccUrl(String key, int timeoutSecond) {
        return getSignAccUrl(bucketName, key, timeoutSecond);
    }

    @Override
    public void upload(String key, InputStream is) {
        upload(bucketName, key, is);
    }

    @Override
    public void upload(String key, File file) {
        upload(bucketName, key, file);
    }

    @Override
    public File download(String key, String destPath) {
        return download(bucketName, key, destPath);
    }

    @Override
    public void delete(String key) {
        delete(bucketName, key);
    }

    @Override
    public String getSignAccUrl(String bucketName, String key, int timeoutSecond){
        Date expiration = new Date(new Date().getTime() + timeoutSecond * 1000);
        URL url = client.generatePresignedUrl(bucketName, key, expiration, HttpMethod.GET);
        return url.getPath();
    }

    @Override
    public void upload(String bucketName, String key, InputStream is) {
        PutObjectResult result = client.putObject(new PutObjectRequest(bucketName, key, is));
        result.getResponse().isSuccessful();
    }

    @Override
    public void upload(String bucketName, String key, File file) {
        PutObjectResult result = client.putObject(new PutObjectRequest(bucketName, key, file));
        result.getResponse().isSuccessful();
    }

    @Override
    public File download(String bucketName, String key, String destPath) {
        OSSObject object = client.getObject(bucketName, key);
        System.out.println("Content-Type: " + object.getObjectMetadata().getContentType());
        return FileUtil.writeFromStream(object.getObjectContent(), new File(destPath));
    }

    @Override
    public void delete(String bucketName, String key){
        client.deleteObject(bucketName, key);
    }

}
