package com.damon.shared.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * 基于JPA + QueryDSL的数据持久化接口
 * @author Damon S.
 * @param <T> 数据库实体类型
 * @param <ID> 唯一标识字段类型
 */
@NoRepositoryBean
public interface EntryRepository<T, ID extends Serializable>
        extends JpaRepository<T, ID>, JpaSpecificationExecutor<T>, QuerydslPredicateExecutor<T> {
}
