package com.yunxiao.spring.core.rest;

import lombok.*;

import java.util.List;
import java.util.Map;

/**
 * @author LuoYunXiao
 * @since 2023/10/19 22:03
 */
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestObj {

    private String url;
    private String method;
    private String body;
    private Map<String, List<String>> headers;
}
