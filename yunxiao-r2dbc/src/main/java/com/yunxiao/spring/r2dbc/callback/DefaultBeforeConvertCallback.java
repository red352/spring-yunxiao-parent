package com.yunxiao.spring.r2dbc.callback;

import cn.hutool.core.util.IdUtil;
import com.yunxiao.spring.core.protocol.BaseEntity;
import org.reactivestreams.Publisher;
import org.springframework.core.Ordered;
import org.springframework.data.r2dbc.mapping.event.BeforeConvertCallback;
import org.springframework.data.relational.core.sql.SqlIdentifier;
import reactor.core.publisher.Mono;

/**
 * @author LuoYunXiao
 * @since 2023/12/17 12:27
 */
public class DefaultBeforeConvertCallback implements BeforeConvertCallback<BaseEntity>, Ordered {
    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }

    @Override
    public Publisher<BaseEntity> onBeforeConvert(BaseEntity entity, SqlIdentifier table) {

        if (entity.getId() == null) {
            entity.setId(IdUtil.getSnowflakeNextId());
        }
        if (entity.getDeleted() == null) {
            entity.setDeleted(0);
        }
        return Mono.just(entity);
    }
}
