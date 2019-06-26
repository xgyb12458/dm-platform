package com.damon.oauth.domain.audit.entity;

import com.damon.shared.model.EntryRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Damon S.
 */
@Repository
public interface AuditRepository extends EntryRepository<AuditEntry, Long> {
}
