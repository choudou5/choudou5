package com.choudou5.base.util;


import cn.hutool.core.codec.Base64;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 加密工具类 （XXTEA）
 */
public final class EncryptUtil{

    private static final int DELTA = 0x9E3779B9;

    //用来将字节转换成 16 进制表示的字符
    private static final char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static MessageDigest digestMd5 = null;
    private static MessageDigest digestSha1 = null;

    static {
        try {
            digestMd5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException nsaex) {
            System.err.println("初始化MessageDigest MD5 失败");
            nsaex.printStackTrace();
        }
        try {
            digestSha1 = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException nsaex) {
            System.err.println("初始化MessageDigest Sha1 失败");
            nsaex.printStackTrace();
        }
    }


    private EncryptUtil() {}

    public final static String md5(String str) {
        try {
            byte[] strTemp = str.getBytes();
            digestMd5.update(strTemp);
            byte tmp[] = digestMd5.digest(); // MD5 的计算结果是一个 128 位的长整数，
            // 用字节表示就是 16 个字节
            char strs[] = new char[16 * 2]; // 每个字节用 16 进制表示的话，使用两个字符，
            // 所以表示成 16 进制需要 32 个字符
            int k = 0; // 表示转换结果中对应的字符位置
            for (int i = 0; i < 16; i++) { // 从第一个字节开始，对 MD5 的每一个字节
                // 转换成 16 进制字符的转换
                byte byte0 = tmp[i]; // 取第 i 个字节
                strs[k++] = hexDigits[byte0 >>> 4 & 0xf]; // 取字节中高 4 位的数字转换,
                // >>> 为逻辑右移，将符号位一起右移
                strs[k++] = hexDigits[byte0 & 0xf]; // 取字节中低 4 位的数字转换
            }
            return new String(strs).toUpperCase(); // 换后的结果转换为字符串
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public final static String sha1(String str) {
        try {
            digestSha1.update(str.getBytes());
            byte messageDigest[] = digestSha1.digest();
            StringBuffer hexString = new StringBuffer();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static int mx(int sum, int y, int z, int p, int e, int[] k) {
        return (z >>> 5 ^ y << 2) + (y >>> 3 ^ z << 4) ^ (sum ^ y) + (k[p & 3 ^ e] ^ z);
    }

    public static final byte[] encrypt(byte[] data, byte[] key) {
        if (data.length == 0) {
            return data;
        }
        return toByteArray(encrypt(toIntArray(data, true), toIntArray(fixKey(key), false)), false);
    }
    public static final byte[] encrypt(String data, byte[] key) {
        try {
            return encrypt(data.getBytes("UTF-8"), key);
        }
        catch (UnsupportedEncodingException e) {
            return null;
        }
    }
    public static final byte[] encrypt(byte[] data, String key) {
        try {
            return encrypt(data, key.getBytes("UTF-8"));
        }
        catch (UnsupportedEncodingException e) {
            return null;
        }
    }
    public static final byte[] encrypt(String data, String key) {
        try {
            return encrypt(data.getBytes("UTF-8"), key.getBytes("UTF-8"));
        }
        catch (UnsupportedEncodingException e) {
            return null;
        }
    }
    public static final String encryptToBase64String(byte[] data, byte[] key) {
        byte[] bytes = encrypt(data, key);
        if (bytes == null) return null;
        return Base64.encode(bytes);
    }
    public static final String encryptToBase64String(String data, byte[] key) {
        byte[] bytes = encrypt(data, key);
        if (bytes == null) return null;
        return Base64.encode(bytes);
    }
    public static final String encryptToBase64String(byte[] data, String key) {
        byte[] bytes = encrypt(data, key);
        if (bytes == null) return null;
        return Base64.encode(bytes);
    }
    public static final String encryptToBase64String(String data, String key) {
        byte[] bytes = encrypt(data, key);
        if (bytes == null) return null;
        return Base64.encode(bytes);
    }
    public static final byte[] decrypt(byte[] data, byte[] key) {
        if (data.length == 0) {
            return data;
        }
        return toByteArray(
                decrypt(toIntArray(data, false), toIntArray(fixKey(key), false)), true);
    }
    public static final byte[] decrypt(byte[] data, String key) {
        try {
            return decrypt(data, key.getBytes("UTF-8"));
        }
        catch (UnsupportedEncodingException e) {
            return null;
        }
    }
    public static final byte[] decryptBase64String(String data, byte[] key) {
        return decrypt(Base64.decode(data), key);
    }
    public static final byte[] decryptBase64String(String data, String key) {
        return decrypt(Base64.decode(data), key);
    }
    public static final String decryptToString(byte[] data, byte[] key) {
        try {
            byte[] bytes = decrypt(data, key);
            if (bytes == null) return null;
            return new String(bytes, "UTF-8");
        }
        catch (UnsupportedEncodingException ex) {
            return null;
        }
    }
    public static final String decryptToString(byte[] data, String key) {
        try {
            byte[] bytes = decrypt(data, key);
            if (bytes == null) return null;
            return new String(bytes, "UTF-8");
        }
        catch (UnsupportedEncodingException ex) {
            return null;
        }
    }
    public static final String decryptBase64StringToString(String data, byte[] key) {
        try {
            byte[] bytes = decrypt(Base64.decode(data), key);
            if (bytes == null) return null;
            return new String(bytes, "UTF-8");
        }
        catch (UnsupportedEncodingException ex) {
            return null;
        }
    }
    public static final String decryptBase64StringToString(String data, String key) {
        try {
            byte[] bytes = decrypt(Base64.decode(data), key);
            if (bytes == null) return null;
            return new String(bytes, "UTF-8");
        }
        catch (UnsupportedEncodingException ex) {
            return null;
        }
    }

    private static int[] encrypt(int[] v, int[] k) {
        int n = v.length - 1;

        if (n < 1) {
            return v;
        }
        int p, q = 6 + 52 / (n + 1);
        int z = v[n], y, sum = 0, e;

        while (q-- > 0) {
            sum = sum + DELTA;
            e = sum >>> 2 & 3;
            for (p = 0; p < n; p++) {
                y = v[p + 1];
                z = v[p] += mx(sum, y, z, p, e, k);
            }
            y = v[0];
            z = v[n] += mx(sum, y, z, p, e, k);
        }
        return v;
    }

    private static int[] decrypt(int[] v, int[] k) {
        int n = v.length - 1;

        if (n < 1) {
            return v;
        }
        int p, q = 6 + 52 / (n + 1);
        int z, y = v[0], sum = q * DELTA, e;

        while (sum != 0) {
            e = sum >>> 2 & 3;
            for (p = n; p > 0; p--) {
                z = v[p - 1];
                y = v[p] -= mx(sum, y, z, p, e, k);
            }
            z = v[n];
            y = v[0] -= mx(sum, y, z, p, e, k);
            sum = sum - DELTA;
        }
        return v;
    }

    private static byte[] fixKey(byte[] key) {
        if (key.length == 16) return key;
        byte[] fixedkey = new byte[16];
        if (key.length < 16) {
            System.arraycopy(key, 0, fixedkey, 0, key.length);
        }
        else {
            System.arraycopy(key, 0, fixedkey, 0, 16);
        }
        return fixedkey;
    }

    private static int[] toIntArray(byte[] data, boolean includeLength) {
        int n = (((data.length & 3) == 0)
                ? (data.length >>> 2)
                : ((data.length >>> 2) + 1));
        int[] result;

        if (includeLength) {
            result = new int[n + 1];
            result[n] = data.length;
        }
        else {
            result = new int[n];
        }
        n = data.length;
        for (int i = 0; i < n; ++i) {
            result[i >>> 2] |= (0x000000ff & data[i]) << ((i & 3) << 3);
        }
        return result;
    }

    private static byte[] toByteArray(int[] data, boolean includeLength) {
        int n = data.length << 2;

        if (includeLength) {
            int m = data[data.length - 1];
            n -= 4;
            if ((m < n - 3) || (m > n)) {
                return null;
            }
            n = m;
        }
        byte[] result = new byte[n];

        for (int i = 0; i < n; ++i) {
            result[i] = (byte) (data[i >>> 2] >>> ((i & 3) << 3));
        }
        return result;
    }

}

