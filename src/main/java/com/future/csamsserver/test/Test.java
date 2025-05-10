package com.future.csamsserver.test;

import cn.dev33.satoken.util.SaResult;
import cn.hutool.crypto.SecureUtil;

import java.util.HashMap;

/**
 * @description
 * @dateTime 2025:04:05 00:46
 * @user Jenming
 */
public class Test {
    public static void main(String[] args) {
        final HashMap<String, Integer> map = new HashMap<>();
        map.put("a",12);
        map.put("b",122);
        final SaResult data = SaResult.data(map);
        System.out.println("SaResult.data(map) = " + data);
        passwordEncryption();
    }

    private static void passwordEncryption() {
        System.out.println("SecureUtil.md5(\"Pwd#1234\") = " + SecureUtil.md5("Pwd#1234"));
//        SecureUtil.md5("Pwd#1234") = 8add023d4204bc7a2d6afa501029c79a

    }
}
