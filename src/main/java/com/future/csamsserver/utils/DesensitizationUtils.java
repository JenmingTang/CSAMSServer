package com.future.csamsserver.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * @description
 * @dateTime 2025:03:08 19:16
 * @user Jenming
 */
public class DesensitizationUtils {
    // 手机号脱敏（保留前3后4）
    public static String desensitizePhone(String phone) {
        if (StringUtils.isBlank(phone)) return "";
        if (phone.length() != 11) return phone; // 根据实际情况处理非常规长度

        return phone.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
    }

    // 邮箱脱敏（保留@前首字符和域名）
    public static String desensitizeEmail(String email) {
        if (StringUtils.isBlank(email)) return "";
        int atIndex = email.indexOf("@");
        if (atIndex <= 1) return email;

        String prefix = email.substring(0, atIndex);
        String suffix = email.substring(atIndex);
        return prefix.charAt(0) + "****" + prefix.substring(prefix.length()-1) + suffix;
    }
}
