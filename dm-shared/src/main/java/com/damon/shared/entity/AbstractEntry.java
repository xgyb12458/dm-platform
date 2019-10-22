package com.damon.shared.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * 数据库表映射基础类
 * @author Damon S.
 * @version v1.0.1
 * @date 2019年05月02日 20:25
 */
@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractEntry implements Serializable {

    @Column(nullable = false)
    protected Integer removed;

    @CreatedBy
    @Column(name = "created_by", nullable = false, updatable = false)
    protected Long   createdBy;

    @LastModifiedBy
    @Column(name = "updated_by")
    protected Long   updatedBy;

    @Column(name = "removed_by")
    protected Long   removedBy;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    protected Long   createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    protected Long   updatedAt;

    @Column(name = "removed_at", updatable = false)
    protected Long   removedAt;
}
