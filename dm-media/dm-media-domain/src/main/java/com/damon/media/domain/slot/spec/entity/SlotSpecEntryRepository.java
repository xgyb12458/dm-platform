package com.damon.media.domain.slot.spec.entity;

import com.damon.shared.model.EntryRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Damon S.
 */
@Repository
public interface SlotSpecEntryRepository extends EntryRepository<SlotSpecEntry, Long> {
}
