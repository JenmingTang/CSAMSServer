package com.future.csamsserver.converter;

/**
 * @description
 * @dateTime 2025:03:06 23:28
 * @user Jenming
 */

import org.springframework.core.convert.converter.Converter;

import java.time.LocalDateTime;
import java.time.Instant;
import java.time.ZoneId;
import java.util.TimeZone;

public class TimestampToLocalDateTimeConverter implements Converter<String, LocalDateTime> {

    @Override
    public LocalDateTime convert(String source) {
        // 将字符串转换为long类型的时间戳
        long timestamp = Long.parseLong(source);
        // 使用Instant和ZoneId来处理时间戳到LocalDateTime的转换
//        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), java.time.ZoneOffset.UTC);
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.of("Asia/Shanghai"));
    }
}