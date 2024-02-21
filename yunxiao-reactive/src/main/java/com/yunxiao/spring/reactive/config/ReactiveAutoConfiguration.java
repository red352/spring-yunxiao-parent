package com.yunxiao.spring.reactive.config;

import com.yunxiao.spring.reactive.response.ResponseBodyReWriteHandler;
import com.yunxiao.spring.reactive.response.ErrorHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ReactiveAdapterRegistry;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.accept.RequestedContentTypeResolver;

/**
 * @author LuoYunXiao
 * @since 2023/9/18 14:40
 */
@Configuration
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.REACTIVE)
@ComponentScan(basePackages = {"com.yunxiao.spring.reactive.config"})
public class ReactiveAutoConfiguration {

    @Bean
    ErrorHandler errorHandler() {
        Logger logger = LoggerFactory.getLogger(ErrorHandler.class);
        logger.info("已注册 RestControllerAdvice");
        return new ErrorHandler();
    }

    @Bean
    ResponseBodyReWriteHandler responseBodyReWriteHandler(final ServerCodecConfigurer codecConfigurer, final RequestedContentTypeResolver resolver, final ReactiveAdapterRegistry registry) {
        return new ResponseBodyReWriteHandler(codecConfigurer.getWriters(), resolver, registry);
    }
}
