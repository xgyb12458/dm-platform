package com.damon.oauth.domain.permission.entity;

import com.damon.shared.model.EntryRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Damon S.
 */
@Repository
public interface PermissionRepository extends EntryRepository<PermissionEntry, Long> {
}
