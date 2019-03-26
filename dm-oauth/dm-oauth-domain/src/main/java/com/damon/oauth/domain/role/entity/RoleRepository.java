package com.damon.oauth.domain.role.entity;

import com.damon.shared.model.EntryRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Damon S.
 */
@Repository
public interface RoleRepository extends EntryRepository<RoleEntry, Long> {
}
