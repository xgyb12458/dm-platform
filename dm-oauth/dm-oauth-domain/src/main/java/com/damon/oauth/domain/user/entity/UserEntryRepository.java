package com.damon.oauth.domain.user.entity;

import com.damon.shared.model.EntryRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Damon S.
 */
@Repository
public interface UserEntryRepository extends EntryRepository<UserEntry, Long> {
}
