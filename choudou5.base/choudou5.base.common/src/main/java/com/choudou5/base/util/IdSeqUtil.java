package com.choudou5.base.util;


import cn.hutool.core.lang.Snowflake;

public class IdSeqUtil {

    private static long DC_ID = 1; //数据中心ID
    private static long WORKER_ID = 20; //终端ID
    private static String SIGN_KEY = "choudou5"; //签名key

    /** 构造注入 */
    public IdSeqUtil(long dcId, long workerId, String signKey) {
        DC_ID = dcId;
        WORKER_ID = workerId;
        SIGN_KEY = signKey;
    }
    /** Spring注入属性 */
    public void setDcId(long dcId) {
        DC_ID = dcId;
    }
    public void setWorkerId(long workerId) {
        WORKER_ID = workerId;
    }
    public void setSignKey(String signKey) {
        SIGN_KEY = signKey;
    }

    private static final Snowflake snowflake = new Snowflake(WORKER_ID, DC_ID);

    public static String getIdStr() {
        return getId()+"";
    }

    public static long getId(){
        return snowflake.nextId();
    }


    /**
     * 加密ID
     * @param id
     * @return
     */
    public static String encryptId(String id){
        return EncryptUtil.encryptToBase64String(id, SIGN_KEY);
    }

    /**
     * 深度 加密ID
     * @param id
     * @return
     */
    public static String depthEncryptId(String id){
        String code = GzipUtil.compress(id);
        return encryptId(code);
    }


    /**
     * 解密ID
     * @param id
     * @return
     */
    public static String decryptId(String id) {
        String value = null;
        try {
            value = EncryptUtil.decryptBase64StringToString(id, SIGN_KEY);
        } catch (Exception e) {
            e.printStackTrace();
        }
        AssertUtil.isNotNull(value, "解密ID失败！");
        return value;
    }


    /**
     * 解密ID
     * @param id
     * @return
     */
    public static String depthDecryptId(String id) {
        String code = decryptId(id.replace(" ", "+"));
        return GzipUtil.uncompress(code);
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            String code = depthEncryptId("123456" + i);
            System.out.println(code);
            String value = depthDecryptId(code);
            System.out.println(value);
        }
        long end = System.currentTimeMillis();
        System.out.println("time:"+(end-start));
    }
}
