package com.damon.media.domain.app.entity;

import com.damon.shared.model.EntryRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Damon S.
 */
@Repository
public interface AppEntryRepository extends EntryRepository<AppEntry, Long> {
}
