package com.jiebai.framework.common.util;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.io.IOException;
import java.security.SecureRandom;

/**
 * DesUtil
 *
 * @author lizhihui
 * @version 1.0.0
 */
public class DesUtil {
    private final static String DES = "DES";

    public static void main(String[] args) throws Exception {
        String data = "{\"id\":1,\"username\":\"moozilee\",\"age\":30,\"password\":\"123456\",\"ctm\":\"2018-04-24 19:34:11\"}";
        String key = "wow!@#$%";
        System.out.println(encrypt(data, key));
        System.out.println("------------------------");
        System.out.println(decrypt(encrypt(data, key), key));

        System.out.printf(decrypt("2L1i3skYLEv2n09GQzPvT3ZaTkYxPsV0qsxW2EYdxqj6Qb2mikWCdcbCCbuoEAFa1G8JAlsocHRY\r\n0ecoOjtWeo7Yywh2rVviKHgNv1XwwXnckWG5eIW9oMi0BnHcBHXRD8+kGKvcIogkkwkNM3ZfpzsS\r\nTeIXeSHFaGNSWyHL7wVSVWsNIr8dSFLOdOroA+bc1lyWB/uUvUP9GDtW2tHDI2e6fgHeM90b+wjG\r\nZ4166i7o5G+F3dZ6/E1hpZMWazA85qibshpnic1T16Hv4axYpw==",key));

    }

    /**
     * Description 根据键值进行加密
     *
     * @param data data
     * @param key  加密键byte数组
     * @return String
     * @throws Exception Exception
     */
    public static String encrypt(String data, String key) throws Exception {
        byte[] bt = encrypt(data.getBytes(), key.getBytes());
        String strs = Base64.encodeBase64String(bt);
        return strs;
    }

    /**
     * Description 根据键值进行解密
     *
     * @param data data
     * @param key  加密键byte数组
     * @return String
     * @throws IOException IOException
     * @throws Exception Exception
     */
    public static String decrypt(String data, String key) throws IOException, Exception {
        if (data == null) {
            return null;
        }
        byte[] buf = Base64.decodeBase64(data);
        byte[] bt = decrypt(buf, key.getBytes());
        return new String(bt);
    }

    /**
     * Description 根据键值进行加密
     *
     * @param data data
     * @param key  加密键byte数组
     * @return byte[]
     * @throws Exception
     */
    private static byte[] encrypt(byte[] data, byte[] key) throws Exception {
        // 生成一个可信任的随机数源
        SecureRandom sr = new SecureRandom();

        // 从原始密钥数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);

        // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey securekey = keyFactory.generateSecret(dks);

        // Cipher对象实际完成加密操作
        Cipher cipher = Cipher.getInstance(DES);

        // 用密钥初始化Cipher对象
        cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);

        return cipher.doFinal(data);
    }


    /**
     * Description 根据键值进行解密
     *
     * @param data data
     * @param key  加密键byte数组
     * @return byte[]
     * @throws Exception
     */
    private static byte[] decrypt(byte[] data, byte[] key) throws Exception {
        // 生成一个可信任的随机数源
        SecureRandom sr = new SecureRandom();

        // 从原始密钥数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);

        // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey securekey = keyFactory.generateSecret(dks);

        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance(DES);

        // 用密钥初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, securekey, sr);

        return cipher.doFinal(data);
    }
}
