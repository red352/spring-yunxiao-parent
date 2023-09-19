package com.yunxiao.spring.reactive.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.util.Optional;

/**
 * @author LuoYunXiao
 * @since 2023/9/19 12:36
 */
@RequiredArgsConstructor
@Slf4j
@Configuration
@ConditionalOnWebApplication
public class StartConfig implements CommandLineRunner {

    private final ServerProperties serverProperties;

    @Override
    public void run(String... args) throws Exception {
        Integer port = Optional.ofNullable(serverProperties.getPort()).orElse(8080);
        String host = InetAddress.getLocalHost().getHostAddress();
        log.info("Server is running at http://{}:{}/", host, port);
    }
}
