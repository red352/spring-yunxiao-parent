package com.yunxiao.spring.reactive.config;

import com.yunxiao.spring.reactive.model.result.ErrorHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author LuoYunXiao
 * @since 2023/9/18 14:40
 */
@Configuration
@ComponentScan(basePackages = {"com.yunxiao.spring.reactive.config"})
public class AppAutoConfiguration {

    @Bean
    ErrorHandler errorHandler() {
        Logger logger = LoggerFactory.getLogger(ErrorHandler.class);
        logger.info("已注册 RestControllerAdvice");
        return new ErrorHandler();
    }

}
