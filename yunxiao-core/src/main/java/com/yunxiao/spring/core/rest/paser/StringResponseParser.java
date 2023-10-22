package com.yunxiao.spring.core.rest.paser;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;

/**
 * @author LuoYunXiao
 * @since 2023/10/19 22:43
 */
public final class StringResponseParser extends AbstractResponseParser<String> {

    public StringResponseParser(ResponseEntity<String> responseEntity) {
        super(responseEntity);
    }

    @Nullable
    public String getText() {
        return super.responseEntity.getBody();
    }

    @Nullable
    public JSON getJson() {
        return JSONUtil.parse(getText());
    }



}
