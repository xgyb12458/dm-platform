package com.damon.media.domain.slot.layout.entity;

import com.damon.shared.model.EntryRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Damon S.
 */
@Repository
public interface FeedLayoutEntryRepository extends EntryRepository<FeedLayoutEntry, Long> {
}
