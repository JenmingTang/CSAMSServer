package com.future.csamsserver.converter;

/**
 * @description
 * @dateTime 2025:03:13 00:36
 * @user Jenming
 */
import com.future.csamsserver.enumeration.TargetTypeEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
/*
or
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
* */
//@Component
public class StringToTargetTypeEnumConverter implements Converter<String, TargetTypeEnum> {

    @Override
    public TargetTypeEnum convert(String source) {
        int code = Integer.parseInt(source); // 将字符串转换为数字
        return TargetTypeEnum.of(code);      // 调用枚举的 of 方法
    }
}
