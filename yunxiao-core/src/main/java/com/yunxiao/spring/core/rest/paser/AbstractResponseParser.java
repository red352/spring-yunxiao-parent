package com.yunxiao.spring.core.rest.paser;

import lombok.Getter;
import org.springframework.http.ResponseEntity;

/**
 * @param <T> body类型
 * @author LuoYunXiao
 * @since 2023/10/21 14:32
 */
@Getter
public class AbstractResponseParser<T> implements ResponseParser<T> {

    protected ResponseEntity<T> responseEntity;

    public AbstractResponseParser(ResponseEntity<T> responseEntity) {
        this.responseEntity = responseEntity;
    }

    @Override
    public int getHttpStatus() {
        return responseEntity.getStatusCode().value();
    }

}
