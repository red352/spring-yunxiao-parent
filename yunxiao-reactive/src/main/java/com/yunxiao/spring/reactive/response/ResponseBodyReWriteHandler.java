package com.yunxiao.spring.reactive.response;

import com.yunxiao.spring.core.protocol.Result;
import org.reactivestreams.Publisher;
import org.springframework.core.MethodParameter;
import org.springframework.core.ReactiveAdapterRegistry;
import org.springframework.http.codec.HttpMessageWriter;
import org.springframework.web.reactive.HandlerResult;
import org.springframework.web.reactive.accept.RequestedContentTypeResolver;
import org.springframework.web.reactive.result.method.annotation.ResponseBodyResultHandler;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * 重写返回body
 *
 * @author LuoYunXiao
 * @since 2023/11/22 14:59
 */
public class ResponseBodyReWriteHandler extends ResponseBodyResultHandler {
    public ResponseBodyReWriteHandler(List<HttpMessageWriter<?>> writers, RequestedContentTypeResolver resolver, ReactiveAdapterRegistry registry) {
        super(writers, resolver, registry);
        setOrder(super.getOrder() - 1);
    }

    @Override
    @Nonnull
    public Mono<Void> handleResult(@Nonnull ServerWebExchange exchange, HandlerResult result) {
        if (result.getReturnValue() instanceof Publisher<?> publisher) {
            // publisher
            if (publisher instanceof Mono<?> mono) {
                return mono.flatMap(originReturnValue -> {
                    if (originReturnValue instanceof Result) {
                        return super.handleResult(exchange, result);
                    } else {
                        return rewriteMono(exchange, result, originReturnValue);
                    }
                });
            } else if (publisher instanceof Flux<?> flux) {
                return flux.collectList().flatMap(returnList -> rewriteMono(exchange, result, returnList));
            }
            return super.handleResult(exchange, result);
        } else {
            // normal java object
            var resulreWriteResulttClass = Result.class;
            HandlerResult reWriteResult;
            if (result.getReturnType().getRawClass() != resulreWriteResulttClass) {
                Result<Object> ok = Result.ok(result.getReturnValue());
                reWriteResult = new HandlerResult(result.getHandler(), ok, new MethodParameter(resulreWriteResulttClass.getConstructors()[0], 0));
            } else {
                reWriteResult = result;
            }
            return super.handleResult(exchange, reWriteResult);
        }
    }

    private Mono<Void> rewriteMono(ServerWebExchange exchange, HandlerResult result, Object originReturnValue) {
        Result<Object> ok = Result.ok(originReturnValue);
        HandlerResult handlerResult;
        try {
            handlerResult = new HandlerResult(result.getHandler(), ok, new MethodParameter(Mono.class.getMethod("just", Object.class), 0));
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

        return super.handleResult(exchange, handlerResult);
    }
}
