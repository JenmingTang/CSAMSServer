package com.future.csamsserver.serializer;

import cn.hutool.core.util.DesensitizedUtil;
import com.future.csamsserver.utils.DesensitizationUtils;

/**
 * @description
 * @dateTime 2025:03:08 19:18
 * @user Jenming
 */
// 邮箱序列化器
public class EmailDesensitizeSerializer extends BaseDesensitizeSerializer {
    @Override
    protected String doDesensitize(String value) {
//        return DesensitizationUtils.desensitizeEmail(value);
        return DesensitizedUtil.email(value);
    }
}
