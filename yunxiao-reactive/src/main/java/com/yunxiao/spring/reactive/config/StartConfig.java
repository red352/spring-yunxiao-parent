package com.yunxiao.spring.reactive.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
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

    private final ApplicationContext applicationContext;
    private final ServerProperties serverProperties;

    @Override
    public void run(String... args) throws Exception {
        Integer port = Optional.ofNullable(serverProperties.getPort()).orElse(8080);
        String host = InetAddress.getLocalHost().getHostAddress();
        log.info("server is running at http://{}:{}/", host, port);
        log.info("swagger is running at http://{}:{}/doc.html", host, port);
    }

    @Bean
    RouterFunction<ServerResponse> index() {
        return RouterFunctions.route(
                RequestPredicates.path("/"),
                request -> ServerResponse.status(HttpStatus.OK).body(Mono.just(Optional.ofNullable(applicationContext.getId()).orElse("service") + " server is ok"), String.class));
    }
}
