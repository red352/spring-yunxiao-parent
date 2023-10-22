package com.yunxiao.spring.core.rest.paser;

import org.springframework.http.ResponseEntity;

/**
 * @author LuoYunXiao
 * @since 2023/10/21 14:30
 */
public interface ResponseParser <T> {

    int getHttpStatus();

    ResponseEntity<T> getResponseEntity();
}
