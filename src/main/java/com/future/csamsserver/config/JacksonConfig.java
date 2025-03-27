package com.future.csamsserver.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.future.csamsserver.converter.StringToLocalDateTimeConverter;
import com.future.csamsserver.converter.TimestampToLocalDateTimeConverter;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.TimeZone;

/**
 * @description
 * @dateTime 2025:03:06 16:57
 * @user Jenming
 */
@Configuration
public class JacksonConfig implements WebMvcConfigurer {


    /*
     * 都是为http 参数服务
     * */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        WebMvcConfigurer.super.configureMessageConverters(converters);
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToLocalDateTimeConverter());
        registry.addConverter(new TimestampToLocalDateTimeConverter());
    }
//    @Bean
//    public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {
//        return builder -> {
//            // 序列化规则：LocalDateTime → "yyyy-MM-dd HH:mm:ss"
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//            builder.serializers(new LocalDateTimeSerializer(formatter));
//            builder.deserializers(new LocalDateTimeDeserializer(formatter));
//            builder.modules(new JavaTimeModule()); // 注册 Java 8 时间模块
//            builder.featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    /// /            若时间显示偏差，可在配置中添加时区设置：
//            builder.timeZone(TimeZone.getTimeZone("GMT+8"));
//        };
//    }
    /*
     * 为序列化json bean服务
其他配置覆盖： 检查项目中是否有其他地方的 @JsonFormat 注解或 ObjectMapper 配置
IDE缓存问题：
执行 mvn clean install
重启IDE并清理编译缓存
     * */
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {
        return builder -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            // 完全自定义反序列化逻辑
            JsonDeserializer<LocalDateTime> customDeserializer = new JsonDeserializer<>() {
                @Override
                public LocalDateTime deserialize(JsonParser p, DeserializationContext ctx)
                        throws IOException {

                    // 处理时间戳（长整型）
                    if (p.currentToken().isNumeric()) {
                        long timestamp = p.getLongValue();
                        return Instant.ofEpochMilli(timestamp)
                                .atZone(ZoneId.of("GMT+8"))
                                .toLocalDateTime();
                    }
                    // 处理字符串格式
                    else if (p.currentToken() == JsonToken.VALUE_STRING) {
                        return LocalDateTime.parse(p.getText(), formatter);
                    }
                    throw new IOException("无法解析的时间格式");
                }
            };

            // 创建新模块并覆盖默认行为
            SimpleModule module = new SimpleModule();
            module.addDeserializer(LocalDateTime.class, customDeserializer);

            builder.serializers(new LocalDateTimeSerializer(formatter))
                    .modules(module) // 只使用自定义模块
                    .featuresToDisable(
                            SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,
                            DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE
                    )
                    .timeZone(TimeZone.getTimeZone("GMT+8"));
        };
    }


}