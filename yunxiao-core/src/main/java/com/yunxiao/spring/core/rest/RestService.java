package com.yunxiao.spring.core.rest;


import com.yunxiao.spring.core.rest.paser.StringResponseParser;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

/**
 * @author LuoYunXiao
 * @since 2023/10/19 22:06
 */
public record RestService(RestTemplate restTemplate) {

    public StringResponseParser doStringRest(RequestObj request) {
        return new StringResponseParser(restTemplate.exchange(getRequestEntity(request), String.class));
    }

    private static RequestEntity<String> getRequestEntity(RequestObj request) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.putAll(request.getHeaders());
        return new RequestEntity<>(request.getBody(), httpHeaders, HttpMethod.valueOf(request.getMethod()), URI.create(request.getUrl()));
    }


}
