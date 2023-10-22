package com.yunxiao.spring.reactive.model.result;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.domain.Page;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * 统一返回类
 *
 * @author LuoYunXiao
 * @since 2023/10/21 21:54
 */
public record Result<T>(
        @Schema(description = "返回码", example = "200")
        int code,
        @Schema(description = "返回数据", example = "{}")
        T data,
        @Schema(description = "返回消息", example = "OK")
        String msg,
        @Schema(description = "返回提示", example = "OK")
        String tips,
        @Schema(description = "返回属性", example = "{}")
        Map<?, ?> properties

) {


    public static Result<Void> ok() {
        return ofNull().codeEnum(CodeEnum.SUCCESS).build();
    }

    public static <T> Result<T> ok(T data) {
        return of(data).codeEnum(CodeEnum.SUCCESS).build();
    }

    public static <T> Builder<T> of(T data) {
        return new Builder<>(data);
    }

    public static Builder<Void> ofNull() {
        return new Builder<>(null);
    }

    public static <T> Builder<Page<T>> ofPage(Page<T> page) {
        return new Builder<>(page);
    }

    public static class Builder<T> {
        private int code;
        private final T data;
        private String msg;
        private String tips;
        private Map<?, ?> properties;

        public Builder(T data) {
            this.data = data;
        }

        public Builder<T> codeEnum(CodeAble codeEnum) {
            this.code = codeEnum.getCode();
            this.msg = codeEnum.getMsg();
            return this;
        }

        public Builder<T> code(int code) {
            this.code = code;
            return this;
        }

        public Builder<T> msg(String msg) {
            this.msg = msg;
            return this;
        }

        public Builder<T> properties(Map<?, ?> properties) {
            this.properties = properties;
            return this;
        }

        public Builder<T> tips(String tips) {
            this.tips = tips;
            return this;
        }

        public Result<T> build() {
            return new Result<>(code, data, msg, tips, properties);
        }

        public Result<T> ok() {
            this.codeEnum(CodeEnum.SUCCESS);
            return build();
        }

        public Mono<Result<T>> toMono() {
            return Mono.just(build());
        }
    }


}
