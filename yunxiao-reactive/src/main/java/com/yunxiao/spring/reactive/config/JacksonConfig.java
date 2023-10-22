package com.yunxiao.spring.reactive.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.format.DateTimeFormatter;

/**
 * @author LuoYunXiao
 * @since 2023/10/22 14:47
 */
@Configuration
public class JacksonConfig {

    @Bean
    Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return builder -> {
            // 时间转换
            String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
            builder.simpleDateFormat(DATE_TIME_PATTERN);
            builder.deserializers(new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(DATE_TIME_PATTERN)));

            // null值处理
            builder.serializationInclusion(JsonInclude.Include.NON_NULL);
        };
    }
}
