package com.yunxiao.spring.r2dbc.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing;

/**
 * @author LuoYunXiao
 * @since 2023/9/18 14:40
 */
@Configuration
@ComponentScan(basePackages = {"com.yunxiao.spring.r2dbc.config"})
@EnableR2dbcAuditing
public class R2dbcAutoConfiguration {

}
