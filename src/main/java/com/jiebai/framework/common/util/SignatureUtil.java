package com.jiebai.framework.common.util;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * 接口请求参数签名工具
 *
 * @author lizhihui
 * @version 1.0.0
 */
@Slf4j
public class SignatureUtil {

    /**
     * 校验sign
     *
     * @param jsonString jsonString
     * @param apiSecret apiSecret
     * @return boolean
     */
    public static boolean verifySignature(String jsonString, String apiSecret) {
        try {
            Map paramMap = JSON.parseObject(jsonString);
            paramMap.put("apiSecret", apiSecret);
            String requestSign = (String) paramMap.get("sign");
            if (Strings.isNullOrEmpty(requestSign)) {
                log.warn("parse request sign must not blank");
                return false;
            }
            paramMap.remove("sign");
            String parameterString = sortParameter(paramMap);
            String expectedSign = MD5Util.encode(parameterString);
            return Objects.equals(requestSign, expectedSign);
        } catch (Exception e) {
            log.error("parse request body json to map fail", e);
            return false;
        }
    }


    /**
     * 创建带sign的请求json字符串
     *
     * @param jsonString jsonString
     * @param apiSecret apiSecret
     * @return String
     */
    public static String generateSignature(String jsonString, String apiSecret) {
        try {
            Map paramMap = JSON.parseObject(jsonString);
            paramMap.put("apiSecret", apiSecret);
            paramMap.remove("sign");
            String parameterString = sortParameter(paramMap);
            String sign = MD5Util.encode(parameterString);
            paramMap.put("sign", sign);
            return JSON.toJSONString(paramMap);
        } catch (Exception e) {
            log.error("parse request body json to map fail", e);
            return "";
        }
    }


    /**
     * 请求参数按key正序排序，并拼接返回字符串
     *
     * @param info info
     * @return String
     */
    private static String sortParameter(Map<String, Object> info) {

        List<Map.Entry<String, Object>> infoIds = new ArrayList<>(info.entrySet());
        Collections.sort(infoIds, Comparator.comparing(Map.Entry<String, Object>::getKey));
        String resultString;
        StringBuilder stringBuilder = new StringBuilder();
        infoIds.forEach(entry -> {
            Object value = entry.getValue();
            stringBuilder
                .append(String.format("%s=%s&", entry.getKey(), Objects.nonNull(value) ? value.toString() : ""));
        });
        resultString = stringBuilder.substring(0, stringBuilder.length() - 1);
        return resultString;
    }

    public static void main(String[] args) {
        String jsonString = "{\"username\":\"moozilee\",\"password\":\"123456\"}";
        String requestString = generateSignature(jsonString, "20180425");
        log.info("请求密文:\n{}", AESUtil.encrypt(requestString, "wow!@#$%"));
    }
}
