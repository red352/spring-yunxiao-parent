package com.yunxiao.spring.core.protocol;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.PageRequest;

/**
 * @author LuoYunXiao
 * @since 2023/10/22 0:15
 */
@Valid
public record RequestPage(
        @NotNull
        @Min(1)
        int pageNum,
        @NotNull
        @Min(1)
        int pageSize
) {

    public PageRequest convert() {
        return PageRequest.of(pageNum - 1, pageSize);
    }

}
