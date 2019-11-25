package com.jiebai.framework.common.util;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * NumberUtils
 *
 * @author wangyibin
 */
@Slf4j
public class NumberUtils {

    /**
     * double四舍五入保留两位小数
     *
     * @param number number
     * @return double
     */
    public static double keepTwoDecimals(double number) {
        DecimalFormat df = new DecimalFormat("#.00");
        String format = df.format(number);
        return Double.parseDouble(format);
    }

    /**
     * double保留两位小数
     *
     * @param number number
     * @return String
     */
    public static String keepStringTwoDecimals(double number) {
        return String.format("%.2f", number);
    }

    /**
     * 保留两位小数
     *
     * @param number number
     * @return double
     */
    public static double keepDoubleDecimal(double number) {
        BigDecimal bg = new BigDecimal(number);
        return bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * string 转 Integer
     *
     * @param number number
     * @return Integer
     */
    public static Integer stringToInteger(String number) {
        if (number == null) {
            return null;
        }
        Integer num = null;
        try {
            num = Integer.valueOf(number.trim());
        } catch (Exception e) {
            log.error("字符串(" + number + ")转int异常:" + e.getMessage());
        }
        return num;
    }

    /**
     * string 转 int
     *
     * @param number number
     * @return int
     */
    public static int stringToInt(String number) {
        Integer integer = stringToInteger(number);
        int num = integer == null ? 0 : integer.intValue();
        return num;
    }

}
