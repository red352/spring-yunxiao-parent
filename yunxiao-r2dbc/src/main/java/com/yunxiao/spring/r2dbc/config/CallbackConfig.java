package com.yunxiao.spring.r2dbc.config;

import com.yunxiao.spring.core.protocol.BaseEntity;
import com.yunxiao.spring.r2dbc.callback.DefaultBeforeConvertCallback;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.mapping.event.BeforeConvertCallback;

/**
 * @author LuoYunXiao
 * @since 2023/12/17 13:16
 */
@Configuration
public class CallbackConfig {
    @Bean
    BeforeConvertCallback<BaseEntity> beforeConvertCallback() {
        return new DefaultBeforeConvertCallback();
    }
}
