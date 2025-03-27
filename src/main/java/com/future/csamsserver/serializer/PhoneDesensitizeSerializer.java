package com.future.csamsserver.serializer;

import cn.hutool.core.util.DesensitizedUtil;
import com.future.csamsserver.utils.DesensitizationUtils;

/**
 * @description
 * @dateTime 2025:03:08 19:17
 * @user Jenming
 */
// 手机号序列化器
public class PhoneDesensitizeSerializer extends BaseDesensitizeSerializer {
    @Override
    protected String doDesensitize(String value) {
//        return DesensitizationUtils.desensitizePhone(value);
        return DesensitizedUtil.mobilePhone(value);
    }
}
