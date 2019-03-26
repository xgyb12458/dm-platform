package com.damon.oauth.domain.resource.entity;

import com.damon.shared.model.EntryRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Damon S.
 */
@Repository
public interface ResourceRepository extends EntryRepository<ResourceEntry, Long> {
}
