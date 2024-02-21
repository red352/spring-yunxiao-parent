package com.yunxiao.spring.reactive.response;


import com.yunxiao.spring.core.protocol.Result;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

/**
 * @author LuoYunXiao
 * @since 2023/10/22 13:13
 */
@RestControllerAdvice
@Slf4j
public class ErrorHandler {

    @ExceptionHandler(Exception.class)
    public Mono<Result<Void>> exceptionHandler(Exception e) {
        log.error(e.getLocalizedMessage(), e);
        Result<Void> result = Result.ofNull()
                .codeAble(Result.CodeEnum.FAIL).build();
        return Mono.just(result);

    }

    @ExceptionHandler(WebExchangeBindException.class)
    public Mono<Result<Void>> webExchangeBindException(WebExchangeBindException e) {
        handleKnownError(e);
        Result<Void> result = Result.ofNull()
                .codeAble(Result.CodeEnum.VALIDATE_ERROR)
                .tips(Arrays.toString(Arrays.stream(Optional.ofNullable(e.getDetailMessageArguments()).orElse(new Object[0]))
                        .filter(o -> {
                            if (Objects.isNull(o)) {
                                return false;
                            }
                            if (o instanceof String s) {
                                return !s.isBlank();
                            }
                            if (o instanceof Collection<?> n) {
                                return !n.isEmpty();
                            }
                            return true;
                        })
                        .toArray())
                ).build();
        return Mono.just(result);
    }

    @ExceptionHandler(ValidationException.class)
    public Mono<Result<Void>> validationException(ValidationException e) {
        handleKnownError(e);
        Result<Void> result = Result.ofNull().codeAble(Result.CodeEnum.VALIDATE_ERROR).tips(e.getMessage()).build();
        return Mono.just(result);
    }

    private static void handleKnownError(Exception e) {
        if (log.isDebugEnabled()) {
            log.error(e.getLocalizedMessage(), e);
        }
    }

}
