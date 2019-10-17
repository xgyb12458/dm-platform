package com.damon.oauth.domain.permission.entity;

import com.damon.shared.model.EntryRepository;
import org.springframework.stereotype.Repository;

/**
 * 许可域数据库操作接口
 * @author Damon S.
 */
@Repository
public interface PermissionRepository extends EntryRepository<PermissionEntry, Long> {
}
