package com.jiebai.framework.common.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * AES 加密工具类
 *
 * @author lizhihui
 * @version 1.0.0
 */
@Slf4j
public class AESUtil {
    private static final String KEY_ALGORITHM = "AES";
    /**
     * 默认的加密算法
     */
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";

    /**
     * CBC默认的加密算法
     */
    private static final String DEFAULT_CIPHER_ALGORITHM_CBC = "AES/CBC/PKCS5Padding";

    /**
     * AES 加密操作
     *
     * @param content 待加密内容
     * @param key     加密密码
     * @return 返回Base64转码后的加密数据
     */
    public static String encrypt(String content, String key) {
        try {
            // 创建密码器
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);

            byte[] byteContent = content.getBytes("utf-8");
            // 初始化为加密模式的密码器
            //cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(key));
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key.getBytes(), KEY_ALGORITHM));
            // 加密
            byte[] result = cipher.doFinal(byteContent);
            //通过Base64转码返回
            return Base64.encodeBase64String(result);
        } catch (Exception ex) {
            log.error("AES encrypt error ", ex);
        }

        return null;
    }

    /**
     * AES 解密操作
     *
     * @param content content
     * @param key key
     * @return String
     */
    public static String decrypt(String content, String key) {

        try {
            //实例化
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);

            //使用密钥初始化，设置为解密模式
            //cipher.init(Cipher.DECRYPT_MODE, getSecretKey(key));
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key.getBytes(), KEY_ALGORITHM));
            //执行操作
            byte[] result = cipher.doFinal(Base64.decodeBase64(content));

            return new String(result, "utf-8");
        } catch (Exception ex) {
            log.error("AES decrypt error ", ex);
        }

        return null;
    }

    /**
     * 生成加密秘钥
     *
     * @return
     */
    private static SecretKeySpec getSecretKey(final String key) {
        //返回生成指定算法密钥生成器的 KeyGenerator 对象
        KeyGenerator keyGenerator = null;

        try {
            keyGenerator = KeyGenerator.getInstance(KEY_ALGORITHM);

            //AES 要求密钥长度为 128
            keyGenerator.init(128, new SecureRandom(key.getBytes()));

            //生成一个密钥
            SecretKey secretKey = keyGenerator.generateKey();
            // 转换为AES专用密钥
            return new SecretKeySpec(secretKey.getEncoded(), KEY_ALGORITHM);
        } catch (NoSuchAlgorithmException ex) {
            log.error("AES getSecretKey error ", ex);
        }

        return null;
    }


    /**
     * CBC AES加密操作
     *
     * @param content content
     * @param key key
     * @param ivParameter ivParameter
     * @return String
     */
    public static String encryptCBC(String content, String key, String ivParameter) {
        try {
            // 创建密码器
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM_CBC);

            byte[] byteContent = content.getBytes("utf-8");

            IvParameterSpec ivParameterSpec = new IvParameterSpec(ivParameter.getBytes());
            // 初始化为加密模式的密码器
            //cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(key));
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key.getBytes(), KEY_ALGORITHM), ivParameterSpec);
            // 加密
            byte[] result = cipher.doFinal(byteContent);
            //通过Base64转码返回
            return Base64.encodeBase64String(result);
        } catch (Exception ex) {
            log.error("AES encrypt error ", ex);
        }

        return null;
    }

    /**
     * CBC AES 解密操作
     *
     * @param content content
     * @param key key
     * @param ivParameter ivParameter
     * @return String
     */
    public static String decryptCBC(String content, String key, String ivParameter) {

        try {
            //实例化
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM_CBC);

            IvParameterSpec ivParameterSpec = new IvParameterSpec(ivParameter.getBytes());
            //使用密钥初始化，设置为解密模式
            //cipher.init(Cipher.DECRYPT_MODE, getSecretKey(key));
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key.getBytes(), KEY_ALGORITHM), ivParameterSpec);
            //执行操作
            byte[] result = cipher.doFinal(Base64.decodeBase64(content));

            return new String(result, "utf-8");
        } catch (Exception ex) {
            log.error("AES decrypt error ", ex);
        }

        return null;
    }

    public static void main(String[] args) {
        String s =
            "{\"id\":1,\"username\":\"moozilee\",\"age\":30,\"password\":\"123456\",\"ctm\":\"2018-04-24 19:34:11\"}";
        //String aesKey = "wow!@#$%";
        String aesKey = "rZxl3zy!rZxl3zy!";
        String ss = encryptCBC(s, aesKey, "rZxl3zy!rZxl3zy!");

        ss = decryptCBC(ss, aesKey, "rZxl3zy!rZxl3zy!");
        String crypto =
            "6P1Zg0dn4qImyExYkOJxdrqqNjYKsiyYvrYrastziWkkc/ng7xHHLgHZ8DAQwVzx/4iBm/cNCOK0tdr8P9U4k9zfWVucyx/dJsni0y2Q8tvtjHa1qGb60Y9/N12K5S9cTvi9GIIFtpXqAC3qekzQsAI4zSMtyzwDIhn/qBjxtKEj+8pV9ITtIgUOib2VsjuGMACZvrLPnFbD1cEWI9na5837/cTHDjtiWCJnJz5cl6L7ag6EiXZf/64r0ZXfIZhfFCQU+dBLs4kBnyKzThOpmweMuwIEhVCCONSRwK0cqkBK/J8EDeC4bLLUZt4INU2Bq9gUiolKx7zosBjq1Clel6yJMQaEENO5J/Mp4SZzB+oQMzAcr/HOE+uS11TNAeIL";
        log.info("解密结果：\n{}", AESUtil.decrypt(crypto, aesKey));
    }
}
