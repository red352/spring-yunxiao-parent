package com.yunxiao.spring.reactive.config;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.server.AbstractConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.InetAddress;
import java.util.Optional;

/**
 * @author LuoYunXiao
 * @since 2023/9/19 12:36
 */
@RequiredArgsConstructor
@Slf4j
@Configuration
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.REACTIVE)
public class StartConfig implements CommandLineRunner {

    private final ServerProperties serverProperties;
    @Override
    public void run(String... args) throws Exception {
        Integer port = Optional.ofNullable(serverProperties.getPort()).orElse(8080);
        String host = InetAddress.getLocalHost().getHostAddress();
        log.info("Server is running at http://{}:{}/", host, port);
        log.info("openApi is running at http://{}:{}/doc.html", host, port);
    }

    @Bean
    RouterFunction<ServerResponse> index() {
        return RouterFunctions.route(
                RequestPredicates.path("/"),
                request -> ServerResponse.status(HttpStatus.OK).body(Mono.just("server ok"), String.class));
    }
}
