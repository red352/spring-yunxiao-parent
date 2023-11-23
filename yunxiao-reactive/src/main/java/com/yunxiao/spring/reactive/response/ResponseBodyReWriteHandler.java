package com.yunxiao.spring.reactive.response;

import org.junit.jupiter.api.Order;
import org.springframework.core.MethodParameter;
import org.springframework.core.Ordered;
import org.springframework.core.ReactiveAdapterRegistry;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.codec.HttpMessageWriter;
import org.springframework.web.reactive.HandlerResult;
import org.springframework.web.reactive.accept.RequestedContentTypeResolver;
import org.springframework.web.reactive.result.method.annotation.ResponseBodyResultHandler;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.List;

/**
 * @author LuoYunXiao
 * @since 2023/11/22 14:59
 */
public class ResponseBodyReWriteHandler extends ResponseBodyResultHandler {
    public ResponseBodyReWriteHandler(List<HttpMessageWriter<?>> writers, RequestedContentTypeResolver resolver, ReactiveAdapterRegistry registry) {
        super(writers, resolver, registry);
        setOrder(100);
    }

    @Override
    public Mono<Void> handleResult(ServerWebExchange exchange, HandlerResult result) {
        Object body = result.getReturnValue();
        MethodParameter bodyTypeParameter = result.getReturnTypeSource();
        if (body instanceof ProblemDetail detail) {
            exchange.getResponse().setStatusCode(HttpStatusCode.valueOf(detail.getStatus()));
            if (detail.getInstance() == null) {
                URI path = URI.create(exchange.getRequest().getPath().value());
                detail.setInstance(path);
            }
        }
        return writeBody(body, bodyTypeParameter, exchange);
    }
}
