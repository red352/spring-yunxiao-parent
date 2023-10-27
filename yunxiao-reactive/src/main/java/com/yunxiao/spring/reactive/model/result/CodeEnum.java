package com.yunxiao.spring.reactive.model.result;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author LuoYunXiao
 * @since 2023/10/21 22:32
 */
@Getter
@AllArgsConstructor
public enum CodeEnum implements CodeAble {

    SUCCESS(0, "ok"),
    FAIL(1, "failure");

    private final int code;
    private final String msg;
}
