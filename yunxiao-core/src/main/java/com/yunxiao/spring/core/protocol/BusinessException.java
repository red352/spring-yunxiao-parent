package com.yunxiao.spring.core.protocol;

import lombok.Getter;
import lombok.Setter;

/**
 * @author LuoYunXiao
 * @since 2023/10/27 22:00
 */
@Getter
@Setter
public class BusinessException extends RuntimeException {

    private final int code;

    private final String msg;

    private final String tips;

    public BusinessException(CodeAble codeAble, String tips) {
        super(codeAble.getMsg());
        this.code = codeAble.getCode();
        this.msg = codeAble.getMsg();
        this.tips = tips;
    }

    public BusinessException(CodeAble codeAble) {
        super(codeAble.getMsg());
        this.code = codeAble.getCode();
        this.msg = codeAble.getMsg();
        this.tips = null;
    }
}
