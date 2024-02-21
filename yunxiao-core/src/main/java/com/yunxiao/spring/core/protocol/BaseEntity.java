package com.yunxiao.spring.core.protocol;

import lombok.Data;
import org.springframework.data.annotation.*;
import org.springframework.data.relational.core.mapping.Column;

import java.time.LocalDateTime;

/**
 * @author LuoYunXiao
 * @since 2023/12/17 12:28
 */
@Data
public class BaseEntity {
    @Id
    private Long id;

    @CreatedDate
    @Column("created_at")
    private LocalDateTime createdAt;

    @CreatedBy
    @Column("created_by")
    private String createdBy;

    @LastModifiedDate
    @Column("updated_at")
    private LocalDateTime updatedAt;

    @LastModifiedBy
    @Column("updated_by")
    private String updatedBy;

    private Integer tenantId;
    private Integer deleted;
}
