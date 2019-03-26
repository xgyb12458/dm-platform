package com.damon.oauth.domain.operation.entity;

import com.damon.shared.model.EntryRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Damon S.
 */
@Repository
public interface OperationRepository extends EntryRepository<OperationEntry, Long> {
}
